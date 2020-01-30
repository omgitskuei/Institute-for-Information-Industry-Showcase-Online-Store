package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.profile.ProfileBean;
import model.profile.ProfileBeanDAO;

@Controller
@SessionAttributes()
public class ProfileController {
	
	@Autowired
	ProfileBeanDAO dao;
	
	@RequestMapping(path = "/controller.ProfileController")
	public String showProfile(Model m) {
        List<ProfileBean> list=dao.selectAll();    
        m.addAttribute("list",list);  
		return "UserProfile";
	}
}
