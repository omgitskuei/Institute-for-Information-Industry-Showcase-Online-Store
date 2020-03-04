package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.order.OrderBean;
import model.order.OrderBeanDAO;
import model.orderDetails.OrderDetailsBean;
import model.orderDetails.OrderDetailsBeanDAO;
import model.profile.ProfileBean;
import model.profile.ProfileBeanService;
import model.setting.SettingBean;
import model.setting.SettingBeanService;
import model.user.UserBean;
import model.user.UserBeanService;
import model.wallet.WalletBean;
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
	
	@Autowired
	private SettingBeanService settingService;

  @Autowired
	private OrderDetailsBeanDAO odDAO;

	
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
	
	@GetMapping("/userUpdatePasswordForm")
	public String showFormForUserPassword(@SessionAttribute("userEmail") String uEmail, Model m) {
		int userID = userService.selectUserIDByEmail(uEmail);
		UserBean theUser = userService.selectUser(userID);
		System.out.println("User password was: " + theUser.getUserPwd() + "!!!");
		m.addAttribute("user", theUser);
		
		return "UserUpdatePasswordForm";
	}
	
	// 儲存修改後的密碼
		@PostMapping("/savePassword")
		public String savePassword(@ModelAttribute UserBean updateThisUser, 
								   @RequestParam(value = "newPwd", required = true) String newPwd, 
								   @RequestParam(value = "userID",required = true) int userID) {
			userService.updatePwd(updateThisUser, newPwd);
			return "redirect:/UserProfile/userUpdateForm";
		}
	
	
	// user 查看電子錢包餘額
	@GetMapping("/showUserWallet")
	public String showUserWallet(@SessionAttribute("userEmail") String uEmail, Model m) {
		int userID = userService.selectUserIDByEmail(uEmail);
		WalletBean theUserWallet = walletService.selectUser(userID);
		
		m.addAttribute("wallet", theUserWallet);
		
		return "UserCheckWallet";
	}
	
	@GetMapping("/showTheUserOrder")
	public String showTheUserOrder(@SessionAttribute("userEmail") String uEmail, Model m) {
		int userID = userService.selectUserIDByEmail(uEmail);
		System.out.println("userID is: " + userID);
		List<OrderBean> theUserOrder = orderDAO.selectOrdersByUserID(userID);
		
		m.addAttribute("userOrder", theUserOrder);
		return "UserCheckOrder";
	}

	
	@GetMapping("/showTheUserSetting")
	public String showTheUserSetting(@SessionAttribute("userEmail") String uEmail, Model m) {
		int userID = userService.selectUserIDByEmail(uEmail);
		SettingBean theUserSetting = settingService.select(userID);
		System.out.println("Setting ID is " + theUserSetting.getSettingID());
		System.out.println("User ID is " + theUserSetting.getUserID());
		System.out.println("Security Q is " + theUserSetting.getSettingSecurityQ());
		System.out.println("Security A is " + theUserSetting.getSettingSecurityA());
		System.out.println("Allow metadata is " + theUserSetting.getSettingAllowMetadata());
		
		m.addAttribute("userSetting",theUserSetting);
		System.out.println("UserSetting is :" + theUserSetting);
		return "UserUpdateSettingForm";
	}
	
	@PostMapping("/updateSetting")
	public String updateSetting(@RequestParam(value = "settingID",required = true) int settingID,
							  @RequestParam(value = "userID",required = true) int userID,
			                  @RequestParam(value="settingSecurityQ", required=false) String settingSecurityQ,
			                  @RequestParam(value="settingSecurityA", required=false) String settingSecurityA,
			                  @RequestParam(value="settingDisplayName", required=true) String settingDisplayName,
			                  @RequestParam(value="settingAllowMetadata", required=true) boolean settingAllowMetadata) {
	    SettingBean setting = new SettingBean();
	    setting.setSettingID(settingID);
	    setting.setUserID(userID);
	    setting.setSettingSecurityQ(settingSecurityQ);
	    setting.setSettingSecurityA(settingSecurityA);
	    setting.setSettingDisplayName(settingDisplayName);
	    setting.setSettingAllowMetadata(settingAllowMetadata);
		
		return "UserUpdateSettingForm";

	//使用者查看訂單明細
	@GetMapping("/userDetails")
	public String showForm(@SessionAttribute("userEmail") String uEmail, Model model, @RequestParam("orderID")int OrderID ) {
		//顯示Order部分
		List<OrderBean> orderToDetailsList=orderDAO.selectByOrderID(OrderID);
		model.addAttribute("orderToDetailsList", orderToDetailsList);
		//顯示OrderDetails部分
		List<OrderDetailsBean> detailsList=odDAO.selectAllByOrderID(OrderID);
		model.addAttribute("detailsList", detailsList);

		return "UserDetailsList";

	}
}
