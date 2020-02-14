package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes(names = {"userEmail"})
public class AdminLogoutController {

	@Autowired
	public AdminLogoutController() {
	}

	// Write Logout 
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public String processActionLogout() {
//		System.out.println("Directing to Login Page");
//		return "AdminLogin";
//	}
	
//	// Write RedirectHomePage
//	@RequestMapping(path = "/controller.AdminRedirectController", method = RequestMethod.GET)
//	public String processAction(@SessionAttribute("userEmail") String uEmail, Model nextPage) {
//		System.out.println("Directing to AdminIndex");
////		nextPage.addAttribute("userEmail", uEmail);
//		return "AdminIndex";
//	}
	
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public String rediectAction(@SessionAttribute("userEmail") String uEmail, Model model) {
		System.out.println("Directing to AdminIndex");
		return "AdminIndex";
	}
	
	 @RequestMapping(value = "/logout", method = RequestMethod.GET)
	    public ModelAndView loadApp(HttpServletRequest request) {
	        HttpSession session= request.getSession(false);
	        SecurityContextHolder.clearContext();
	        if(session != null) {
	            session.invalidate();
	            System.out.println("Session Cleaned");
	        }

	        return new ModelAndView("/AdminLogin");
	    }


}
