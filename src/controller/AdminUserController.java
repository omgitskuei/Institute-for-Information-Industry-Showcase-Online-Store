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
import model.profile.ProfileBean;
import model.profile.ProfileBeanDAO;

@Controller
@SessionAttributes(names = {"userEmail"})
public class AdminUserController {

	@Autowired
	ProfileBeanDAO dao;
	
	public AdminUserController() {
	}

	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String showForm(@SessionAttribute("userEmail") String uEmail, Model model) {
		System.out.println("Directing to AdminUser");
//		nextPage.addAttribute("userEmail", uEmail);
		List<ProfileBean> profileList=dao.selectAll();
		model.addAttribute("profileList", profileList);
		return "AdminViewAllUser";
	}

}
