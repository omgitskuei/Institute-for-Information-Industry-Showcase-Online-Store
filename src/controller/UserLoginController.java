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

		

		return "writeLoginCookie";
	}

	// Methods > User sign up account
	@RequestMapping(path = "/userSignIn", method = RequestMethod.POST)
	public String userSignIn(@RequestParam(name = "userEmail") String uEmail,
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
				byte[] pwdCipher = hexConvert.HexStringToByteArray(pwdCookie.getValue());
				String pwd = util.decryptGoogleTinkAEAD(pwdCipher, "OMGiloveyou");
				bean.setUserPwd(pwd);
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
				// Write a Cookie storing email so user don't need to enter email next time
				// Write a Cookie storing email so user don't need to enter email next time
				if (remMe == true) {
					System.out.println("	MAKING COOKIE");
					writeLoginCookie(bean.getUserEmail(), bean.getUserPwd(), nextPage, response);
				} else {
					System.out.println("	Remember Me == false, DELETING OLD COOKIES");
					Cookie cookie = new Cookie("UserEmailCookie", "");
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
