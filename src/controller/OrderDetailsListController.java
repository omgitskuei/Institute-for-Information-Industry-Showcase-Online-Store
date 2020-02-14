package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.order.OrderBean;
import model.order.OrderBeanDAO;
import model.orderDetails.OrderDetailsBean;
import model.orderDetails.OrderDetailsBeanDAO;
import model.user.UserBeanDAO;

@Controller
@SessionAttributes(names = {"userEmail"})
public class OrderDetailsListController {

	@Autowired
	OrderDetailsBeanDAO dao;
	
	@Autowired
	OrderBeanDAO dao2;
	
	@Autowired
	UserBeanDAO uDAO;
	
	public OrderDetailsListController() {
	}
	
	@RequestMapping(value = "/orderDetails", method = RequestMethod.GET)
	public String showForm(@SessionAttribute("userEmail") String uEmail, Model model, @RequestParam("orderID")int OrderID ) {
		
		//Order
		int userID = uDAO.selectUserIDByEmail(uEmail);
		List<OrderBean> detailsList2=dao2.selectOrdersByUserID(userID);
		model.addAttribute("detailsList2", detailsList2);
		//OrderDetails
		List<OrderDetailsBean> detailsList=dao.selectAllByOrderID(OrderID);
		model.addAttribute("detailsList", detailsList);

		return "OrderDetailsList";
	}
}
