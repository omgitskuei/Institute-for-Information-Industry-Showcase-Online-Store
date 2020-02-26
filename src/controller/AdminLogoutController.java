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
@SessionAttributes(names = { "userEmail" })
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
	// 3)Thomas， Chris added cookies handling
	// Closes session when user logs out
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(@CookieValue(value = "EmailCookie", required = false) Cookie emailCookie,
			@CookieValue(value = "PasswordCookie", required = false) Cookie pwdCookie, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("BEGIN: /logout");
		// read Login Cookies
		ModelAndView nextPage = new ModelAndView("/AdminLogin");
		// Cookies exist
		if (emailCookie != null && pwdCookie != null) {
			System.out.println("有抓到Cookie - Successfuly read cookies");
			System.out.println("	Cookie email name, value, maxAge: " + emailCookie.getName() + ", "
					+ emailCookie.getValue() + ", " + emailCookie.getMaxAge());
			System.out.println("	Cookie pwd name, value, maxAge: " + pwdCookie.getName() + ", "
					+ pwdCookie.getValue() + ", " + pwdCookie.getMaxAge());
			EncryptString util = new EncryptString();
			EncodeHexString hexConvert = new EncodeHexString();

//			// decrypt Email Cookie				
//			byte[] emailCipher = hexConvert.HexStringToByteArray(emailCookie.getValue());
//			String email = util.decryptGoogleTinkAEAD(emailCipher, "OMGiloveyou");
			// decrypt Password Cookie
			byte[] pwdCipher = hexConvert.HexStringToByteArray(pwdCookie.getValue());
			String pwd = util.decryptGoogleTinkAEAD(pwdCipher, "OMGiloveyou");
			System.out.println("	Cookie pwd decrypted value (read only): " + pwd);

			// nextPage.addObject(EmailCookie);
			// nextPage.addObject(PasswordCookie);
		} else {
			System.out.println("No cookies at nextPage AdminLogin");
			// deleting cookies a second time just in case
			try {
				emailCookie.setMaxAge(0);
				pwdCookie.setMaxAge(0);
				response.addCookie(emailCookie);
				response.addCookie(pwdCookie);
			} catch (Exception e) {
				System.out.println("cant delete a second time");
			}
		}
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		if (session != null) {
			session.invalidate();
			System.out.println("Invalid Session!");
		}

		return nextPage;
	}
}
