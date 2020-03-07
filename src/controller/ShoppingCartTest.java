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
	 * 目的：Ajax傳送購物車資料，要送給金流第三方API
	 * 進度：以可以刪除，所有特殊符號
	 * 刪除階段一：
	 * [{"ProductID":"1" "ProductName":"蔥" "ProductCount":"20" "ProductPrice":"20"}
	 * 刪除階段二：
	 * {"ProductID":"3" "ProductName":"大蒜" "ProductCount":"5" "ProductPrice":"193"}]
	 * 刪除階段三：且只選曲i%2!=0
	 * [2, 洋蔥, 3, 18, 3, 大蒜, 3, 193, 6, 紅蘿蔔, 1, 16, 5, 山藥, 1, 100, 11, 馬鈴薯, 2, 29, 10, 綠竹筍, 1, 95, 8, 白蘿蔔, 1, 45, 7, 牛蒡, 2, 23]
	 * 
	 * @param importedData
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userAddToOrder")
	public String userAddToOrder(@RequestParam(name = "dataArray") ArrayList<String> importedData) {
		
		
		System.out.println("原始JSON資料："+importedData);
		try {
			
			// 刪除階段一：刪除以下符號並傳給ArrayList: importedData
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
			// 刪除階段二：依造":"分割為兩筆資料並存入ArrayList: delimitedData
			for(int i=0; i < importedData.size(); i++ ) {
				 delimitedData.addAll(removeItem.delimitAtAnyChar((String)importedData.get(i), ":"));
			}
			// 刪除階段三：將ArrayList：delimitedData 只取出奇位數資料，並存入ArrayList: delimitedData
			for(int i=0; i < delimitedData.size(); i++) {
				if(i%2!=0) {
					dataValues.add(delimitedData.get(i));
					
				}
			}
			delimitedData = removeItem.removeAnyChar(delimitedData, ":");
			dataValues = removeItem.removeAnyChar(dataValues, ":");
			
			// 處理過後的JSON：[ID：1, 名稱：蔥, 個數：24, 單價：20, 3, 大蒜, 5, 193, 5, 山藥, 2, 100]
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
	
		
		// TODO 處理導到綠界過程...
		
//		String merchantTradeNo = "9849398987";//訂單編號
//		String merchantTradeDate = "2017/01/01 08:05:23";//購買日期
//		String totalAmount = "50";//金額
//		String tradeDesc = "test Description";//交易描述 null
//		String itemName = "TestItem";//商品名稱
//		String returnURL = "http://211.23.128.214:5000";//付款完成路徑
//		String needExtraPaidInfo = "N";//是否需要額外付款資訊
		
		// 導到綠界
		return "GReen world wow placeholder";
	}

}
