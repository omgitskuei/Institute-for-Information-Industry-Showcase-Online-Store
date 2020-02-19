package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


// TODO 現在出現很奇怪的狀況，GET換頁沒寫錯，但有時成功有時失敗...他都還是讀到原本寫的html
// 1)進首頁 V
// 2)進關於我們 V
// 3)進服務
// 4)進聯絡我們 
// 5)進登入
// 6)進註冊 V
@Controller 
@SessionAttributes(names= { "userEmail", "userPwd", "rememberMe" })
public class FrontDirectController {

	// 1)進首頁
	// 2)完成
	// 3)Thomas
	@RequestMapping(value = "/directhomepage", method = RequestMethod.GET)
	public String direct() {
		System.out.println("導到首頁");
		return "front_index";
	}
	
	// 1)進關於我們
	// 2)完成
	// 3)Thomas
	@RequestMapping(value = "/directaboutus", method = RequestMethod.GET)
	public String directToAboutUs() {
		System.out.println("導到關於我們");
		return "front_about";
	}
	
	// 1)進服務
	// 2)測試
	// 3)Thomas
	@RequestMapping(value = "/directservices", method = RequestMethod.GET)
	public String directToServices() {
		System.out.println("導到服務");
		return "front_services";
	}
	
	// 1)進聯絡我們
	// 2)測試
	// 3)Thomas
	@RequestMapping(value = "/directcontact", method = RequestMethod.GET)
	public String directToContact() {
		System.out.println("導到聯絡我們");
		return "front_contact";
	}
	
	// 1)進登入
	// 2)測試
	// 3)Thomas
	@RequestMapping(value = "/directlogin", method = RequestMethod.GET)
	public String directToLogin() {
		System.out.println("導到登入");
		return "front_login";
	}
	
	// 1)進註冊
	// 2)測試
	// 3)Thomas
	@RequestMapping(value = "/directsignup", method = RequestMethod.GET)
	public String directToServices1() {
		System.out.println("導到註冊");
		return "front_signup";
	}
	
	
}
