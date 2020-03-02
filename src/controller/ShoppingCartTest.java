package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.order.OrderBeanService;
import model.orderDetails.OrderDetailsBeanService;

@Controller
public class ShoppingCartTest {

	private OrderBeanService oService;
	private OrderDetailsBeanService odService;
	
	@Autowired
	public ShoppingCartTest(OrderBeanService oService, OrderDetailsBeanService odService) {
		this.oService = oService;
		this.odService = odService;
	}
	
	@ResponseBody
	@RequestMapping("/userAddToOrder")
	public String userAddToOrder(@RequestParam(name = "dataArray") Object[] test){
		// System.out.println(oderDetail.getProductID());
		for(int i=0; i<test.length; i++) {
			System.out.println(test[i]);
		}
		// System.out.println(test[0]);
		System.out.println("test here!!");
		return "FU";
	}
	
	
}
