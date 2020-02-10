package controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.profile.ProfileBean;
import model.profile.ProfileBeanDAO;
import model.profile.ProfileBeanService;

@Controller
@SessionAttributes
@RequestMapping("/AdminProfile")
public class AdminProfileController {

	@Autowired
	private ProfileBeanService profileService;

	@Autowired
	ProfileBeanDAO dao;
	
	@GetMapping("/list")
	public String listProfiles(Model m) {
		List<ProfileBean> theProfiles = profileService.getProfiles();
		m.addAttribute("profiles", theProfiles);
		return "AdminViewAllUser";
	}

	@PostMapping("/saveProfile")
	public String saveProfile(@ModelAttribute("profile") @Valid ProfileBean theProfile, BindingResult bindingResult) {
		profileService.saveProfile(theProfile);
		
		if (bindingResult.hasErrors()) {
			return "AdminProfileUpdateForm";
		} else {
			return "redirect:/AdminProfile/list";
		}
	}

	@GetMapping("/updateForm")
	public String showFormForUpdate(@RequestParam("userID") int userID, Model m) {
		ProfileBean theProfile = profileService.getProfile(userID);
		m.addAttribute("profile", theProfile);
		return "AdminProfileUpdateForm";
	}
	
	
	@RequestMapping(path = "/showProfile")
	public String showProfile(Model m) {
        List<ProfileBean> list=dao.getProfiles();    
        m.addAttribute("list",list);  
		return "UserProfile";
	}

//	@GetMapping("/delete")
//	public String deleteProfile(@RequestParam("userID") int userID) {
//		profileService.deleteProfile(userID);
//		return "redirect:/AdminProfile/list";
//	}

}
