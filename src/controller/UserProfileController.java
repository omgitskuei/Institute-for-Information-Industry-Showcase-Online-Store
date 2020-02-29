package controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.order.OrderBeanDAO;
import model.profile.ProfileBean;
import model.profile.ProfileBeanService;
import model.user.UserBeanService;
import model.wallet.WalletBeanService;

// 使用者 Profile 控制器
@Controller
@SessionAttributes(names= {"userEmail"})
@RequestMapping("/UserProfile")
public class UserProfileController {
	
	@Autowired
	private ProfileBeanService profileService;
	
	@Autowired
	private UserBeanService userService;
	
	@Autowired
	private WalletBeanService walletService;
	
	@Autowired
	private OrderBeanDAO orderDAO;
	
	@GetMapping("/userUpdateForm")
	public String showFormForUpdate(@SessionAttribute("userEmail") String uEmail, Model m) {
		int userID = userService.selectUserIDByEmail(uEmail);
		ProfileBean theProfile = profileService.getProfile(userID);
		System.out.println("userID is " + userID);
		m.addAttribute("profile", theProfile);
		return "UserProfileUpdateForm";
	}
	
	@PostMapping("/userUpdateProfile")
	public String updateProfile(@ModelAttribute("profile") @Valid ProfileBean theProfile, BindingResult bindingResult) {
		System.out.println(theProfile);
		profileService.updateProfile(theProfile);
		if (bindingResult.hasErrors()) {
			return "UserProfileUpdateForm";
		} else {
			System.out.println("Success update profile");
			return "redirect:/UserProfile/userUpdateForm";
		}
	}
}
