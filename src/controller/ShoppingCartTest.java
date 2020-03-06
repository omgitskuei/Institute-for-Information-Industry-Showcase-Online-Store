package controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;
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
	 * @param importedData
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userAddToOrder")
	public String userAddToOrder(@RequestParam(name = "dataArray") ArrayList<String> importedData) {
		// System.out.println(oderDetail.getProductID());
		
		
		System.out.println("原始JSON資料："+importedData);
		try {
			CheckSubstring removeItem = new CheckSubstring();
			importedData = removeItem.removeAnyChar(importedData, "[");
			importedData = removeItem.removeAnyChar(importedData, "]");
			importedData = removeItem.removeAnyChar(importedData, "{");
			importedData = removeItem.removeAnyChar(importedData, "}");
			importedData = removeItem.removeAnyChar(importedData, "\"");
			importedData = removeItem.removeAnyChar(importedData, " ");
			importedData = removeItem.removeAnyChar(importedData, ",");
			
			
			ArrayList<String> delimitedData = new ArrayList<String>();
			ArrayList<String> dataValues = new ArrayList<String>();
			ArrayList<String> unitPrice = new ArrayList<String>();
			ArrayList<String> count = new ArrayList<String>();
			int sum = 0;
			for(int i=0; i < importedData.size(); i++ ) {
				 delimitedData.addAll(removeItem.delimitAtAnyChar((String)importedData.get(i), ":"));
			}
			
			for(int i=0; i < delimitedData.size(); i++) {
				if(i%2!=0) {
					dataValues.add(delimitedData.get(i));
					
				}
			}
			delimitedData = removeItem.removeAnyChar(delimitedData, ":");
			dataValues = removeItem.removeAnyChar(dataValues, ":");
			// 處理過後的JSON：[ID 1, 蔥, 個數：24, 單價：20, 3, 大蒜, 5, 193, 5, 山藥, 2, 100]
			for(int i = 2; i < dataValues.size(); i+=4) {
				count.add(dataValues.get(i));
			}
			for(int i = 3; i < dataValues.size(); i+=4) {
				unitPrice.add(dataValues.get(i));
			}
			for(int i = 0; i < count.size(); i++) {
				sum += Integer.parseInt(count.get(i)) * Integer.parseInt(unitPrice.get(i)); 	
			}
			
			
			
			System.out.println("處理過後的JSON："+dataValues + "總金額：" + sum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		
//		String merchantTradeNo = "9849398987";//訂單編號
//		String merchantTradeDate = "2017/01/01 08:05:23";//購買日期
//		String totalAmount = "50";//金額
//		String tradeDesc = "test Description";//交易描述 null
//		String itemName = "TestItem";//商品名稱
//		String returnURL = "http://211.23.128.214:5000";//付款完成路徑
//		String needExtraPaidInfo = "N";//是否需要額外付款資訊
		
		
		
		
		// AioCheckOutOneTime obj = new AioCheckOutOneTime();
		// 到綠界
		return "GReen world wow placeholder";
	}

}
