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
@SessionAttributes(names = { "newEmail", "newPwd", "reEnterPwd" })
public class AdminSignUpController {

	private UserBeanService service;
	private HttpServletResponse response;

	@Autowired
	public AdminSignUpController(UserBeanService service, HttpServletResponse response) {
		this.service = service;
		this.response = response;
	}

	// URL address for this controller, method POST/GET, what data fields
	@RequestMapping(path = "/controller.AdminSignUpController", method = RequestMethod.POST)
	public String processAction(@RequestParam(name = "newEmail") String nEmail,
			@RequestParam(name = "newPwd") String nPwd,
			@RequestParam(name = "reEnterPwd") String rPwd,
			Model nextPage) {

		System.out.println("BEGIN /controller.AdminSignUpController");
		System.out.println("User input: ");
		System.out.println("Email = " + nEmail);
		System.out.println("Password = " + nPwd);
		System.out.println("Remember Me = " + rPwd);
		System.out.println("");

		UserBean bean = new UserBean();
		bean.setUserEmail(nEmail);
		bean.setUserPwd(nPwd);
		bean.setAdmin(1);
		try {
			if (service.checkLogin(bean) == null) {
				// Match not found
				// If match NOT found, return to previous page AdminLogin
				System.out.println("USER NOT FOUND");
				System.out.println("AUTHENTICATED: Directing to AdminIndex");
				nextPage.addAttribute("userEmail", nEmail);
				nextPage.addAttribute("loggedInUserEmail", nEmail);
				nextPage.addAttribute("loggedInUserPwd", nPwd);
				return "AdminIndex";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return "AdminIndex";
	}
}