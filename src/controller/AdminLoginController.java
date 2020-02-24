package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.crypto.tink.Aead;

import model.user.UserBean;
import model.user.UserBeanService;
import util.EncodeHexString;
import util.EncryptString;

@Controller
@SessionAttributes(names = { "userEmail", "userPwd", "rememberMe" })
public class AdminLoginController {

	private UserBeanService service;
	private HttpServletResponse response;

	@Autowired
	public AdminLoginController(UserBeanService service, HttpServletResponse response) {
		this.service = service;
		this.response = response;
	}

	// 1)登入功能，並製造Cookie
	// 2)還沒處理回來解碼Cookie的到Input資料欄位裡面
	// 3)Chris, Thomas
	// URL address for this controller, method POST/GET, what data fields
	@RequestMapping(path = "/adminSignIn", method = RequestMethod.POST)
	public String processAction(
			@RequestParam(name = "userEmail") String uEmail,
			@RequestParam(name = "userPwd") String uPwd,
			@RequestParam(name = "rememberMe", required = false, defaultValue = "false") boolean remMe,
			@RequestParam(name = "g-recaptcha-response", required = false) boolean recaptcha,
			@CookieValue(value = "Email", required = false, defaultValue = "user@domain.com") String cookieEmail,
			@CookieValue(value = "Password", required = false, defaultValue = "Testing123!") String cookiePwd,
			Model nextPage) {

		System.out.println("BEGIN /adminSignIn");
		System.out.println("	User input: ");
		System.out.println("	Email = " + uEmail);
		System.out.println("	Password = " + uPwd);
		System.out.println("	Remember Me = " + remMe);
		System.out.println("	Recaptcha = " + recaptcha);
		
		// Check for empty email and pwd input
		Map<String, String> errors = new HashMap<String, String>();
		if (uEmail.length()==0) {
			errors.put("emailError", "Email is required");
		}
		if (uPwd.length() < 8) {
			errors.put("pwdError", "Password is too short");
		}
		if (uPwd.length()==0) {
			errors.put("pwdError", "Password is required");
		}
		if (errors.size() > 0) {
			System.out.println("User input was too short or empty: Returning to AdminLogin");
			System.out.println("FINISH /adminSignIn");
			nextPage.addAttribute("errors", errors);
			return "AdminLogin";
		} else {
			// User input was not empty valid
			UserBean bean = new UserBean();
			bean.setUserEmail(uEmail);
			bean.setUserPwd(uPwd);
			bean.setAdmin(1);

			// Use bean to use UserBeanService to see if there's a match
			UserBean results = service.checkLogin(bean);              // checkLogin READ ONLY, DOESNT DECRYPT Pwd in DB
			System.out.println("	UserService.select(bean) RESULTS: ");
			if (results == null || results.getUserID() == 0) {
				// Match not found
				// If match NOT found, return to previous page AdminLogin
				errors.put("notFoundError", "Incorrect Email or Password");
				nextPage.addAttribute("errors", errors);
				System.out.println("		NO MATCH FOUND: Returning to AdminLogin");
				System.out.println("FINISH /adminSignIn");
				return "AdminLogin";
			} else {
				System.out.println("		userID: "+results.getUserID());
				System.out.println("		Email: "+results.getUserEmail());
				System.out.println("		Pwd: "+results.getUserPwd());
				System.out.println("		Admin: "+results.getAdmin());
				// If match found, return next page
				if (remMe == true) {
					System.out.println("	RememberMe = True: MAKING COOKIE");
					writeLoginCookie(bean, bean, nextPage, response);
					System.out.println("	cookieEmail: "+cookieEmail);
					System.out.println("	cookiePwd: "+cookiePwd);
				}
				
				nextPage.addAttribute("userEmail", uEmail);
				nextPage.addAttribute("loggedInUserEmail", uEmail);
				nextPage.addAttribute("loggedInUserPwd", uPwd);
				System.out.println("	AUTHENTICATED: Directing to AdminIndex");
				System.out.println("FINISH /adminSignIn");
				return "AdminIndex";
			}
		}
	}

	// 1)製造Cookie
	// 2)寫完了，唯一問題為這麽確保加密用的AssociatedData，但又不寫死（暫時是"OMGiloveyou"）
	// 3)Chris
	@RequestMapping("/writeAdminLoginCookie")
	private String writeLoginCookie(
			@CookieValue(name = "Email", required = false, defaultValue = "user@domain.com") UserBean beanWithEmail,
			@CookieValue(name = "Password", required = false, defaultValue = "Testing123!") UserBean beanWithPwd,
			Model nextPage, HttpServletResponse response) {
		// ^ name is synonymous to 'value'
		// response.addCookie(new Cookie("adminLoginCookie", email));

		// Encrypt email before writing to a cookie
		String email = beanWithEmail.getUserEmail();
		EncryptString util1 = new EncryptString();
		// Aead aead = util1.newCleartextAEADKeyset();
		byte[] cipher = util1.encryptGoogleTinkAEAD(email, "OMGiloveyou");
		EncodeHexString hexConvert = new EncodeHexString();
		email = hexConvert.byteArrayToHexString(cipher);
		// Write encrypted email cookie
		Cookie ck = new Cookie("Email", email);
		System.out.println("email: " + beanWithEmail.getUserEmail());
		System.out.println("cookie email(encrypted): " + email);

		// Encrypt pwd before writing to a cookie
		String pwd = beanWithPwd.getUserPwd();
		cipher = util1.encryptGoogleTinkAEAD(email, "OMGiloveyou");
		email = hexConvert.byteArrayToHexString(cipher);
		// Write encrypted pwd cookie
		Cookie pw = new Cookie("Password", pwd);
		System.out.println("pwd: " + beanWithPwd.getUserPwd());
		System.out.println("cookie pwd(encrpyted): " + pwd);

		// ck.setMaxAge(60*60*24);
		// ck.setPath("/");
		response.addCookie(ck);
		response.addCookie(pw);
		System.out.println("有抓Cookie");

		return "writeLoginCookie";
	}

//	@RequestMapping("/readAdminLoginCookie")
//	public String readLoginCookie(
//			@CookieValue(value = "Email", required = false, defaultValue = "user@domain.com") String loginEmail,
//			@CookieValue(name = "Password", required = false, defaultValue = "Testing123!") String loginPwd) {
//
//		return "readCookie";
//	}

}