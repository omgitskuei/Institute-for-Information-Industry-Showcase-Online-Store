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

import model.orderDetails.OrderDetailsBean;
import model.orderDetails.OrderDetailsBeanDAO;

@Controller
@SessionAttributes(names = {"userEmail"})
public class OrderDetailsListController {

	@Autowired
	OrderDetailsBeanDAO dao;
	public OrderDetailsListController() {
	}
	
	@RequestMapping(value = "/orderDetails", method = RequestMethod.GET)
	public String showForm(@SessionAttribute("userEmail") String uEmail, Model model, @RequestParam("orderID")int OrderID ) {
		System.out.println("Directing to AdminInventory");
		
		List<OrderDetailsBean> detailsList=dao.selectAllByOrderID(OrderID);
		model.addAttribute("detailsList", detailsList);
		return "OrderDetailsList";
	}

}
