package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.mailingList.MailBeanService;
import model.product.ProductBean;
import model.product.ProductBeanService;
import util.EmailUsers;


// TODO 現在出現很奇怪的狀況，GET換頁沒寫錯，但有時成功有時失敗...他都還是讀到原本寫的html
// 1)進首頁 V
// 2)進關於我們 V
// 3)進服務
// 4)進聯絡我們 
// 5)進登入
// 6)進註冊 V
// 7)進購物車
// 8)進結帳頁面
@Controller 
@SessionAttributes(names= { "userEmail", "userPwd", "rememberMe" })
public class FrontDirectController {
	
	// Local fields
	private MailBeanService mService;
	public ProductBeanService productService;
	public HttpServletRequest request;
	public HttpServletResponse response;

	// Constructors
	@Autowired
	public FrontDirectController(MailBeanService mService, ProductBeanService productService, HttpServletRequest request, HttpServletResponse response) {
		this.mService=mService;
		this.productService= productService;
		this.request = request;	
		this.response = response;
	}
	
	@RequestMapping(value = "/directStripeCheckout", method = RequestMethod.GET)
	public String directStripeCheckout() {
		System.out.println("導到 Stripe 結賬頁面");
		return "front_checkout";
	}
	
	@RequestMapping(value = "/directCheckoutSuccess", method = RequestMethod.GET)
	public String directCheckoutSuccess() {
		System.out.println("導到　結賬成功頁面");
		return "front_intro_checkoutSuccess";
	}
	
	@RequestMapping(value = "/joinNewsletter", method = RequestMethod.POST)
	public String joinNewsletter(@RequestParam("inputEmail") String email, Model nextPage) {
		System.out.println("BEGIN: /joinNewsletter");
		System.out.println(" 從 front_index.jsp Newsletter 導到 FrontDirectController.java /directnewsletter controller");
		System.out.println(" inputEmail = " + email);
		try {
			mService.insertMail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, String> errors = new HashMap<String, String>();
		errors.put("messageError", "Thank you for joining our newsletter.");
		nextPage.addAttribute("errors", errors);

		System.out.println("導回front_index homepage首頁");
		System.out.println("FINISH: /joinNewsletter");
		return "front_index";
	}
	
	// 1)進首頁
	// 2)完成
	// 3)Thomas
	@RequestMapping(value = "/directhomepage", method = RequestMethod.GET)
	public String directHomepage() {
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

		mm.addAttribute("SearchResults", Inventorylist);
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
		System.out.println("導到Forgot Password; front_forgetpwd.jsp");
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
	
	@RequestMapping(value = "/directLogout", method = RequestMethod.GET)
	public String directLogout() {
		Cookie cookie = new Cookie("loginSuccessCookie", "omg");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		if (session != null) {
			session.invalidate();
			System.out.println("Invalid Session!");
		}
		return "front_intro_logoutSuccess";
	}
	
	@RequestMapping(value = "/directFrontContactUs", method = RequestMethod.POST)
	public String frontContactUs(
			Model nextPage,
			@RequestParam("inputEmail") String email,
			@RequestParam("inputName") String name,
			@RequestParam("inputCategory") String category,
			@RequestParam("inputMessage") String message
			) {
		System.out.println("BEGIN: /directFrontContactUs");
		System.out.println("	從 front_contact.jsp Contact Us 導到 FrontDirectController.java /directFrontContactUs controller");
		System.out.println("		email="+email);
		System.out.println("		name="+name);
		System.out.println("		category="+category);
		System.out.println("		message="+message);
		Map<String, String> errors = new HashMap<String, String>();
		if (email.length()==0 || name.length()==0 || message.length()==0) {
			errors.put("messageError", "To send this form, all fields must be filld out.");
			nextPage.addAttribute("errors", errors);
		} else {
			try {
				EmailUsers emailer = new EmailUsers();
				emailer.sendContactUsEmail(email, name, category, message);
			} catch (Exception e) {
				e.printStackTrace();
			}
			errors.put("messageError", "Thank you for sending us an Email. Please allow 3 business days for a response.");
			nextPage.addAttribute("errors", errors);
			System.out.println("FINISH: /directFrontContactUs");
		}
		return "front_contact";
	}
	
	@RequestMapping(value = "/directCheckOutPage", method = RequestMethod.GET)
	public String checkOutPage() {
		System.out.println("導到結帳頁面");
		return "front_checkout";
	}
	
}
