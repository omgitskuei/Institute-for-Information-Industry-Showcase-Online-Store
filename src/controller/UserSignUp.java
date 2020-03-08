package controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.profile.ProfileBean;
import model.profile.ProfileBeanService;
import model.setting.SettingBean;
import model.setting.SettingBeanService;
import model.user.UserBean;
import model.user.UserBeanService;
import model.wallet.WalletBean;
import model.wallet.WalletBeanService;
import util.EmailUsers;
import util.GetCode;
import util.GetDateOrTime;
import util.ValidateString;

@Controller
@SessionAttributes(names = { "nEmail", "nPwd", "rPwd"})
public class UserSignUp {

	private UserBeanService uService;
	private SettingBeanService sService;
	private WalletBeanService wService;
	private ProfileBeanService pService;
	private HttpServletResponse response;
	private String verificationCode = "";
	private int retry=2;

	@Autowired
	public UserSignUp(UserBeanService uService, SettingBeanService sService, WalletBeanService wService,
			ProfileBeanService pService, HttpServletResponse response) {
		this.uService = uService;
		this.sService = sService;
		this.wService = wService;
		this.pService = pService;
		this.response = response;
	}

	// Admin attempts to sign up. If credentials Don't match, prepare to create new
	// admin; must enter email verification code.
	@RequestMapping(path = "/userSignUp", method = RequestMethod.POST)
	public String userSignUp(
			@RequestParam(name = "nEmail") String nEmail, 
			@RequestParam(name = "nPwd") String nPwd,
			@RequestParam(name = "rPwd") String cPwd, Model nextPage) {
		System.out.println("BEGIN /userSignUp");
		System.out.println("	User input: ");
		System.out.println("	Email: " + nEmail);
		System.out.println("	Password: " + nPwd);
		System.out.println("	Confirm Password: " + cPwd);

		if ((nEmail == null) || (nPwd.length() < 8 || nPwd == null) || (cPwd.length() < 8)) {
			// return to previous page with error messages
			System.out.println("USER INPUT INVALID: Returning to front_login");
			Map<String, String> errors = new HashMap<String, String>();
			// email empty
			if (nEmail == null || nEmail.length() == 0) {
				errors.put("emailError", "Email is required");
			}
			// password empty or too short
			if (nPwd == null || nPwd.length() < 8) {
				errors.put("pwdError", "Password is too short");
				if (nPwd == null || nPwd.length() == 0) {
					errors.put("pwdError", "Password is required");
				}
			}
			// Passwords don't match
			if (!(cPwd.equals(nPwd))) {
				// Add error messages
				errors.put("pwdError", "Passwords are mismatched");
			}
			nextPage.addAttribute("errors", errors);
			return "front_login";
		}

		// input not empty or mismatched
		else {
			ValidateString validate = new ValidateString();
			boolean validEmail = validate.validateEmail(nEmail);
			boolean validPwd = validate.validatePwd(nPwd);
			if (!validEmail || !validPwd) {
				// invalid email or pwd will return page
				Map<String, String> errors = new HashMap<String, String>();
				if (!validEmail) {
					// Add error messages
					errors.put("emailError", "Email is invalid");
				}
				if (!validPwd) {
					errors.put("pwdError", "Password is invalid");
				}
				return "front_login";
			}
			// valid email and pwd
			else {
				// email verification code
				nextPage.addAttribute("nEmail", nEmail);
				nextPage.addAttribute("nPwd", nPwd);
				// Make code
				GetCode genCode = new GetCode(10, true, true, true);
				verificationCode = genCode.generateCode();
				// Make code available on next page
				nextPage.addAttribute("verificationCode", verificationCode);
				// Send
				EmailUsers emailSender = new EmailUsers();
				emailSender.sendVerifyEmail(nEmail, nEmail, verificationCode);
				System.out.println("Redirecting to front_authmail.jsp");
				retry = 2;
				return "front_authmail";
			}
		}
	}

	// Admin entered confirm code. If code match, create new admin.
	@RequestMapping(path = "/userSignUpVerification", method = RequestMethod.POST)
	public String userSignUpVerification(
			@SessionAttribute(name = "nEmail") String nEmail,
			@SessionAttribute(name = "nPwd") String nPwd, 
			@RequestParam(name = "confirmCode") String confirmCode,
			Model nextPage) {

		System.out.println("BEGIN /userSignUpVerification");
		System.out.println("	User input: confirmCode = " + confirmCode);
		System.out.println("	verificationCode = " + verificationCode);
		if (confirmCode.equals(verificationCode)) {
			System.out.println("		the 2 codes match!");
			// Create new bean with passed values, in order to use Service
			UserBean bean = new UserBean();
			bean.setUserEmail(nEmail);
			bean.setUserPwd(nPwd);
			bean.setAdmin(0);
			// Use Service to select bean, if null, means user doesn't current exist
			try {
				// If user doesn't exist, create new user
				// uService checkLogin includes validating userEmail and userPwd
				// In the case that the user is NOT already in DB, returned UserId will be 0
				if (uService.checkLogin(bean).getUserID() == 0) {

					System.out.println("User not found, creating new user");
					// Create user, profile, setting, wallet beans and inserting beans
					// -- User
					uService.insertUser(nEmail, nPwd);
					int newUserID = uService.selectUserIDByEmail(nEmail);

					// -- Profile
					System.out.println("Creating new profile for user id: " + newUserID);
					ProfileBean s = new ProfileBean();
					s.setUserID(newUserID);
					s.setProfileAddress("Taiwan");
					s.setProfileFullName(nEmail);
					try {
						GetDateOrTime dateUtil = new GetDateOrTime();
						Date y = dateUtil.generateDate();
						s.setProfileJoinDate(y);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					s.setProfilePhone("0000-000-000");
					s.setProfileVIP(0);
					pService.saveProfile(s);
					// -- Wallet
					System.out.println("Creating new wallet for user id: " + newUserID);
					wService.insert(new WalletBean(newUserID));
					// -- Setting
					System.out.println("Creating new setting for user id: " + newUserID);
					SettingBean bean1 = new SettingBean();
					bean1.setUserID(newUserID);
					bean1.setSettingDisplayName(nEmail);
					bean1.setSettingAllowMetadata(true);
					bean1.setSettingDisplayName(nEmail);
					bean1.setSettingSecurityQ("");
					bean1.setSettingSecurityA("");
					System.out.println("NEW USER ID = " + uService.selectUser(newUserID).getUserID());
					sService.insert(bean1);

					// Send info to model nextPage
					System.out.println("model nextPage addAttribute");
					nextPage.addAttribute("userEmail", nEmail);
					nextPage.addAttribute("loggedInUserEmail", nEmail);
					nextPage.addAttribute("loggedInUserPwd", nPwd);

					// Send email
					System.out.println("Sending welcome email");
					new EmailUsers().sendWelcomeEmail(nEmail, nEmail); // sendWelcomeEmail()'s userName can be Email,
																		// Display Name,
																		// or Full Name

					return "front_intro_signUpSuccess";
				} else {
					System.out.println("User already exists in the database, do not create new User");
					Map<String, String> errors = new HashMap<String, String>();
					errors.put("notFoundError", "This user already exists - please sign in.");
					nextPage.addAttribute("errors", errors);
					return "front_login";
				}
			} catch (Exception e) {
				e.printStackTrace();
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("notFoundError", "EXCEPTION ERROR - SEE CONSOLE");
				nextPage.addAttribute("errors", errors);
				return "front_login";
			}
		} else {
			System.out.println("retry: "+retry);
			if (retry > 0) {
				System.out.println("Confirm code incorrect");
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("codeError", "驗證碼有效錯誤: 您剩下"+retry+"次機會");
				nextPage.addAttribute("errors", errors);
				retry--;
				return "front_authmail";
			} else {
				return "front_login";
			}
		}
	}
}
