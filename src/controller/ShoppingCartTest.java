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

/**
 * 1) 去Ajax符號且計算總金額
 * 2) 還沒寫傳到哪
 * @author chienlin
 */
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
			ArrayList<String> uPrice = new ArrayList<String>();
			ArrayList<String> count = new ArrayList<String>();
			int sum = 0;
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
			// 處理過後的JSON：[1, 蔥, 個數：24, 單價：20, 3, 大蒜, 5, 193, 5, 山藥, 2, 100]
			for(int i = 2; i < test3.size(); i+=4) {
				count.add(test3.get(i));
			}
			for(int i = 3; i < test3.size(); i+=4) {
				uPrice.add(test3.get(i));
			}
			for(int i = 0; i < count.size(); i++) {
				sum += Integer.parseInt(count.get(i)) * Integer.parseInt(uPrice.get(i)); 	
			}
			
			
			
			System.out.println("處理過後的JSON："+test3 + "總金額：" + sum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// 到綠界
		return "TEST";
	}

}
