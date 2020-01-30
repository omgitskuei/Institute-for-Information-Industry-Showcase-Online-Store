package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes()
public class ProfileController {
	
	@Autowired
	public ProfileController() {
	}
	
	@RequestMapping(path = "/controller.ProfileController")
	public String processAction() {
		System.out.println("To Profile Page!");
		return "ThisUserProfile";
	}
}
