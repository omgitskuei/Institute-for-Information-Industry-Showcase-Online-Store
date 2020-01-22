package controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.userBean.UserBean;
import model.userBean.UserBeanDAO;
import model.userBean.UserBeanService;

@Controller
@SessionAttributes(names = {"user", "pwd", "errors"})
public class AdminLoginController {

	// Which Service am I using for this Controller?
	//private UserBeanDAO DAO;
	private UserBeanService service;

	@Autowired
	public AdminLoginController(UserBeanService service) {
		//this.DAO = thisDAO;
		this.service = service;
	}
	
	// URL address for this controller, method POST/GET, what data fields
	@RequestMapping(path="/AdminLoginController.controller", method = RequestMethod.POST)
	public String processAction(@RequestParam(name = "userEmail") String userEmail, 
			                    @RequestParam(name = "userPwd") String userPwd,
			                    Model m) {
		
		System.out.println("BEGIN: /AdminLoginController.controller");
		
		Map<String, String> errors = new HashMap<String, String>();
		m.addAttribute("errors", errors);
		
		// Check for user not inputting anything
		if(userEmail==null || userEmail.length()==0) {
			errors.put("emailError", "Email is required");
			System.out.println("Email error");
		}
		// Check for user not inputting anything
		if(userPwd==null || userPwd.length()==0) {
			errors.put("pwdError", "Password is required");
			System.out.println("Password error");
		}
		// There are error messages, return to previous page with error messages
		if(errors!=null && !errors.isEmpty()) {
			System.out.println("Returning to loginSystem with error messages");
			return "loginSystem2";
		}
		
		// Add variables for hqlString on UserBeanDAO.selectUser
		m.addAttribute("userEmail", userEmail);
		m.addAttribute("userPwd", userPwd);
		// Do UserBeanService.select(UserBean a UserBean with userEmail, userPwd)
		UserBean foundBean = service.select(new UserBean(userEmail, userPwd, 1));
		System.out.println("THIS IS FOUNDBEAN: "+foundBean);
		
		if(foundBean != null) {
			return "loginSuccess";
		}		
		
		errors.put("msgError", "username or password is not correct");
		return "loginSystem2";
	}

}