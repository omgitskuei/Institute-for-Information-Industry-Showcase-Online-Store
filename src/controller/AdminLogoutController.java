package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.order.OrderBean;

@Controller
@SessionAttributes(names = {"userEmail"})
public class AdminLogoutController {

	@Autowired
	public AdminLogoutController() {
	}

	// Write Logout 
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String processActionLogout() {
		System.out.println("Directing to Login Page");
		return "AdminLogin";
	}
	
//	// Write RedirectHomePage
//	@RequestMapping(path = "/controller.AdminRedirectController", method = RequestMethod.GET)
//	public String processAction(@SessionAttribute("userEmail") String uEmail, Model nextPage) {
//		System.out.println("Directing to AdminIndex");
////		nextPage.addAttribute("userEmail", uEmail);
//		return "AdminIndex";
//	}
	
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public String redirectAction(@SessionAttribute("userEmail") String uEmail, Model model) {
		System.out.println("Directing to AdminIndex");
		return "AdminIndex";
	}


}
