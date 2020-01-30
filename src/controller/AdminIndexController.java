package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes()
public class AdminIndexController {

	@Autowired
	public AdminIndexController() {
	}

	// URL address for this controller, method POST/GET, what data fields
	@RequestMapping(path = "/controller.AdminIndexController", method = RequestMethod.POST)
	public String processAction( Model nextPage) {
		System.out.println("Directing to AdminLogin");
		return "AdminLogin";
	}



}
