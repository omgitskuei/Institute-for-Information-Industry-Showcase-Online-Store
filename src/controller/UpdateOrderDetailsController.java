package controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.orderDetails.OrderDetailsBean;
import model.orderDetails.OrderDetailsBeanService;

//1)控制OrderDetails表單更新
//2)寫完了
//3)忠城寫的
@Controller
@SessionAttributes
@RequestMapping("/orderDetails")
public class UpdateOrderDetailsController {

	@Autowired
	private OrderDetailsBeanService orderDetailsService;

//	@GetMapping("/list")
//	public String listOrderDetailss(Model m) {
//		List<OrderDetailsBean> theOrderDetails = orderDetailsService.selectAll();
//		m.addAttribute("orderDetails", theOrderDetails);
//		return "AdminViewAllUser";
//	}

	@PostMapping("/saveOrderDetails")
	public String saveOrderDetails(@ModelAttribute("orderDetails") @Valid OrderDetailsBean theOrderDetails) {
		orderDetailsService.insert(theOrderDetails);
		return "redirect:/orders";
	}

	@GetMapping("/updateForm")
	public String showFormForUpdate(@RequestParam("orderID") int orderID, Model m) {
		if (orderID == 0) {
			OrderDetailsBean theorderDetails = orderDetailsService.getOrderDetails(orderID);
			m.addAttribute("orderDetails", theorderDetails);
			return "DetailAdding";
		} else {
			OrderDetailsBean theorderDetails = new OrderDetailsBean();
			m.addAttribute("orderDetails", theorderDetails);
			return "DetailAdding";
		}
	}
//
//	@GetMapping("/delete")
//	public String deleteOrderDetails(@RequestParam("orderDetailsID") int orderDetailsID) {
//		orderDetailsService.deleteOrderDetails(orderDetailsID);
//		return "redirect:/orderDetailss";
//	}

}
