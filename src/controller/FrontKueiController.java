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

@Controller 
@SessionAttributes(names= { "userEmail", "userPwd", "rememberMe" })
public class FrontKueiController {
	
	// Local fields
	private MailBeanService mService;
	public ProductBeanService productService;
	public HttpServletRequest request;
	public HttpServletResponse response;

	// Constructors
	@Autowired
	public FrontKueiController(MailBeanService mService, ProductBeanService productService, HttpServletRequest request, HttpServletResponse response) {
		this.mService=mService;
		this.productService= productService;
		this.request = request;	
		this.response = response;
	}
	
//	@RequestMapping(value = "/directFrontContactUs", method = RequestMethod.POST)
//	public String frontContactUs(
//			Model nextPage,
//			@RequestParam("inputEmail") String email,
//			@RequestParam("inputName") String name,
//			@RequestParam("inputCategory") String category,
//			@RequestParam("inputMessage") String message
//			) {
//		System.out.println("BEGIN: /directFrontContactUs");
//		System.out.println("	從 front_contact.jsp Contact Us 導到 FrontDirectController.java /directFrontContactUs controller");
//		System.out.println("		email="+email);
//		System.out.println("		name="+name);
//		System.out.println("		category="+category);
//		System.out.println("		message="+message);
//		try {
//			EmailUsers emailer = new EmailUsers();
//			emailer.sendContactUsEmail(email, name, category, message);
//		} catch (Exception e) {
//			e.printStackTrace();
//		};
//		Map<String, String> errors = new HashMap<String, String>();
//		errors.put("messageError", "Thank you for sending us an Email. Please allow 3 business days for a response.");
//		nextPage.addAttribute("errors", errors);
//		System.out.println("FINISH: /directFrontContactUs");
//		return "front_contact";
//	}
	
		
	
}
