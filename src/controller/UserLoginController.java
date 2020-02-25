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
import org.springframework.web.bind.annotation.SessionAttributes;

import model.user.UserBean;
import model.user.UserBeanService;
import util.EncodeHexString;
import util.EncryptString;

@Controller
@SessionAttributes(names = { "userEmail", "userPwd", "rememberMe" })
public class UserLoginController {

	// Local fields
	private UserBeanService uService;
	private HttpServletResponse response;
	private EncryptString util = new EncryptString();
	private EncodeHexString hexConvert = new EncodeHexString();

	// Constructors
	@Autowired
	public UserLoginController(UserBeanService uService, HttpServletResponse response) {
		this.uService = uService;
		this.response = response;
	}

	// Methods
	@RequestMapping("/writeUserLoginCookie")
	private String writeLoginCookie(String email, String pwd, Model nextPage, HttpServletResponse response) {
		// ^ name is synonymous to 'value'
		// response.addCookie(new Cookie("adminLoginCookie", email));

		System.out.println("email: " + email);
		// Encrypt email before writing to a cookie
		byte[] cipherEmail = util.encryptGoogleTinkAEAD(email, "OMGiloveyou");
		email = hexConvert.byteArrayToHexString(cipherEmail);
		System.out.println("cookie email(encrypted): " + email);
		// Write encrypted email cookie
		Cookie emailCookie = new Cookie("EmailCookie", email);

		System.out.println("pwd: " + pwd);
		// Encrypt pwd before writing to a cookie
		byte[] cipherPwd = util.encryptGoogleTinkAEAD(pwd, "OMGiloveyou");
		pwd = hexConvert.byteArrayToHexString(cipherPwd);
		System.out.println("cookie pwd(encrpyted): " + pwd);
		// Write encrypted pwd cookie
		Cookie pwdCookie = new Cookie("PasswordCookie", pwd);

		emailCookie.setMaxAge(60 * 60);
		pwdCookie.setMaxAge(60 * 60);
		response.addCookie(emailCookie);
		response.addCookie(pwdCookie);
		System.out.println("有抓Cookie");

		return "writeLoginCookie";
	}

	// Methods > User sign up account
	@RequestMapping(path = "/signIn", method = RequestMethod.POST)
	public String signIn(@RequestParam(name = "userEmail") String uEmail, @RequestParam(name = "userPwd") String uPwd,
			@RequestParam(name = "rememberMe", required = false, defaultValue = "false") boolean remMe,
			@RequestParam(name = "g-recaptcha-response", required = false) boolean recaptcha,
			@CookieValue(value = "EmailCookie", required = false, defaultValue = "user@domain.com") String cEmail,
			@CookieValue(value = "PasswordCookie", required = false, defaultValue = "Testing123!") String cPwd,
			Model nextPage) {

		System.out.println("BEGIN /UserLogin");
		System.out.println("User input: ");
		System.out.println("Email = " + uEmail);
		System.out.println("Password = " + uPwd);
		System.out.println("Remember Me = " + remMe);

		// Check for empty input
		if ((uEmail == null) || (uPwd.length() < 8 || uPwd == null)) {
			// One of the User's input is empty, return to previous page with error messages
			System.out.println("USER INPUT INVALID: Returning to AdminLogin");
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
			return "AdminLogin";
		} else {
			// User input somewhat valid
			UserBean bean = new UserBean();
			bean.setUserEmail(uEmail);
			bean.setUserPwd(uPwd);
			bean.setAdmin(1);
			// Use bean to use UserBeanService uService
			UserBean results = uService.checkLogin(bean);
			System.out.println("Service.select(bean) RESULTS: ");
			if (results == null || results.getUserID() == 0) {
				// Match not found
				// If match NOT found, return to previous page AdminLogin
				System.out.println("USER NOT FOUND: Returning to AdminLogin");
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("notFoundError", "Incorrect Email or Password");
				nextPage.addAttribute("errors", errors);
				return "AdminLogin";
			} else {
				// If match found, return
				// EEIT111FinalProject/WebContent/WEB-INF/pages/AdminDashboard
				System.out.println("Class = " + results.getClass());
				System.out.println("User ID = " + results.getUserID());
				System.out.println("Email = " + results.getUserEmail());
				System.out.println("Password = " + results.getUserPwd());
				System.out.println("Admin = " + results.getAdmin());
				System.out.println("");

				// Write a Cookie storing email so user don't need to enter email next time
				if (remMe == true) {
					System.out.println("MAKING COOKIE");
					writeLoginCookie(uEmail, uPwd, nextPage, response);
				} else {
					// delete cookie
					Cookie EmailCookie = new Cookie("EmailCookie", uEmail);
					EmailCookie.setMaxAge(0);
					response.addCookie(EmailCookie);
					Cookie PasswordCookie = new Cookie("PasswordCookie", uEmail);
					PasswordCookie.setMaxAge(0);
					response.addCookie(PasswordCookie);
				}
				
				System.out.println("AUTHENTICATED: Directing to AdminIndex");
				nextPage.addAttribute("userEmail", uEmail);
				nextPage.addAttribute("loggedInUserEmail", uEmail);
				nextPage.addAttribute("loggedInUserPwd", uPwd);
				return "AdminIndex";
			}
		}
	}
}
