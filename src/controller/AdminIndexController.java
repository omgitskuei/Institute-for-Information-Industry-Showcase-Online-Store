package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.mailingList.MailBean;
import model.mailingList.MailBeanService;
import util.EmailUsers;

@Controller
@SessionAttributes()
public class AdminIndexController {

	private MailBeanService mService;

	@Autowired
	public AdminIndexController(MailBeanService m) {
		this.mService = m;
	}

	// 1)這個應該沒用了，再請確認一下
	// 2)已完成
	// 3)Thomas
	@RequestMapping(path = "/controller.AdminIndexController", method = RequestMethod.POST)
	public String processAction(
			Model nextPage) {
		System.out.println("Directing to Adminindex");
		return "AdminIndex";
	}

	@RequestMapping(path = "/sendNewsletter", method = RequestMethod.POST)
	public String sendNewsletter(
			@RequestParam(name = "title") String title,
			@RequestParam(name = "messageBody") String messageBody, 
			Model nextPage) {
		System.out.println("BEGIN: AdminIndexController.java /sendNewsletter");
		EmailUsers emailer = new EmailUsers();
		List<MailBean> results = mService.selectAllMail();
		for (int index = 0; index < results.size(); index++) {
			emailer.sendOutNewsletter(title, messageBody, results.get(index).getEmail());
		}
		System.out.println("Directing from /sendNewsletter to Adminindex");
		return "AdminIndex";
	}
}
