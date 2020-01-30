package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.profile.ProfileBeanService;
import model.setting.SettingBean;
import model.setting.SettingBeanService;
import model.user.UserBean;
import model.user.UserBeanService;
import model.wallet.WalletBeanService;

@Controller
@SessionAttributes(names= {"userEmail"})
public class CheckoutController {
	private UserBeanService uService;
	private SettingBeanService sService;
	private WalletBeanService wService;
	private ProfileBeanService pService;
	
	@Autowired
	public CheckoutController(UserBeanService uService, SettingBeanService sService, WalletBeanService wService, ProfileBeanService pService) {
		this.uService = uService;
		this.sService = sService;
		this.wService = wService;
		this.pService = pService;
	}
	
	@RequestMapping(value="/selectSetting",method = RequestMethod.POST)    
	public String processActionPost(@SessionAttribute({"userEmail", "userPwd"}) String email, Model nextPage) {
		UserBean newThisUser = new UserBean();
		SettingBean newSetting = new SettingBean();
		
		uService.select(newThisUser);
		//nextPage.addAllAttributes(ArrayList<String> s);
		return "redirect:/checkout";
	}
}