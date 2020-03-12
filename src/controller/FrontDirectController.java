package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.checkout.Session;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;
import model.mailingList.MailBeanService;
import model.product.ProductBean;
import model.product.ProductBeanService;
import model.profile.ProfileBean;
import model.profile.ProfileBeanService;
import model.user.UserBean;
import model.user.UserBeanService;
import util.CheckSubstring;
import util.EmailUsers;


// TODO 現在出現很奇怪的狀況，GET換頁沒寫錯，但有時成功有時失敗...他都還是讀到原本寫的html（已解決）
// 1)進首頁 V
// 2)進關於我們 V
// 3)進服務 V
// 4)進聯絡我們 
// 5)進登入 V
// 6)進註冊 V
// 7)進購物車 V
// 8)進結帳頁面 V
@Controller 
@SessionAttributes(names= { "userEmail", "userPwd", "rememberMe" })
public class FrontDirectController {
	
	// Local fields
	private MailBeanService mService;
	private UserBeanService uService;
	private ProfileBeanService pService;
	public ProductBeanService productService;
	public HttpServletRequest request;
	public HttpServletResponse response;
	private String total;
	private ArrayList<String> checkoutStripeUserInput = new ArrayList<String>();

	
	public static AllInOne all;

	
	private static void initial() {
		all = new AllInOne(null);
	}
	// Constructors
	@Autowired
	public FrontDirectController(MailBeanService mService, ProfileBeanService pService, UserBeanService uService, ProductBeanService productService, HttpServletRequest request, HttpServletResponse response) {
		this.mService=mService;
		this.uService=uService;
		this.pService=pService;
		this.productService= productService;
		this.request = request;	
		this.response = response;
	}
	
	@RequestMapping(value = "/directStripeCheckoutStep1", method = RequestMethod.GET)
	public String directStripeCheckoutStep1(
			@CookieValue(name="loginSuccessCookie", required = false) Cookie loginSuccessCookie,
			@CookieValue(value = "UserEmailCookie", required = false) Cookie emailCookie,
			@SessionAttribute("userEmail") String userEmail,
			@CookieValue(name="totalCookie") String shoppingCartTotal,
			Model nextPage) {
		total = shoppingCartTotal+".00";
		System.out.println("CookieValue is"+shoppingCartTotal+ ", Total is = " +total);
		System.out.println("UserEmailCookie "+emailCookie+", UserEmailCokokie"+emailCookie.getValue());
		
		System.out.println("導到 Stripe 結賬#1頁面");
		System.out.println("SessionAttribute userEmail"+userEmail);
		UserBean uBean = new UserBean();
		uBean.setUserEmail(userEmail);
		int userID = uService.selectUserIDByEmail(userEmail);
		uBean = uService.selectUser(userID);
		
		ProfileBean pBean = pService.getProfile(userID);
		String name = pBean.getProfileFullName();
		String address = pBean.getProfileAddress();
		
		Map<String, String> userData = new HashMap<String, String>();
		userData.put("email", userEmail);
		userData.put("fullname", name);
		userData.put("address", address);
		nextPage.addAttribute("userData", userData);
		
		nextPage.addAttribute("sumTotal", total);
		return "front_checkout_stripe_mailingDetails";
	}
	
	@RequestMapping(value = "/directStripeCheckoutStep2", method = RequestMethod.POST)
	public String directStripeCheckoutStep2(
			@RequestParam(value="fullname") String fullname,
			@RequestParam(value="email") String email,
			@RequestParam(value="country") String country,
			@RequestParam(value="city") String city,
			@RequestParam(value="zipcode") String zipcode,
			@RequestParam(value="address") String address,
			@RequestParam(value="shipAddress") String shipAddress,
			@CookieValue(name="totalCookie") String shoppingCartTotal,
			Model nextPage) {
//		System.out.println("導到 Stripe 結賬#2頁面");
//		System.out.println("fullname"+fullname);
//		System.out.println("email"+email);
//		System.out.println("country"+country);
//		System.out.println("city"+city);
//		System.out.println("zipcode"+zipcode);
//		System.out.println("address"+address);
//		System.out.println("shipaddress"+shipAddress);
		checkoutStripeUserInput.add(fullname);
		checkoutStripeUserInput.add(email);
		checkoutStripeUserInput.add(country);
		checkoutStripeUserInput.add(city);
		checkoutStripeUserInput.add(zipcode);
		checkoutStripeUserInput.add(address);
		checkoutStripeUserInput.add(shipAddress);
		
		nextPage.addAttribute(checkoutStripeUserInput);
		nextPage.addAttribute("sumTotal", total);

		return "front_checkout_stripe_paymentDetails";
	}
	
	@RequestMapping(value = "/directCheckoutSuccess", method = RequestMethod.POST)
	public String directCheckoutSuccess(
			@RequestParam(name="cardNum") String cardNum,
			@RequestParam(name="expiry") String expiry,
			@RequestParam(name="cvCode") String cvCode,
			@RequestParam(name="receipt") String receipt,
			@RequestParam(name="couponCode") String couponCode,
			@RequestParam(name="walletAmount") String walletAmount,
//			@RequestParam("checkoutStripeUserInput") ArrayList<String> checkoutStripeUserInput,
			@CookieValue(name="totalCookie") String shoppingCartTotal
			) {
//		System.out.println("cardNum"+cardNum);
//		System.out.println("expiry"+expiry);
//		System.out.println("cvCode"+cvCode);
//		System.out.println("receipt"+receipt);
//		System.out.println("couponCode"+couponCode);
//		System.out.println("walletAmount"+walletAmount);
//		System.out.println("	totalCookie"+shoppingCartTotal);
		checkoutStripeUserInput.add(cardNum);
		checkoutStripeUserInput.add(expiry);
		checkoutStripeUserInput.add(cvCode);
		checkoutStripeUserInput.add(receipt);
		checkoutStripeUserInput.add(couponCode);
		checkoutStripeUserInput.add(walletAmount);
		
		System.out.println("print all user input" + checkoutStripeUserInput);
		
		
		Stripe.apiKey = "sk_test_s56neoj7TwIIkY5oFr45aZHd00cvXIHSQo";
		
//		Customer newCustomer = createNewCustomer("kueifengtung@yahoo.com");

		// Retrieving Stripe Customer
		Customer retrievedCustomer;
		try {
			retrievedCustomer = Customer.retrieve("cus_GquXSUfYCXaNAF");
			System.out.println(""+retrievedCustomer);
		} catch (StripeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Map<String, Object> params = new HashMap<String, Object>();

		HashMap<String, Object> paymentIntentData = new HashMap<String, Object>();
		paymentIntentData.put("setup_future_usage", "off_session");
		params.put("payment_intent_data", paymentIntentData);
		
		params.put("customer_email", "kueifengtung@yahoo.com");
		
		ArrayList<String> paymentMethodTypes = new ArrayList<>();
		paymentMethodTypes.add("card");
		params.put("payment_method_types", paymentMethodTypes);

		ArrayList<HashMap<String, Object>> lineItems = new ArrayList<>();
		HashMap<String, Object> lineItem = new HashMap<String, Object>();
		lineItem.put("name", "T-shirt");
		lineItem.put("description", "Comfortable cotton t-shirt");
		
		double totalDouble = Double.parseDouble(total);
		int totalInt = (int) totalDouble*100;
		lineItem.put("amount", totalInt);
		
		lineItem.put("currency", "usd");
		lineItem.put("quantity", 1);
		lineItems.add(lineItem);
		params.put("line_items", lineItems);

		HttpSession thisSession = request.getSession(false);
		
		params.put("success_url", "https://420b76e4.ngrok.io/EEIT111FinalProject/directCheckoutSuccess?session_id="+thisSession.getId());
		params.put("cancel_url", "https://420b76e4.ngrok.io/EEIT111FinalProject/directshoppingcart");

		try {
			Session session = Session.create(params);
		} catch (StripeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	public String directHomepage(Model m) {
		System.out.println("導到首頁");
		
		List<ProductBean> Inventorylist= productService.selectAll();

		m.addAttribute("product", Inventorylist);
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
	// 2)完成
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
	
	// 1)導到單一商品畫面
	// 2)完成
	// 3)Jerry
	@RequestMapping(value = "/showSpecificProduct", method = RequestMethod.GET)
	public String showSpecificProduct(
			@RequestParam("productID") int productID,
			@CookieValue(value = "historyCookie", required = false) Cookie historyCookie,
			Model mm) {
		
		System.out.println("導到 show Specific Product");
		System.out.println("Directing to showSpecificProduct");
		
		ProductBean theProduct = productService.getProduct(productID);
		

		System.out.println("-------------------------HISTORY COOKIE:"+historyCookie);
		if (historyCookie != null) {
			System.out.println("historyCookie!=null");
			// Cookie already exist, extract value
			String cookieValue = historyCookie.getValue();
			System.out.println("cookie value: " + cookieValue);
			// string value will look like this format;
			// ... "1,1,13,4,55,,13,34" where these # are productID
			
			// Add new id onto old value
			String productIDString = String.valueOf(productID);
			System.out.println("productIDString: "+productIDString);
			
			CheckSubstring checker = new CheckSubstring();
			if(checker.countAnyChar(cookieValue, ".")<5) {
				System.out.println("is less than 5");
				cookieValue = cookieValue+"."+productIDString;
				
			} else {
				System.out.println("is more than 5");
				ArrayList<String> delimited = checker.delimitAtAnyChar(cookieValue, ".");
				System.out.println("delimited: "+delimited);
				
				delimited = checker.removeAnyChar(delimited, ".");
				System.out.println("delimited: "+delimited);
				
				delimited.remove(0);
				System.out.println("delimited (post slice, remove, addall, remove first: "+delimited);
				delimited.add(productIDString);
				cookieValue = delimited.get(0);
				for(int index = 1; index<delimited.size(); index++) {
					cookieValue = cookieValue + "."+delimited.get(index);
				}
				System.out.println("cookieValue final: "+cookieValue);
			}
			
			
			System.out.println("cookieValue: "+cookieValue);
			historyCookie.setValue(cookieValue);
//			Cookie cookie = new Cookie("historyCookie", cookieValue);
			historyCookie.setMaxAge(-1);
			response.addCookie(historyCookie);
			
		} else {
			// No cookie right now, make new cookie
			String productIDString = String.valueOf(productID);
			Cookie cookie = new Cookie("historyCookie", productIDString);
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
		}

		
		
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
	// 2)完成
	// 3)Thomas
	@RequestMapping(value = "/directsignup", method = RequestMethod.GET)
	public String directToServices1() {
		System.out.println("導到註冊");
		return "front_signup";
	}
	
	// 1)進購物車
	// 2)完成
	// 3)Thomas
	@RequestMapping(value = "/directshoppingcart", method = RequestMethod.GET)
	public String directToShoppingCart() {
			System.out.println("導到購物車");
			return "front_shoppingcart";
		}
	
	// 1)登出
	// 2)完成
	// 3)Chris
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
	
	// 1)導到結帳頁面
	// 2)已完成
	// 3)Thomas
	@RequestMapping(value = "/directCheckOutPage", method = RequestMethod.GET)
	public String checkOutPage() {
		System.out.println("導到結帳頁面");
		return "front_checkout";
	}
	
	
	// 1)導到綠界
	// 2)已完成，但無法回首頁
	// 3)Thomas
	@RequestMapping(value = "/greenPay.controller")
	public String greenPay(
			@CookieValue(name="totalCookie") String shoppingCartTotal, 
			Model m) {
		try {
			initial();
			AioCheckOutOneTime obj = new AioCheckOutOneTime();// 產生信用卡一次付清訂單物件
			
			// String total = importedData.get(importedData.size()-1);// 抓取總金額
			String id = "temporaryID";// 取得會員 利用會員Id跟日期 創建訂單編號

			Date date = new Date();// 目前時間
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");// 設定日期格式 給訂單編號用
			String dateString = sdf.format(date);// 進行轉換
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 設定日期格式 給日期用
			String dateStringToMerchantTradeDate = sdf1.format(date);// 進行轉換
			String merchantTradeNo = id.toString() + dateString;// 合併訂單編號
			
			System.out.println("有進綠界");
			obj.setMerchantTradeNo(dateString);
			obj.setMerchantTradeDate(dateStringToMerchantTradeDate);
			obj.setTotalAmount(shoppingCartTotal);
			obj.setTradeDesc("FarmVille一些商品");
			obj.setItemName("FarmVille一堆商品");
			obj.setReturnURL("http://211.23.128.214:5000");
			obj.setClientBackURL("http://localhost:8080/EEIT111FinalProject/front_intro.jsp");
			obj.setNeedExtraPaidInfo("N");
			String form = all.aioCheckOut(obj, null);
			
			m.addAttribute("form", form);


			System.out.println(form);

			return "greenTest";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return "greenTest";
	}
	
}
