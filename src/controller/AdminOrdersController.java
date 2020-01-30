package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.order.OrderBeanService;

@Controller
@SessionAttributes(names= {"UserID"})
public class AdminOrdersController {

	private OrderBeanService service;
	
	@Autowired
	public AdminOrdersController() {
		
	}
	
	@RequestMapping(path="/controller.AdminOrdersController")
	public String processActionGET(@RequestParam(name="UserID")int UserID, Model nextPage) {
		
		return "OrdersManagement";
	}
}
