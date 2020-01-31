package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(names = {"userEmail"})
public class AdminLogoutController {

	@Autowired
	public AdminLogoutController() {
	}

	// Write Logout 
	@RequestMapping(path = "/controller.AdminLogoutController", method = RequestMethod.POST)
	public String processActionLogout() {
		System.out.println("Directing to Login Page");
		return "AdminLogin";
	}
	
	// Write RedirectHomePage
	@RequestMapping(path = "/controller.AdminRedirectController", method = RequestMethod.POST)
	public String processAction(@SessionAttribute("userEmail") String uEmail, Model nextPage) {
		System.out.println("Directing to AdminIndex");
//		nextPage.addAttribute("userEmail", uEmail);
		return "AdminIndex";
	}


}
