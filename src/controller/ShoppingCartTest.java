package controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.order.OrderBeanService;
import model.orderDetails.OrderDetailsBeanService;
import util.CheckSubstring;

@Controller
public class ShoppingCartTest {

	private OrderBeanService oService;
	private OrderDetailsBeanService odService;

	@Autowired
	public ShoppingCartTest(OrderBeanService oService, OrderDetailsBeanService odService) {
		this.oService = oService;
		this.odService = odService;
	}

	/**
	 * [{"ProductID":"1" "ProductName":"蔥" "ProductCount":"20" "ProductPrice":"20"}
	 * {"ProductID":"3" "ProductName":"大蒜" "ProductCount":"5" "ProductPrice":"193"}]
	 * 
	 * @param test
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userAddToOrder")
	public String userAddToOrder(@RequestParam(name = "dataArray") ArrayList<String> test) {
		// System.out.println(oderDetail.getProductID());
		
		
		System.out.println("原始JSON資料："+test);
		try {
			CheckSubstring removeItem = new CheckSubstring();
			test = removeItem.removeAnyChar(test, "[");
			test = removeItem.removeAnyChar(test, "]");
			test = removeItem.removeAnyChar(test, "{");
			test = removeItem.removeAnyChar(test, "}");
			test = removeItem.removeAnyChar(test, "\"");
			test = removeItem.removeAnyChar(test, " ");
			test = removeItem.removeAnyChar(test, ",");
			
			
			ArrayList<String> test2 = new ArrayList<String>();
			ArrayList<String> test3 = new ArrayList<String>();
			for(int i=0; i < test.size(); i++ ) {
				 test2.addAll(removeItem.delimitAtAnyChar((String)test.get(i), ":"));
			}
			
			for(int i=0; i < test2.size(); i++) {
				if(i%2!=0) {
					test3.add(test2.get(i));
				}
			}
			test2 = removeItem.removeAnyChar(test2, ":");
			test3 = removeItem.removeAnyChar(test3, ":");
			
			System.out.println("處理過後的JSON："+test3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		return "FU";
	}

}
