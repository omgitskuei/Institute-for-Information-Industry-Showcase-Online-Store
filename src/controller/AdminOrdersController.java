package controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.order.OrderBean;
import model.order.OrderBeanDAO;
import model.order.OrderBeanService;

@Controller
@SessionAttributes()
public class AdminOrdersController {

	@Autowired
	OrderBeanDAO dao;
	
	
	@RequestMapping(path="/controller.AdminOrdersController")
	public String orderInfo(Model m) {
		
		List<OrderBean> orderList=dao.selectAll();
		m.addAttribute("orderList", orderList);
		
		return "OrdersManagement";
	}
}
