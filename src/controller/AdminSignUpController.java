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

import model.user.UserBean;
import model.user.UserBeanService;
import util.EmailUsers;

@Controller
@SessionAttributes(names = { "nEmail", "nPwd", "rPwd" })
public class AdminSignUpController {

	private UserBeanService service;
	private HttpServletResponse response;

	@Autowired
	public AdminSignUpController(UserBeanService service, HttpServletResponse response) {
		this.service = service;
		this.response = response;
	}

	// Admin attempts to sign up. If credentials Don't match, prepare to create new
	// admin; must enter secret confirm code.
	@RequestMapping(path = "/controller.AdminSignUpController", method = RequestMethod.POST)
	public String signInStep1(@RequestParam(name = "nEmail") String nEmail, @RequestParam(name = "nPwd") String nPwd,
			@RequestParam(name = "rPwd") String cPwd, Model nextPage) {

		System.out.println("BEGIN /controller.AdminSignUpController");
		System.out.println("User input: ");
		System.out.println("Email = " + nEmail);
		System.out.println("Password = " + nPwd);
		System.out.println("Confirm Password = " + cPwd);
		if (cPwd.equals(nPwd)) {
			System.out.println("Confirm Password and New Password match!");
			try {
				System.out.println("User not found");

				nextPage.addAttribute("nEmail", nEmail);
				nextPage.addAttribute("nPwd", nPwd);

				System.out.println("AUTHENTICATED: Directing to AdminLoginConfirm");
				return "AdminLoginConfirm";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ERROR: AdminSignUpController EXCEPTION");
				return "AdminLogin";
			}
		}
		System.out.println("Re-enter Password != New Password, return AdminLogin");
		return "AdminLogin";
	}

	// Admin entered confirm code. If code match, create new admin.
	@RequestMapping(path = "/controller.AdminSignUpConfirmController", method = RequestMethod.POST)
	public String signInStep2(@SessionAttribute(name = "nEmail") String nEmail, @SessionAttribute(name = "nPwd") String nPwd,
			@RequestParam(name = "confirmCode") String confirmCode, Model nextPage) {

		System.out.println("BEGIN /controller.AdminSignUpConfirmController");
		System.out.println("User input: ");
		System.out.println("confirmCode = " + confirmCode);
		System.out.println("");
		if (confirmCode.equals("344pp1")) {
			// Create new bean with passed values, in order to use Service
			UserBean bean = new UserBean();
			bean.setUserEmail(nEmail);
			bean.setUserPwd(nPwd);
			bean.setAdmin(1);
			// Use Service to select bean, if null, means user doesn't current exist
			try {
				// If user doesn't exist, create new user
				// service checkLogin includes validating userEmail and userPwd
				if (service.checkLogin(bean) == null) {
					// Match not found
					System.out.println("User not found, creating new user");
					service.insertAdmin(nEmail, nPwd);

					System.out.println("Sending welcome email");
					EmailUsers emailUtil = new EmailUsers();
					emailUtil.sendWelcomeEmail(nEmail, nEmail);

					System.out.println("model nextPage addAttribute");
					nextPage.addAttribute("userEmail", nEmail);
					nextPage.addAttribute("loggedInUserEmail", nEmail);
					nextPage.addAttribute("loggedInUserPwd", nPwd);

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