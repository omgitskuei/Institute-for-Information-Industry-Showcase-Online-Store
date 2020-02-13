package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.user.UserBean;

@Controller
@SessionAttributes(names = { "newEmail", "newPwd", "repeatPwd", "userType" })
public class AdminNewUserController {

	@Autowired
	public AdminNewUserController() {
		
	}

	// URL address for this controller, method POST/GET, what data fields
	@RequestMapping(path = "/controller.AdminNewUserController", method = RequestMethod.POST)
	public String processAction(@RequestParam(name = "newEmail", required = true) String uEmail,
			@RequestParam(name = "newPwd", required = true) String uPwd,
			@RequestParam(name = "repeatPwd", required = true) String rPwd,
			@RequestParam(name = "userType", required = true) String userType, // 0 for normal user, 1 for admin
			Model nextPage) {
		System.out.println("BEGIN: /controller.AdminNewUserController");

		// Turn user input into a persistence bean
		UserBean bean = new UserBean();
		bean.setUserEmail(uEmail);
		bean.setUserPwd(uPwd);
		if (userType.equals("管理者")) {
			bean.setAdmin(1);
		} else if (userType.equals("一般使用者")) {
			bean.setAdmin(0);
		} else {
			System.out.println("New User failed, Invalid user type");
		}
		System.out.println("FINISH: /controller.AdminNewUserController");
		System.out.println("Returning to AdminIndex");
		return "AdminIndex";

	}

}
