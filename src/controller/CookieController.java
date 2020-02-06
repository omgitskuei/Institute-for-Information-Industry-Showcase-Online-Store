//package controller;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import model.user.UserBean;

//@Controller
//public class CookieController {
//
//	@RequestMapping("/cookie")
//	public String createCookie(@RequestParam(required=false, defaultValue="0")int state, UserBean user, HttpServletResponse response, 
//			HttpServletRequest request, ModelMap map) {
//		
//		if(user.getUserEmail() && user.getUserPwd()) {
//			// 如果選擇記住密碼，則創建cookie，並將帳號密碼給cookie
//			if(state==1) {
//				// 創建cookie
//				Cookie ck = new Cookie(user.getUserEmail(), user.getUserPwd());
//				// 設置Cookie有效時間，單位為秒
//				ck.setMaxAge(60*60*24);
//				// 設置Cookie有效範圍,/為全部的路徑
//				ck.setPath("/");
//				response.addCookie(ck);
//				System.out.println("有抓到cookie");
//			}else {
//			// 如果沒有選擇記住密碼，則將以記住密碼的Cookie失效，有效時間設為0
//				Cookie[] cookies = request.getCookies();
//				for(Cookie cookie: cookies) X{
//					if(cookie.getName().equals(user.getUserEmail())) {
//						cookie.setMaxAge(0);
//						cookie.setPath("/");
//						response.addCookie(cookie);
//					}
//				}
//			}
//			return "AdminIndex";
//		}else {
//			map.put("ts", "使用者名稱或密碼錯誤");
//			return "AdminLogin";
//		}
//	}
//	
//}