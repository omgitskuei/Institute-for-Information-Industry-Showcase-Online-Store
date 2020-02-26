package controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.user.UserBean;
import model.user.UserBeanService;
import util.CheckSubstring;
import util.EncodeHexString;
import util.EncryptString;

@Controller
@SessionAttributes(names = { "userEmail", "userPwd", "rememberMe" })
public class UserLoginController {

	// Local fields
	private UserBeanService uService;
	private HttpServletResponse response;
	private EncryptString util = new EncryptString();
	private EncodeHexString hexConvert = new EncodeHexString();

	// Constructors
	@Autowired
	public UserLoginController(UserBeanService uService, HttpServletResponse response) {
		this.uService = uService;
		this.response = response;
	}

	// Methods
	@RequestMapping("/writeUserLoginCookie")
	private String writeUserLoginCookie(String email, String pwd, Model nextPage, HttpServletResponse response) {
		System.out.println("BEGIN: /writeAdminLoginCookie");
		System.out.println("	passed email: " + email);
		Cookie emailCookie = new Cookie("UserEmailCookie", email);
		System.out.println("	passed pwd: " + pwd);
		// Encrypt pwd before writing to a cookie
		byte[] cipherPwd = util.encryptGoogleTinkAEAD(pwd, "OMGiloveyou");
		pwd = hexConvert.byteArrayToHexString(cipherPwd);
		System.out.println("			cookie pwd(encrpyted): " + pwd);
		// Write encrypted pwd cookie
		Cookie pwdCookie = new Cookie("UserPasswordCookie", pwd);

		emailCookie.setMaxAge(60 * 60);
		pwdCookie.setMaxAge(60 * 60);
		response.addCookie(emailCookie);
		response.addCookie(pwdCookie);
		System.out.println("cookies ["+emailCookie.getName()+", "+pwdCookie.getName()+"] added to response");

		return "writeUserLoginCookie";
	}

	@RequestMapping(path = "userForgotPwd", method = RequestMethod.POST)
	public String userForgotPwd(
			Model nextPage,
			@RequestParam(name="userEmail") String userEmail) {
		System.out.println("BEGIN /userForgotPwd");
		// if there are errors, return to previous page, else query the input
		String result = validateEmailreturnErrors(userEmail);
		System.out.println("	validate emamil result: "+result);
		if ( !(result.equals("VALID EMAIL")) ) {
			System.out.println("		Adding error message to nextPage model");
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("validateError", result);
			nextPage.addAttribute("errors", errors);
			System.out.println("	returning to front_forgetpwd.jsp");
			System.out.println("FINISH /userForgotPwd");
			return "front_forgetpwd";
		} else {
			
			;
		}
		nextPage.addAttribute("", "");
		return "";
	}
	
	public String validateEmailreturnErrors(String email) {
		try {
			// email cant be empty, and must be longer than 5 (x@x.x)
			if (email != null && email.length() > 5) {
				// email must have "@"
				if (email.contains("@")) {
					// Partition email string based on email syntax; localpart@domain
					String localpart = email.substring(0, email.indexOf("@"));
					String domain = email.substring(email.indexOf("@") + 1, email.length());

					// Localpart and Domain each must not exceed 64 characters
					if (localpart.length() <= 64 && domain.length() <= 63) {
						// Create empty ArrayList for storing where "." appear in email String
						ArrayList<Integer> dotIndexes = new ArrayList<Integer>();
						// Create counter for "@"
						int countAt = 0;
						// Create flag for spaces, false will fail;
						boolean noSpaces = true;
						// Checking each character of email string for space, @, .
						for (int index = 0; index < email.length(); index++) {
							// Split email into substrings
							String substring = email.substring(index, index + 1);
							// Count how many "@"
							if (substring.equals("@")) {
								countAt++;
							}
							// Check if there are any spaces in the email
							if (substring.equals(" ")) {
								noSpaces = false;
							}
							// Note where "." dots appear in the email, store into ArrayList
							if (substring.equals(".")) {
								dotIndexes.add(index);
							}
						}
						// A valid email must have "@" and can only have one "@"
						if (countAt == 1) {
							// A valid email must have no spaces
							if (noSpaces) {
								// A valid email cannot begin or end on "."
								if (!(dotIndexes.contains(0) || dotIndexes.contains(email.length() - 1))) {
									// Email @ sign can't be next to "."
									boolean atNotNextToDot = true;
									for (int index = 0; index < dotIndexes.size(); index++) {
										// if "." is to the left or right of the "@", fail
										if (email.indexOf("@") - dotIndexes.get(index) == -1
												|| email.indexOf("@") - dotIndexes.get(index) == 1) {
											atNotNextToDot = false;
										}
									}
									if (atNotNextToDot) {
										// Domain must comply with LDH rule (letters, digits, hyphen)
										CheckSubstring util = new CheckSubstring();
										if (util.countSpecialCharacters(domain) == 0) {
											// Domain must contain one "."
											if (domain.contains(".")) {
												boolean noConsecutiveDotsFlag = true;
												// Domain cannot have any consecutive dots
												for (int index = 0; index < dotIndexes.size() - 1; index++) {
													if ((dotIndexes.get(index + 1) - dotIndexes.get(index)) == 1) {
														noConsecutiveDotsFlag = false;
													}
												}
												if (noConsecutiveDotsFlag) {
													return "VALID EMAIL";
												}
											} else {
												return "Invalid Input: Email domain missing . character";
											}
										} else {
											return "Invalid Input: Email may only use letters, digits, hyphen";
										}
									} else {
										return "Invalid Input: Email cannot have a . character next to a @";
									}
								} else {
									return "Invalid Input: Email may not end on a . character";
								}
							} else {
								return "Invalid Input: Email may not use any Spaces";
							}
						} else {
							return "Invalid Input: Email may only have one @ character";
						}
					} else {
						return "Invalid Input: Email localpart and domain must be under 64 characters";
					}
				} else {
					return "Invalid Input: Email must contain @";
				}
			} else {
				return "Invalid Input: Email is too short";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "EXCEPTION";
	}
	
	// Methods > User sign up account
	@RequestMapping(path = "/userSignIn", method = RequestMethod.POST)
	public String userSignIn(@SessionAttribute("userEmail") String dEmail, // display 用的
			@RequestParam(name = "userEmail") String uEmail,
			@RequestParam(name = "userPwd") String uPwd,
			@RequestParam(name = "rememberMe", required = false, defaultValue = "false") boolean remMe,
			@RequestParam(name = "g-recaptcha-response", required = false) boolean recaptcha,
			@CookieValue(value = "UserEmailCookie", required = false) Cookie emailCookie,
			@CookieValue(value = "UserPasswordCookie", required = false) Cookie pwdCookie, Model nextPage) {

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
			System.out.println("		No Useremailcookie and Userpwdcookie");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Check for empty input
		if ((uEmail == null) || (uPwd.length() < 8 || uPwd == null)) {
			// One of the User's input is empty, return to previous page with error messages
			System.out.println("	USER INPUT INVALID: Returning to AdminLogin");
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
			return "front_login";
		} else {
			UserBean bean = new UserBean();
			if (emailCookie != null && pwdCookie != null) {
				bean.setUserEmail(emailCookie.getValue());
				byte[] pwdCipher = hexConvert.HexStringToByteArray(pwdCookie.getValue());
				String pwd = util.decryptGoogleTinkAEAD(pwdCipher, "OMGiloveyou");
				bean.setUserPwd(pwd);
				bean.setAdmin(0);
			} else {
				bean.setUserEmail(uEmail);
				bean.setUserPwd(uPwd);
				bean.setAdmin(0);
			}
			// Use bean to use UserBeanService uService
			UserBean results = uService.checkLogin(bean);
			System.out.println("	Service.select(bean) RESULTS: ");
			if (results == null || results.getUserID() == 0) {
				if (results == null || results.getUserID() == 0) {
					System.out.println("USER NOT FOUND: Returning to AdminLogin");
				} 
				// Match not found, return to previous page to login again
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("notFoundError", "Incorrect Email or Password");
				nextPage.addAttribute("errors", errors);
				return "front_login";
			} else {
				// If match found, return
				// EEIT111FinalProject/WebContent/WEB-INF/pages/AdminDashboard
				System.out.println("		Class = " + results.getClass());
				System.out.println("		User ID = " + results.getUserID());
				System.out.println("		Email = " + results.getUserEmail());
				System.out.println("		Password = " + results.getUserPwd());
				System.out.println("		Admin = " + results.getAdmin());
				// Write a Cookie storing email so user don't need to enter email next time
				// Write a Cookie storing email so user don't need to enter email next time
				if (remMe == true) {
					System.out.println("	MAKING COOKIE");
					writeUserLoginCookie(bean.getUserEmail(), bean.getUserPwd(), nextPage, response);
				} else {
					System.out.println("	Remember Me == false, DELETING OLD COOKIES");
					Cookie cookie = new Cookie("UserEmailCookie", "");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					cookie = new Cookie("UserPasswordCookie", "");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				System.out.println("AUTHENTICATED: Directing to front_intro_loginSuccess");
				nextPage.addAttribute("userEmail", uEmail);
				nextPage.addAttribute("loggedInUserEmail", uEmail);
				nextPage.addAttribute("loggedInUserPwd", uPwd);
				return "front_intro_loginSuccess";
			}
		}
	}
}
