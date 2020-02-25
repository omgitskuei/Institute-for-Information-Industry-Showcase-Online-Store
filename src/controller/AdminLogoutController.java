package controller;

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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import util.EncodeHexString;
import util.EncryptString;

@Controller
@SessionAttributes(names = {"userEmail"})
public class AdminLogoutController {
	
	private HttpServletResponse response;
	
	@Autowired
	public AdminLogoutController(HttpServletResponse response) {
		this.response = response;
	}
	
	// 1)回首頁
	// 2)已完成
	// 3)Thomas
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public String processActionLogout(@SessionAttribute("userEmail") String uEmail, Model nextPage) {
		System.out.println("Directing to 首頁");
		
		
		nextPage.addAttribute("userEmail", uEmail);
		return "AdminIndex";
	}
	
	
	// 1)停用Session，並登出
	// 2)已完成
	// 3)Thomas
	// Closes session when user logs out
	 @RequestMapping(value = "/logout", method = RequestMethod.GET)
	 public ModelAndView loadApp(
	    		@CookieValue(value = "EmailCookie", required = false) String cEmail,
				@CookieValue(value = "PasswordCookie", required = false) String cPwd, 
				HttpServletRequest request,
				HttpServletResponse response) {
		 // read Login Cookies
		 ModelAndView nextPage = new ModelAndView("/AdminLogin");
		// Cookies exist
		if (cEmail != null && cPwd != null) {
			System.out.println("有抓到Cookie - Success read cookie");
			System.out.println("	Cookie email value: " + cEmail);
			System.out.println("	Cookie pwd value: " + cPwd);
			EncryptString util = new EncryptString();
			EncodeHexString hexConvert = new EncodeHexString();
			
			// decrypt Email Cookie				
			byte[] emailCipher = hexConvert.HexStringToByteArray(cEmail);
			String email = util.decryptGoogleTinkAEAD(emailCipher, "OMGiloveyou");
			// decrypt Password Cookie
			byte[] pwdCipher = hexConvert.HexStringToByteArray(cPwd);
			String pwd = util.decryptGoogleTinkAEAD(pwdCipher, "OMGiloveyou");
			
			System.out.println("	Cookie email value: " + email);
			System.out.println("	Cookie pwd value: " + pwd);
			
			// delete cookie
			Cookie cookie = new Cookie("EmailCookie", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			cookie = new Cookie("PasswordCookie", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			
			Cookie EmailCookie = new Cookie("EmailCookie", email);
			Cookie PasswordCookie = new Cookie("PasswordCookie", pwd);
			EmailCookie.setMaxAge(60*30);
			PasswordCookie.setMaxAge(60*30);
			
			
			
			response.addCookie(EmailCookie);
			response.addCookie(PasswordCookie);
			//nextPage.addObject(EmailCookie);
			//nextPage.addObject(PasswordCookie);
		} 	else {
			System.out.println("No cookies at nextPage AdminLogin");
		}
		HttpSession session= request.getSession(false);
		SecurityContextHolder.clearContext();
		if(session != null) {
			session.invalidate();
			System.out.println("Invalid Session!");
		}
		
	    return nextPage;
	 }
}
