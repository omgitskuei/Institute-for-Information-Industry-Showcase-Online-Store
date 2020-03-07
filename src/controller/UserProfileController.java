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
import model.setting.SettingBeanDAO;
import model.setting.SettingBeanService;
import model.user.UserBean;
import model.user.UserBeanService;
import model.wallet.WalletBean;
import model.wallet.WalletBeanService;
import util.EncodeHexString;
import util.EncryptString;
import util.ValidateString;

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
	private SettingBeanDAO settingDAO;

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
								   @RequestParam(value = "currentPwd", required = true) String currentPwd,
								   @RequestParam(value = "newPwd", required = true) String newPwd, 
								   @RequestParam(value = "userID",required = true) int userID,
								   Model nextPage) {
			System.out.println("currentPwd"+currentPwd);
			System.out.println("newPwd"+newPwd);
			System.out.println("userID"+userID);
			
			if(newPwd.length()==0 || newPwd==null) {
				newPwd = " ";
			}
			if(currentPwd.length()==0 || currentPwd==null) {
				currentPwd = " ";
			}
			
			ValidateString util = new ValidateString();
			String validateNewPwdResult = util.validatePwdreturnErrors(newPwd);
			System.out.println("validate new pwd results: "+ validateNewPwdResult);
			String validateOldPwdResult = util.validatePwdreturnErrors(currentPwd);
			System.out.println("validate current pwd results: "+ validateOldPwdResult);
			
			if (validateNewPwdResult.equals("VALID PASSWORD") && validateOldPwdResult.equals("VALID PASSWORD")) {
				
				UserBean bean = userService.selectUser(userID);
				String encrypted = bean.getUserPwd();
				EncodeHexString encoder = new EncodeHexString();
				byte[] cipher = encoder.HexStringToByteArray(encrypted);
				EncryptString tink = new EncryptString();
				String decrypted = tink.decryptGoogleTinkAEAD(cipher, "OMGiloveyou");
				
				//if (decrypted.equals(currentPwd)) {
				if (bean != null && bean.getUserID()!=0) {
					userService.updatePwd(updateThisUser, newPwd);
					Map<String, String> errors = new HashMap<String, String>();
					errors.put("mismatchError", "Password Updated!");
					nextPage.addAttribute("errors", errors);
					return "UserUpdatePasswordForm";
				} else {
					Map<String, String> errors = new HashMap<String, String>();
					errors.put("mismatchError", "To verify your identity, please enter correct old password");
					errors.put("invalidError", validateNewPwdResult);
					nextPage.addAttribute("errors", errors);
					return "UserUpdatePasswordForm";
				}
			} else {
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("mismatchError", validateOldPwdResult);
				errors.put("invalidError", validateNewPwdResult);
				nextPage.addAttribute("errors", errors);
				return "UserUpdatePasswordForm";
			}		
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
	public String updateSetting(@ModelAttribute SettingBean updateThisSetting,
			                  @RequestParam(value="settingSecurityQ", required=false) String newQ,
			                  @RequestParam(value="settingSecurityA", required=false) String newA,
			                  @RequestParam(value="settingDisplayName", required=true) String newSettingDisplayName,
			                  @RequestParam(value="settingAllowMetadata", required=true) boolean newSettingAllowMetadata) {
		
		settingDAO.updateSettingSecurityQ(updateThisSetting, newQ);
		settingDAO.updateSettingSecurityA(updateThisSetting, newA);
		settingDAO.updateSettingDisplayName(updateThisSetting, newSettingDisplayName);
		settingDAO.updateSettingAllowMetadata(updateThisSetting, newSettingAllowMetadata);
		
		return "redirect:/UserProfile/showTheUserSetting";
	}

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
