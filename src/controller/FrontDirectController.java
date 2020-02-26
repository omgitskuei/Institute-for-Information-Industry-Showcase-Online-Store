package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.product.ProductBean;
import model.product.ProductBeanService;


// TODO 現在出現很奇怪的狀況，GET換頁沒寫錯，但有時成功有時失敗...他都還是讀到原本寫的html
// 1)進首頁 V
// 2)進關於我們 V
// 3)進服務
// 4)進聯絡我們 
// 5)進登入
// 6)進註冊 V
// 7)進購物車
@Controller 
@SessionAttributes(names= { "userEmail", "userPwd", "rememberMe" })
public class FrontDirectController {
	
	@Autowired
	public ProductBeanService productService;

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
	
	// 1)進服務(商品頁面，User Products Page)
	// 2)測試
	// 3)Thomas
	@RequestMapping(value = "/directservices", method = RequestMethod.GET)
	public String directToServices(Model mm) {
		
		System.out.println("導到服務");
		System.out.println("Directing to user service");
		System.out.println("塞進去以前的 model : " + mm);
		
		List<ProductBean> Inventorylist= productService.selectAll();

		mm.addAttribute("InventoryList", Inventorylist);
		System.out.println("塞進去以後的 model : " + mm );
		System.out.println("Get Inventiry content: " + Inventorylist );
		
		return "front_services";
	}
	
	@RequestMapping(value = "/showSpecificProduct", method = RequestMethod.GET)
	public String showSpecificProduct(@RequestParam("productID") int productID,Model mm) {
		
		System.out.println("導到 show Specific Product");
		System.out.println("Directing to showSpecificProduct");
		
		ProductBean theProduct = productService.getProduct(productID);

		mm.addAttribute("theProduct", theProduct);
		System.out.println(" theProduct 塞進去以後的 model : " + mm );
		System.out.println("抓到的商品物件: " + theProduct );
		
		return "front_specificProduct";
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
	
	@RequestMapping(value = "/directForgotPassword", method = RequestMethod.GET)
	public String directToForgotPassword() {
		System.out.println("導到Forgot Password; front_forgetpwd1_email.jsp");
		return "front_forgetpwd1_email";
	}
	
	// 1)進註冊
	// 2)測試
	// 3)Thomas
	@RequestMapping(value = "/directsignup", method = RequestMethod.GET)
	public String directToServices1() {
		System.out.println("導到註冊");
		return "front_signup";
	}
	
	// 1)進購物車
	// 2)測試
	// 3)Thomas
	@RequestMapping(value = "/directshoppingcart", method = RequestMethod.GET)
	public String directToShoppingCart() {
			System.out.println("導到購物車");
			return "front_shoppingcart";
		}
		
	
}
