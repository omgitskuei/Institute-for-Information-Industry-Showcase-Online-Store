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
public class AdminUserController {

	@Autowired
	public AdminUserController() {
	}

	// URL address for this controller, method POST/GET, what data fields
	@RequestMapping(path = "/controller.AdminUserController", method = RequestMethod.POST)
	public String processAction(@SessionAttribute("userEmail") String uEmail) {
		System.out.println("Directing to AdminOrder");
		return "AdminUser2";
	}


}
