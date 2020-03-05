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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.order.OrderBean;
import model.order.OrderBeanDAO;
import model.orderDetails.OrderDetailsBean;
import model.orderDetails.OrderDetailsBeanDAO;
import model.profile.ProfileBean;
import model.profile.ProfileBeanDAO;
import model.profile.ProfileBeanService;
import model.user.UserBean;
import model.user.UserBeanService;
import model.wallet.WalletBean;
import model.wallet.WalletBeanService;
import util.EncodeHexString;
import util.EncryptString;
import util.ValidateString;

// Admin 的 Profile 控制器
@Controller
@SessionAttributes(names= {"userID", "userEmail"})
@RequestMapping("/AdminProfile")
public class AdminProfileController {

	@Autowired
	private ProfileBeanService profileService;
	
	@Autowired
	private UserBeanService userService;

	@Autowired
	private ProfileBeanDAO dao;
	
	@Autowired
	private WalletBeanService walletService;
	
	@Autowired
	private OrderBeanDAO orderDAO;
	
	@Autowired
	private OrderDetailsBeanDAO orderDetailsDAO;
	
	// 列出所有使用者頁面
	@GetMapping("/list")
	public String listProfiles(Model m) {
		List<ProfileBean> theProfiles = profileService.getProfiles();
		m.addAttribute("SearchResults", theProfiles);
		return "AdminViewAllUser";
	}
	
	// 新增基本資料
	@PostMapping("/saveProfile")
	public String saveProfile(@ModelAttribute("profile") @Valid ProfileBean theProfile, BindingResult bindingResult) {
		profileService.saveProfile(theProfile);
		
		if (bindingResult.hasErrors()) {
			return "AdminProfileUpdateForm";
		} else {
			return "redirect:/AdminProfile/list";
		}
	}
	
	@PostMapping("/updateProfile")
	public String updateProfile(@ModelAttribute("profile") @Valid ProfileBean theProfile, BindingResult bindingResult) {
		System.out.println(theProfile);
		profileService.updateProfile(theProfile);
		if (bindingResult.hasErrors()) {
			return "AdminProfileUpdateForm";
		} else {
			return "redirect:/AdminProfile/list";
		}
	}
	
	// 秀出修改單一該使用者基本資料的頁面 (根據 userID 指定使用者)
	@GetMapping("/updateForm")
	public String showFormForUpdate(@RequestParam("userID") int userID, Model m) {
		ProfileBean theProfile = profileService.getProfile(userID);
		
		m.addAttribute("profile", theProfile);
		return "AdminProfileUpdateForm";
	}
	
	// 指定使用者修改密碼頁面
	@GetMapping("/updatePasswordForm")
	public String showFormForPassword(@RequestParam("userID") int userID, Model m) {
		UserBean theUser = userService.selectUser(userID);
		System.out.println(theUser.getUserPwd());
		m.addAttribute("user", theUser);
		
		return "AdminUpdatePasswordForm";
	}
	
	// 儲存修改後的密碼
	@PostMapping("/savePassword")
	public String savePassword(@ModelAttribute UserBean updateThisUser, 
							   @RequestParam(value = "currentPwd", required = true) String currentPwd,
							   @RequestParam(value = "newPwd", required = true) String newPwd, 
							   @RequestParam(value = "userID",required = true) int userID,
							   Model nextPage) {
		UserBean bean = userService.selectUser(userID);
		String encrypted = bean.getUserPwd();
		EncodeHexString encoder = new EncodeHexString();
		byte[] hex = encoder.HexStringToByteArray(encrypted);
		EncryptString tink = new EncryptString();
		String decrypted = tink.decryptGoogleTinkAEAD(hex, "OMGiloveyou");
		if (decrypted.equals(currentPwd)) {
			userService.updatePwd(updateThisUser, newPwd);
			return "redirect:/AdminProfile/list";
		} else {
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("mismatchError", "To verify your identity, please enter correct old password");
			ValidateString util = new ValidateString();
			if (!util.validatePwd(newPwd)) {
				errors.put("invalidError", "Please enter a valid new password");
				
			} 
			nextPage.addAttribute("errors", errors);
			return "UserUpdatePasswordForm";
		}
		
		
	}
	
	// 秀出修改電子錢包表格
	@GetMapping("/updateWalletForm")
	public String showFormForWallet(@RequestParam("userID") int userID, Model m) {
		WalletBean theUserWallet = walletService.selectUser(userID);
		UserBean theUser = userService.selectUser(userID);
		
		m.addAttribute("user", theUser);
		m.addAttribute("wallet", theUserWallet);
		
		return "AdminUpdateWalletForm";
	}
	
	@PostMapping("/saveWallet")
	public String saveWallet(@ModelAttribute WalletBean updateThisWallet, 
							   @RequestParam(value = "newwalletAmount", required = true) float newwalletAmount, 
							   @RequestParam(value = "userID",required = true) int userID) {
		walletService.updateAmount(updateThisWallet, newwalletAmount);
		return "redirect:/AdminProfile/list";
	}
	
	@GetMapping("/showTheUserOrer")
	public String showTheUserOrder(@RequestParam("userID") int userID, Model m) {
	    System.out.println("userID is: " + userID);
		List<OrderBean> theUserOrder = orderDAO.selectOrdersByUserID(userID);
		UserBean theUser = userService.selectUser(userID);
		
		m.addAttribute("user", theUser);
		m.addAttribute("userOrder", theUserOrder);
		return "AdminShowTheUserOrder";
	}
	
	

	// 先不用刪除使用者
//	@GetMapping("/delete")
//	public String deleteProfile(@RequestParam("userID") int userID) {
//		profileService.deleteProfile(userID);
//		return "redirect:/AdminProfile/list";
//	}
	

}
