package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.order.OrderBean;
import model.order.OrderBeanService;
import model.product.ProductBean;
import model.product.ProductBeanService;
import model.user.UserBean;
import model.user.UserBeanService;
import util.EncodeHexString;
import util.EncryptString;

@Controller
@SessionAttributes(names = { "userEmail", "userPwd", "rememberMe" })
public class AdminLoginController {

	private UserBeanService uService;
	private ProductBeanService pService;
	private OrderBeanService oService;
	private HttpServletResponse response;
	//private EncryptString util = new EncryptString();
	//private EncodeHexString hexConvert = new EncodeHexString();

	@Autowired
	public AdminLoginController(UserBeanService service, ProductBeanService pService, OrderBeanService oService, HttpServletResponse response) {
		this.uService = service;
		this.pService = pService;
		this.oService = oService;
		this.response = response;
	}

	// 1)登入功能，並製造Cookie
	// 2)還沒處理回來解碼Cookie的到Input資料欄位裡面
	// 3)Chris, Thomas
	// URL address for this controller, method POST/GET, what data fields
	@RequestMapping(path = "/adminSignIn", method = RequestMethod.POST)
	public String adminSignIn(
			@RequestParam(name = "userEmail") String uEmail,
			@RequestParam(name = "userPwd") String uPwd,
			@RequestParam(name = "rememberMe", required = false, defaultValue = "false") boolean remMe,
			@RequestParam(name = "g-recaptcha-response", required = false) boolean recaptcha,
			@CookieValue(value = "EmailCookie", required = false) Cookie emailCookie,
			@CookieValue(value = "PasswordCookie", required = false) Cookie pwdCookie,
			Model nextPage) {

		System.out.println("BEGIN /adminSignIn");
		System.out.println("	User input: ");
		System.out.println("	Email = " + uEmail);
		System.out.println("	Password = " + uPwd);
		System.out.println("	Remember Me = " + remMe);
		System.out.println("	Recaptcha = " + recaptcha);
		try {
			System.out.println("	Cookie email name, value, maxAge: " + emailCookie.getName() + ", "
					+ emailCookie.getValue() + ", " + emailCookie.getMaxAge());
			System.out.println("	Cookie pwd name, value, maxAge: " + pwdCookie.getName() + ", "
					+ pwdCookie.getValue() + ", " + pwdCookie.getMaxAge());
		} catch (NullPointerException e) {
			System.out.println("No emailcookie and pwdcookie");
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		// Check for empty input
		if ((uEmail == null) || (uPwd.length() < 8 || uPwd == null)) {
			// One of the User's input is empty, return to previous page with error messages
			System.out.println("USER INPUT INVALID: Returning to AdminLogin");
			Map<String, String> errors = new HashMap<String, String>();
			if (uEmail == null || uEmail.length() == 0) {
				errors.put("emailError", "Email is required");
			}

			if (uPwd == null || uPwd.length() < 8) {
				errors.put("pwdError", "Password is too short");
				if (uPwd == null || uPwd.length() == 0) {
					errors.put("pwdError", "Password is required");
				}
			}

			nextPage.addAttribute("errors", errors);
			return "AdminLogin";
		} else {
			UserBean bean = new UserBean();
			if (emailCookie != null && pwdCookie != null) {
				bean.setUserEmail(emailCookie.getValue());
				//byte[] pwdCipher = hexConvert.HexStringToByteArray(pwdCookie.getValue());
				//String pwd = util.decryptGoogleTinkAEAD(pwdCipher, "OMGiloveyou");
				bean.setUserPwd(pwdCookie.getValue());
				bean.setAdmin(1);
			} else {
				bean.setUserEmail(uEmail);
				bean.setUserPwd(uPwd);
				bean.setAdmin(1);
			}
			// Use bean to use UserBeanService uService
			UserBean results = uService.checkLogin(bean);
			System.out.println("Service.select(bean) RESULTS: ");
			if (results == null || results.getUserID() == 0 || results.getAdmin()==0) {
				if (results == null || results.getUserID() ==0) {
					System.out.println("USER NOT FOUND: Returning to AdminLogin");
				} else if (results.getAdmin() == 0) {
					System.out.println("USER FOUND BUT NOT ADMIN");
				}
				// Match not found
				// If match NOT found, return to previous page AdminLogin
				
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("notFoundError", "Incorrect Email or Password");
				nextPage.addAttribute("errors", errors);
				return "AdminLogin";
			} else {
				// If match found, return
				// EEIT111FinalProject/WebContent/WEB-INF/pages/AdminDashboard
				System.out.println("Class = " + results.getClass());
				System.out.println("User ID = " + results.getUserID());
				System.out.println("Email = " + results.getUserEmail());
				System.out.println("Password = " + results.getUserPwd());
				System.out.println("Admin = " + results.getAdmin());
				System.out.println("");

				// Write a Cookie storing email so user don't need to enter email next time
				if (remMe == true) {
					System.out.println("	MAKING COOKIE");
					writeLoginCookie(bean.getUserEmail(), bean.getUserPwd(), nextPage, response);
				} else {
					System.out.println("	Remember Me == false, DELETING OLD COOKIES");
					Cookie cookie = new Cookie("EmailCookie", "");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					cookie = new Cookie("PasswordCookie", "");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				System.out.println("AUTHENTICATED: Directing to AdminIndex");
				nextPage.addAttribute("userEmail", uEmail);
				nextPage.addAttribute("loggedInUserEmail", uEmail);
				nextPage.addAttribute("loggedInUserPwd", uPwd);
				
				
				
				List<ProductBean> totalP = pService.selectAll();
				int totalPCount = totalP.size();
				String totalPCountString = Integer.toString(totalPCount);
				System.out.println("TOTAL PRODUCT COUNT: "+totalPCount);
				
				List<OrderBean> totalO = oService.selectAll();
				int totalOCount = totalO.size();
				String totalOCountString = Integer.toString(totalOCount);
				System.out.println("TOTAL ORDER COUNT: "+totalOCount);
				
				List<UserBean> totalU = uService.selectFuzzy("");
				int totalUCount = totalU.size();
				String totalUCountString = Integer.toString(totalUCount);
				System.out.println("TOTAL USER COUNT: "+totalUCount);
				
				Map<String, String> dataNum = new HashMap<String, String>();
				dataNum.put("product", totalPCountString);
				dataNum.put("order", totalOCountString);
				dataNum.put("user", totalUCountString);
				
				nextPage.addAttribute("dataNum", dataNum);
				
				return "AdminIndex";
			}
		}
	}

	// 1)製造Cookie
	// 2)寫完了，唯一問題為這麽確保加密用的AssociatedData，但又不寫死（暫時是"OMGiloveyou"）
	// 3)Chris
	@RequestMapping("/writeAdminLoginCookie")
	private String writeLoginCookie(String email, String pwd, Model nextPage, HttpServletResponse response) {
		System.out.println("BEGIN: /writeAdminLoginCookie");
		System.out.println("	passed email: " + email);
		Cookie emailCookie = new Cookie("EmailCookie", email);
		System.out.println("	passed pwd: " + pwd);
		// Encrypt pwd before writing to a cookie
		//byte[] cipherPwd = util.encryptGoogleTinkAEAD(pwd, "OMGiloveyou");
		//pwd = hexConvert.byteArrayToHexString(cipherPwd);
		System.out.println("			cookie pwd(encrpyted): " + pwd);
		// Write encrypted pwd cookie
		Cookie pwdCookie = new Cookie("PasswordCookie", pwd);

		emailCookie.setMaxAge(60 * 60);
		pwdCookie.setMaxAge(60 * 60);
		response.addCookie(emailCookie);
		response.addCookie(pwdCookie);
		System.out.println("cookies ["+emailCookie.getName()+", "+pwdCookie.getName()+"] added to response");

		return "writeLoginCookie";
	}

//	@RequestMapping("/readAdminLoginCookie")
//	public String readLoginCookie(
//			@CookieValue(value = "Email", required = false, defaultValue = "user@domain.com") String loginEmail,
//			@CookieValue(name = "Password", required = false, defaultValue = "Testing123!") String loginPwd) {
//
//		return "readCookie";
//	}

}