package controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionAttributeStore;

import com.google.crypto.tink.Aead;

import model.profile.ProfileBean;
import model.profile.ProfileBeanService;
import model.setting.SettingBean;
import model.setting.SettingBeanService;
import model.user.UserBean;
import model.user.UserBeanService;
import model.wallet.WalletBean;
import model.wallet.WalletBeanService;
import util.EmailUsers;
import util.EncodeHexString;
import util.EncryptString;
import util.GetCode;
import util.GetDateOrTime;

@Controller
@SessionAttributes(names = { "nEmail", "nPwd", "rPwd" })
public class AdminSignUpController {

	private UserBeanService uService;
	private SettingBeanService sService;
	private WalletBeanService wService;
	private ProfileBeanService pService;
	private HttpServletResponse response;
	private String verificationCode = "";
	
	@Autowired
	public AdminSignUpController(UserBeanService uService, SettingBeanService sService, WalletBeanService wService, ProfileBeanService pService, HttpServletResponse response) {
		this.uService = uService;
		this.sService = sService;
		this.wService = wService;
		this.pService = pService;
		this.response = response;
	}

	// Admin attempts to sign up. If credentials Don't match, prepare to create new
	// admin; must enter secret confirm code.
//	@RequestMapping(path = "/controller.AdminSignUpController", method = RequestMethod.POST)
//	public String signInStep1(@RequestParam(name = "nEmail") String nEmail, @RequestParam(name = "nPwd") String nPwd,
//			@RequestParam(name = "rPwd") String cPwd, Model nextPage) {
//
//		System.out.println("BEGIN /controller.AdminSignUpController");
//		System.out.println("User input: ");
//		System.out.println("Email = " + nEmail);
//		System.out.println("Password = " + nPwd);
//		System.out.println("Confirm Password = " + cPwd);
//		if (cPwd.equals(nPwd)) {
//			System.out.println("Confirm Password and New Password match!");
//			try {
//				System.out.println("User not found");
//
//				nextPage.addAttribute("nEmail", nEmail);
//				nextPage.addAttribute("nPwd", nPwd);
//
//				System.out.println("AUTHENTICATED: Directing to AdminLoginConfirm");
//				// Make code
//				GetCode genCode = new GetCode(10, true, false, false);
//				verificationCode = genCode.generateCode();
//				// Make code available on next page
//				nextPage.addAttribute("verificationCode", verificationCode);
//				// Send 
//				EmailUsers emailSender = new EmailUsers();
//				emailSender.sendVerifyEmail(nEmail, nEmail, verificationCode);
//				// 
//				return "AdminLoginConfirm";
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("ERROR: AdminSignUpController EXCEPTION");
//				return "AdminLogin";
//			}
//		}
//		System.out.println("Re-enter Password != New Password, return AdminLogin");
//		return "AdminLogin";
//	}

	// Admin entered confirm code. If code match, create new admin.
	@RequestMapping(path = "/controller.AdminSignUpConfirmController", method = RequestMethod.POST)
	public String signInStep2(@SessionAttribute(name = "nEmail") String nEmail, @SessionAttribute(name = "nPwd") String nPwd,
			@RequestParam(name = "confirmCode") String confirmCode, Model nextPage) {

		System.out.println("BEGIN /controller.AdminSignUpConfirmController");
		System.out.println("User input: ");
		System.out.println("confirmCode = " + confirmCode);
		System.out.println("verificationCode = "+verificationCode);
		if (confirmCode.equals(verificationCode)) {
			System.out.println("the 2 codes match!");
			// Create new bean with passed values, in order to use Service
			UserBean bean = new UserBean();
			bean.setUserEmail(nEmail);
			bean.setUserPwd(nPwd);
			bean.setAdmin(1);
			// Use Service to select bean, if null, means user doesn't current exist
			try {
				// If user doesn't exist, create new user
				// uService checkLogin includes validating userEmail and userPwd
				if (uService.checkLogin(bean) == null) {
					// Match not found
					System.out.println("User not found, creating new user");
					
//					EncryptString util1 = new EncryptString();
//					Aead aead = util1.newCleartextAEADKeyset();
					// encrypt
//					byte[] cipherPwd = util1.encryptGoogleTinkAEAD(nPwd, "OMGiloveyou");
//					EncodeHexString hexConvert = new EncodeHexString();
//					nPwd = hexConvert.byteArrayToHexString(cipherPwd);
					
					// Create user, profile, setting, wallet beans and inserting beans
					// -- User
					uService.insertAdmin(nEmail, nPwd);
					// -- Setting
					int newAdminID = uService.selectUserIDByEmail(nEmail);
					System.out.println("Creating new setting for user id: "+newAdminID);
					SettingBean bean1 = new SettingBean();
					bean1.setUserID(newAdminID);
					bean1.setSettingDisplayName(nEmail);
					bean1.setSettingAllowMetadata(true);
					bean1.setSettingDisplayName(nEmail);
					bean1.setSettingSecurityQ("");
					bean1.setSettingSecurityA("");
					System.out.println("NEW USER ID = "+ uService.selectUser(newAdminID).getUserID());
					sService.insert(bean1);
					// -- Wallet
					System.out.println("Creating new wallet for user id: "+newAdminID);
					wService.insert(new WalletBean(newAdminID));
					// -- Profile
					System.out.println("Creating new profile for user id: "+newAdminID);
					pService.saveProfile(new ProfileBean(newAdminID, new GetDateOrTime().generateDate(), "0000-000-000", "Taiwan", 1));
					
					
					// Send info to model nextPage
					System.out.println("model nextPage addAttribute");
					nextPage.addAttribute("userEmail", nEmail);
					nextPage.addAttribute("loggedInUserEmail", nEmail);
					nextPage.addAttribute("loggedInUserPwd", nPwd);
					
					// Send email
					System.out.println("Sending welcome email");
					new EmailUsers().sendWelcomeEmail(nEmail, nEmail); // sendWelcomeEmail()'s userName can be Email, Display Name, or Full Name
					
					
					return "AdminIndex";
				} else {
					System.out.println("User already exists in the database, do not create new User");
					return "AdminLogin";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Confirm code incorrect");
		}
		return "AdminLogin";
	}

}