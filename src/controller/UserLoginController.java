package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
import util.EmailUsers;
import util.EncodeHexString;
import util.EncryptString;
import util.GetCode;
import util.ValidateString;

@Controller
@SessionAttributes(names = { "userEmail", "userPwd", "rememberMe" })
public class UserLoginController {

	// Local fields
	private UserBeanService uService;
	private ProfileBeanService profService;
	private SettingBeanService sService;
	private HttpServletResponse response;
	private EncryptString util = new EncryptString();
	private EncodeHexString hexConvert = new EncodeHexString();
	private ValidateString validator = new ValidateString();
	private String verificationCode = "";
	private int retry=2;
	private String emailField;
	private int userID;

	// Constructors
	@Autowired
	public UserLoginController(UserBeanService uService, ProfileBeanService profService, SettingBeanService sService, HttpServletResponse response) {
		this.uService = uService;
		this.profService = profService;
		this.sService = sService;
		this.response = response;
	}

	// Methods
	@RequestMapping("/writeUserLoginCookie")
	private String writeUserLoginCookie(String email, String pwd, Model nextPage, HttpServletResponse response) {
		System.out.println("BEGIN: /writeAdminLoginCookie");
		System.out.println("	passed email: " + email);
		Cookie emailCookie = new Cookie("UserEmailCookie", email);
		System.out.println("	passed pwd: " + pwd);
		// Encrypt pwd before writing to a cookie
		//byte[] cipherPwd = util.encryptGoogleTinkAEAD(pwd, "OMGiloveyou");
		//pwd = hexConvert.byteArrayToHexString(cipherPwd);
		System.out.println("			cookie pwd(encrpyted): " + pwd);
		// Write encrypted pwd cookie
		Cookie pwdCookie = new Cookie("UserPasswordCookie", pwd);

		emailCookie.setMaxAge(60 * 60);
		pwdCookie.setMaxAge(60 * 60);
		response.addCookie(emailCookie);
		response.addCookie(pwdCookie);
		System.out.println("cookies ["+emailCookie.getName()+", "+pwdCookie.getName()+"] added to response");

		return "writeUserLoginCookie";
	}

	@RequestMapping(path = "/userForgotPwd", method = RequestMethod.POST)
	public String userForgotPwd(
			Model nextPage,
			@RequestParam(name="userEmail") String userEmail
			) {
		System.out.println("BEGIN /userForgotPwd");
		// if there are errors, return to previous page, else query the input
		String result = validator.validateEmailreturnErrors(userEmail);
		System.out.println("	validate emamil result: "+result);
		if ( !(result.equals("VALID EMAIL")) ) {		// NOT VALID EMAIL
			System.out.println("		Adding error message to nextPage model");
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("validateError", result);
			nextPage.addAttribute("errors", errors);
			System.out.println("	returning to front_forgetpwd.jsp");
			System.out.println("FINISH /userForgotPwd");
			return "front_forgetpwd1_email";
		} else {
			System.out.println("		Email is valid, LookUp email, send email if exists");
			int lookUpEmail = uService.selectUserIDByEmail(userEmail); // returns 0 if not found
			if (lookUpEmail==0) {		// NOT FOUND
				System.out.println("			User with this Email NOT FOUND");
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("validateError", "This email is not registered in the system");
				nextPage.addAttribute("errors", errors);
				System.out.println("	returning to front_forgetpwd.jsp");
				System.out.println("FINISH /userForgotPwd");
				return "front_forgetpwd1_email";
			} else {
				System.out.println("			User with this Email FOUND: userID="+lookUpEmail);
				ProfileBean bean = profService.getProfile(lookUpEmail);
				System.out.println("			profilebean with this ID = "+bean);
				String userName = bean.getProfileFullName();
				System.out.println("			userName = "+userName);
				GetCode gen = new GetCode(10, true, true, false);
				verificationCode = gen.generateCode();
				//nextPage.addAttribute("verificationCode", verificationCode);
				EmailUsers emailSender = new EmailUsers();
				emailSender.sendForgotPwdEmail(userEmail, userName, verificationCode);
				System.out.println("	returning to front_forgetpwd2_code.jsp");
				System.out.println("FINISH /userForgotPwd2");
				retry = 2;
				emailField=userEmail;
				//nextPage.addAttribute("", userEmail);
				return "front_forgetpwd2_code";
			}
		}
	}
	
	@RequestMapping(path = "/userForgotPwd2", method = RequestMethod.POST)
	public String userForgotPwd2(
			Model nextPage,
			@RequestParam(name="confirmCode") String confirmCode
			) {
		System.out.println("BEGIN: /userForgotPwd2");
		System.out.println("	User input: confirmCode = " + confirmCode);
		System.out.println("	verificationCode = " + verificationCode);
		System.out.println("	retries left = "+retry);
		if (confirmCode.equals(verificationCode)) {
			System.out.println("		the 2 codes match!");
			// if user has security questions, we can test them
			// if no questions, then go straight into password update
			userID = uService.selectUserIDByEmail(emailField);
			SettingBean bean = sService.select(userID);
			if (!(bean.getSettingSecurityQ().equals("")) ){
				System.out.println("	this user has security questions, we will go over them now");
				System.out.println("	take user to page with SecQ");
				retry=2;
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("securityQuestion", bean.getSettingSecurityQ());
				nextPage.addAttribute("errors", errors);
				return "front_forgetpwd3_securityQuestions";
			} else {
				System.out.println("	this user has no security questions.");
				System.out.println("	take user to page with password update");
				return "front_forgetpwd3_updatePwd";
			}
		} else {
			System.out.println("Confirm code incorrect");
			if (retry > 0) {
				System.out.println("Confirm code incorrect");
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("validateError", "驗證碼有效錯誤: 您剩下"+retry+"次機會");
				nextPage.addAttribute("errors", errors);
				retry--;
				return "front_forgetpwd2_code";
			} else {
				return "front_login";
			}
		}
	}
	
	@RequestMapping(path = "/userForgotPwd3_5", method = RequestMethod.POST)
	public String userForgotPwd3_5UpdatePassword(
			Model nextPage,
			@RequestParam(name="securityAnswer") String userInput
			) {
		SettingBean bean = sService.select(userID);
		String correctSecurityAnswer = bean.getSettingSecurityA();
		System.out.println("user inputed this security Answer: "+userInput);
		System.out.println("correct Security answer is: "+correctSecurityAnswer);
		System.out.println("retry = "+retry);
		if (userInput.equals(correctSecurityAnswer)) {
			return "front_forgetpwd4_updatePwd";
		} else {
			if(retry>0) {
				System.out.println("retry>0");
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("validateError", "Security Question 有錯誤: 您剩下"+retry+"次機會");
				nextPage.addAttribute("errors", errors);
				retry--;
				return "front_forgetpwd3_securityQuestions";
			} else {
				System.out.println("	ran out of retries, so directing to front_login");
				return "front_login";
			}
		}
	}
	
	@RequestMapping(path = "/userForgotPwd3", method = RequestMethod.POST)
	public String userForgotPwd3UpdatePassword(
			Model nextPage,
			@RequestParam(name="newPwd") String newPwd,
			@RequestParam(name="confirmPwd") String confirmPwd
			) {
		System.out.println("BEGIN: /userForgotPwd3");
		System.out.println("	User input: newPwd = " + newPwd);
		System.out.println("	User input: confirmCode = " + confirmPwd);
		if (newPwd.equals(confirmPwd)) {
			System.out.println("		the 2 passwords match!");
			String passwordValidation = validator.validatePwdreturnErrors(newPwd);
			if(passwordValidation.equals("VALID PASSWORD")) {
				System.out.println("	new password is valid!");
				
				System.out.println("FINISH: /userForgotPwd3");
				return "front_intro_forgotPwdSuccess";
			} else {
				System.out.println("	invalid password");
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("validateError", passwordValidation);
				nextPage.addAttribute("errors", errors);
				System.out.println("FINISH: /userForgotPwd3");
				return "front_forgetpwd3_updatePwd";
			}
			
		} else {
			System.out.println("	passwords don't match");
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("validateError", "兩個密碼必須一樣");
			nextPage.addAttribute("errors", errors);
			retry--;
			System.out.println("FINISH: /userForgotPwd3");
			return "front_forgetpwd3_updatePwd";
		}
	}
	
	
	// Methods > User sign up account
	@RequestMapping(path = "/userSignIn", method = RequestMethod.POST)
	public String userSignIn(
			//@SessionAttribute("userEmail") String dEmail, // display 用的
			@RequestParam(name = "userEmail") String uEmail,
			@RequestParam(name = "userPwd") String uPwd,
			@RequestParam(name = "rememberMe", required = false, defaultValue = "false") boolean remMe,
			@RequestParam(name = "g-recaptcha-response", required = false) boolean recaptcha,
			@CookieValue(value = "UserEmailCookie", required = false) Cookie emailCookie,
			@CookieValue(value = "UserPasswordCookie", required = false) Cookie pwdCookie, Model nextPage) {

		System.out.println("BEGIN /adminSignIn");
		System.out.println("	User input: ");
		System.out.println("	Email = " + uEmail);
		System.out.println("	Password = " + uPwd);
		System.out.println("	Remember Me = " + remMe);
		System.out.println("	Recaptcha = " + recaptcha);
		try {
			System.out.println("	Cookie email name, value, maxAge: " + emailCookie.getName() + ", "
					+ emailCookie.getValue() + ", " + emailCookie.getMaxAge());
			System.out.println("	Cookie pwd name, value, maxAge: " + pwdCookie.getName() + ", "
					+ pwdCookie.getValue() + ", " + pwdCookie.getMaxAge());
		} catch (NullPointerException e) {
			System.out.println("		No Useremailcookie and Userpwdcookie");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Check for empty input
		if ((uEmail == null) || (uPwd.length() < 8 || uPwd == null)) {
			// One of the User's input is empty, return to previous page with error messages
			System.out.println("	USER INPUT INVALID: Returning to AdminLogin");
			Map<String, String> errors = new HashMap<String, String>();
			if (uEmail == null || uEmail.length() == 0) {
				errors.put("emailError", "Email is required");
			}

			if (uPwd == null || uPwd.length() < 8) {
				errors.put("pwdError", "Password is too short");
				if (uPwd == null || uPwd.length() == 0) {
					errors.put("pwdError", "Password is required");
				}
			}
			
			nextPage.addAttribute("errors", errors);
			return "front_login";
		} else {
			UserBean bean = new UserBean();
			if (emailCookie != null && pwdCookie != null) {
				bean.setUserEmail(emailCookie.getValue());
				//byte[] pwdCipher = hexConvert.HexStringToByteArray(pwdCookie.getValue());
				//String pwd = util.decryptGoogleTinkAEAD(pwdCipher, "OMGiloveyou");
				//bean.setUserPwd(pwd);
				bean.setUserPwd(pwdCookie.getValue());
				bean.setAdmin(0);
			} else {
				bean.setUserEmail(uEmail);
				bean.setUserPwd(uPwd);
				bean.setAdmin(0);
			}
			// Use bean to use UserBeanService uService
			UserBean results = uService.checkLogin(bean);
			System.out.println("	Service.select(bean) RESULTS: ");
			if (results == null || results.getUserID() == 0) {
				if (results == null || results.getUserID() == 0) {
					System.out.println("USER NOT FOUND: Returning to AdminLogin");
				} 
				// Match not found, return to previous page to login again
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("notFoundError", "Incorrect Email or Password");
				nextPage.addAttribute("errors", errors);
				return "front_login";
			} else {
				// If match found, return
				// EEIT111FinalProject/WebContent/WEB-INF/pages/AdminDashboard
				System.out.println("		Class = " + results.getClass());
				System.out.println("		User ID = " + results.getUserID());
				System.out.println("		Email = " + results.getUserEmail());
				System.out.println("		Password = " + results.getUserPwd());
				System.out.println("		Admin = " + results.getAdmin());
				Cookie cookie = new Cookie("loginSuccessCookie", "omg");
				cookie.setMaxAge(-1);
				response.addCookie(cookie);
				
				// Write a Cookie storing email so user don't need to enter email next time
				// Write a Cookie storing pwd so user don't need to enter email next time
				if (remMe == true) {
					System.out.println("	MAKING COOKIE");
					writeUserLoginCookie(bean.getUserEmail(), bean.getUserPwd(), nextPage, response);
				} else {
					System.out.println("	Remember Me == false, DELETING OLD COOKIES");
					cookie = new Cookie("UserEmailCookie", "");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					cookie = new Cookie("UserPasswordCookie", "");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				System.out.println("AUTHENTICATED: Directing to front_intro_loginSuccess");
				nextPage.addAttribute("userEmail", uEmail);
				nextPage.addAttribute("loggedInUserEmail", uEmail);
				nextPage.addAttribute("loggedInUserPwd", uPwd);
				return "front_intro_loginSuccess";
			}
		}
	}
}
