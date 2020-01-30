package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.user.UserBean;
import model.user.UserBeanService;

@Controller
@SessionAttributes(names= {"userEmail"})
public class CheckoutController {
	private UserBeanService uService;
	
	@Autowired
	public CheckoutController(UserBeanService service) {
		this.uService = service;
	}
	
	@RequestMapping(value="/selectSetting",method = RequestMethod.POST)    
	public String processActionPost(@SessionAttribute("userEmail") String email, Model nextPage) {
		UserBean selectThisUser = ;
		
		uService.select(selectThisUser);
		//nextPage.addAllAttributes(ArrayList<String> s);
		return "redirect:/checkout";
	}
}