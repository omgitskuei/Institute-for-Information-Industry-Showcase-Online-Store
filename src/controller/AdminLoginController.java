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
	
	// URL address for this controller, method POST/GET, what data fields
	@RequestMapping(path = "/controller.AdminLoginController", method = RequestMethod.POST)
	public String processAction(@RequestParam(name = "userEmail") String uEmail,
			@RequestParam(name = "userPwd") String uPwd,
			@RequestParam(name = "rememberMe", required = false, defaultValue = "false") boolean remMe,
			Model nextPage) {

		System.out.println("BEGIN /controller.AdminLoginController");
		System.out.println("User input: ");
		System.out.println("Email = " + uEmail);
		System.out.println("Password = " + uPwd);
		System.out.println("Remember Me = " + remMe);
		System.out.println("");

		// Check for empty input
		if ( (uEmail.length() >= 5 && uEmail != null) && (uPwd.length() >= 8 && uPwd != null)) {
			
			// Write a Cookie storing email so user don't need to enter email next time    *CURRENTLY INCOMPLETE*
			if (remMe == true) {
				System.out.println("MAKING COOKIE");
				writeLoginCookie(uEmail, nextPage, response);
			}
			
			// Turn user input into a persistence bean
			UserBean bean = new UserBean();
			bean.setUserEmail(uEmail);
			bean.setUserPwd(uPwd);
			bean.setAdmin(1);
			// Use bean to use UserBeanService service
			UserBean results = service.select(bean);
			System.out.println("Service.select(bean) RESULTS: ");
			if (results == null || results.getUserID()==0) {
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

				System.out.println("AUTHENTICATED: Directing to AdminIndex");
				nextPage.addAttribute("userEmail", uEmail);
				nextPage.addAttribute("loggedInUserEmail", uEmail);
				nextPage.addAttribute("loggedInUserPwd", uPwd);
				return "AdminIndex";
			}
		}
		// One of the User's input is empty, return to previous page with error messages
		else {
			System.out.println("USER INPUT INVALID: Returning to AdminLogin");
			Map<String, String> errors = new HashMap<String, String>();
			if(uEmail==null || uEmail.length()==0) {
				errors.put("emailError", "Email is required");
			}
			
			if(uPwd==null || uPwd.length()<8) {
				errors.put("pwdError", "Password is too short");
				if(uPwd==null || uPwd.length()==0) {
					errors.put("pwdError", "Password is required");
				}
			}
			
			nextPage.addAttribute("errors", errors);
			return "AdminLogin";
		}
	}

	@RequestMapping("/writeAdminLoginCookie")
	private String writeLoginCookie(
			@CookieValue(name = "adminLoginCookie", required = false, defaultValue = "user@domain.com") String email,
			Model nextPage, HttpServletResponse response) {
		// ^ name is synonymous to 'value'
		response.addCookie(new Cookie("adminLoginCookie", email));
		return "writeLoginCookie";
	}

	@RequestMapping("/readAdminLoginCookie")
	public String readLoginCookie(
			@CookieValue(value = "adminLoginCookie", required = false, defaultValue = "user@domain.com") String loginCookie) {

		return "readCookie";
	}
	



}