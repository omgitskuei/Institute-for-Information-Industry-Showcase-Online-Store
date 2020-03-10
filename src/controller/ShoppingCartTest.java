package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.domain.AioCheckOutOneTime;
import ecpay.payment.integration.domain.InvoiceObj;
import model.order.OrderBeanService;
import model.orderDetails.OrderDetailsBeanService;
import model.product.ProductBeanService;
import model.user.UserBean;
import util.CheckSubstring;

/**
 * 1) 去Ajax符號且計算總金額 2) 還沒寫傳到哪
 * 
 * @author chienlin
 */
@Controller
@ResponseBody
@SessionAttributes(names={"form"})
public class ShoppingCartTest {

	private OrderBeanService oService;
	private OrderDetailsBeanService odService;
	private UserBean uEmail;
	private ProductBeanService pService;
	public HttpServletRequest request;
	
	public static AllInOne all;

	
	private static void initial() {
		all = new AllInOne(null);
	}

	@Autowired
	public ShoppingCartTest(HttpServletRequest reqeust, OrderBeanService oService, OrderDetailsBeanService odService, UserBean uEmail, ProductBeanService pService) {
		this.oService = oService;
		this.odService = odService;
		this.uEmail = uEmail;
		this.pService = pService;
		
	}
	
	public HashMap<String, Object> makeOrderItem (String name, String description, int amount, String currency, int quantity){
		
		HashMap<String, Object> orderItem = new HashMap<String, Object>();
		
		ShoppingCartTest shoppingCartItem = new ShoppingCartTest(request, oService, odService, uEmail, pService);
		
		
		orderItem.put("name", name);
		orderItem.put("description", description);
		orderItem.put("amount", amount);
		orderItem.put("currency", currency);
		orderItem.put("quantity", quantity);
		
		return null;
		
	}

	/**
	 * 信用卡線上刷卡code
	 */
//	@Override
//	public String genAioCheckOutOneTime1(OrderBean order, UserBean user) {
//		initial();
//		AioCheckOutOneTime obj = new AioCheckOutOneTime();// 產生信用卡一次付清訂單物件
//
//		Integer total = order.get();// 抓取總金額
//		Integer id = user.getMemberId();// 取得會員 利用會員Id跟日期 創建訂單編號
//
//		Date date = new Date();// 目前時間
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");// 設定日期格式 給訂單編號用
//		String dateString = sdf.format(date);// 進行轉換
//		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 設定日期格式 給日期用
//		String dateStringToMerchantTradeDate = sdf1.format(date);// 進行轉換
//		String merchantTradeNo = id.toString() + dateString;// 合併訂單編號
//
//		obj.setMerchantTradeNo(merchantTradeNo);// 合作特店交易編號
//		obj.setMerchantTradeDate(dateStringToMerchantTradeDate);// 訂單日期
//		obj.setTotalAmount(total.toString());// 繳費總額
//		obj.setTradeDesc("募資網 購物");// 設定TradeDesc 交易描述
//		obj.setItemName("募資網 購物");// 設定ItemName 商品名稱
//		obj.setReturnURL("http://localhost:8080/GitHubTest/");// 設定OrderResultURL Client端回傳付款結果網址
//		obj.setOrderResultURL("http://localhost:8080/GitHubTest/");// 設定OrderResultURL Client端回傳付款結果網址
//		obj.setNeedExtraPaidInfo("N");// 設定OrderResultURL Client端回傳付款結果網址
//		// obj.setRedeem("Y");
//		String form = all.aioCheckOut(obj, null);
//		return form;
//	}

	/**
	 * 目的：Ajax傳送購物車資料，要送給金流第三方API 進度：以可以刪除，所有特殊符號 刪除階段一： [{"ProductID":"1"
	 * "ProductName":"蔥" "ProductCount":"20" "ProductPrice":"20"} 刪除階段二：
	 * {"ProductID":"3" "ProductName":"大蒜" "ProductCount":"5" "ProductPrice":"193"}]
	 * 刪除階段三：且只選曲i%2!=0 [2, 洋蔥, 3, 18, 3, 大蒜, 3, 193, 6, 紅蘿蔔, 1, 16, 5, 山藥, 1, 100,
	 * 11, 馬鈴薯, 2, 29, 10, 綠竹筍, 1, 95, 8, 白蘿蔔, 1, 45, 7, 牛蒡, 2, 23]
	 * 
	 * @param importedData
	 */
	
	@PostMapping("/userAddToOrder")
	@ResponseBody
	public String userAddToOrder(@RequestParam(name = "dataArray", required = false) ArrayList<String> importedData, @CookieValue(name="totalCookie") String shoppingCartTotal, Model m) {
                
		
		        initial();
				System.out.println("原始JSON資料：" + importedData);

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
				// totalData目的是把總金額放進同一個arrayList，但現在先測試用importedData 因為發生NullPointException
				ArrayList<String> totalData = new ArrayList<String>();

				Integer sum = 0;
				// 刪除階段二：依造":"分割為兩筆資料並存入ArrayList: delimitedData
				for (int i = 0; i < importedData.size(); i++) {
					delimitedData.addAll(removeItem.delimitAtAnyChar((String) importedData.get(i), ":"));
				}
				// 刪除階段三：將ArrayList：delimitedData 只取出奇位數資料，並存入ArrayList: delimitedData
				for (int i = 0; i < delimitedData.size(); i++) {
					if (i % 2 != 0) {
						dataValues.add(delimitedData.get(i));

					}
				}
				delimitedData = removeItem.removeAnyChar(delimitedData, ":");
				dataValues = removeItem.removeAnyChar(dataValues, ":");

				// 處理過後的JSON：[ID：1, 名稱：蔥, 個數：24, 單價：20, 3, 大蒜, 5, 193, 5, 山藥, 2, 100]
				for (int i = 2; i < dataValues.size(); i += 4) {
					count.add(dataValues.get(i));
				}
				for (int i = 3; i < dataValues.size(); i += 4) {
					unitPrice.add(dataValues.get(i));
				}
				for (int i = 0; i < count.size(); i++) {
					sum += Integer.parseInt(count.get(i)) * Integer.parseInt(unitPrice.get(i));
				}

				System.out.println("處理過後的JSON：" + dataValues + "總金額：" + sum);
				
				importedData.clear();
				importedData.addAll(dataValues);
				importedData.add(sum.toString());
				System.out.println("最後的importedData: " + importedData);
				
				System.out.println("test");
				
				try {
					initial();
					AioCheckOutOneTime obj = new AioCheckOutOneTime();// 產生信用卡一次付清訂單物件
					
					// String total = importedData.get(importedData.size()-1);// 抓取總金額
					String id = "temporaryID";// 取得會員 利用會員Id跟日期 創建訂單編號

					Date date = new Date();// 目前時間
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");// 設定日期格式 給訂單編號用
					String dateString = sdf.format(date);// 進行轉換
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 設定日期格式 給日期用
					String dateStringToMerchantTradeDate = sdf1.format(date);// 進行轉換
					String merchantTradeNo = id.toString() + dateString;// 合併訂單編號
					
					System.out.println("有進綠界");
					obj.setMerchantTradeNo(dateString);
					obj.setMerchantTradeDate(dateStringToMerchantTradeDate);
					obj.setTotalAmount(shoppingCartTotal);
					obj.setTradeDesc("FarmVille一些商品");
					obj.setItemName("FarmVille一堆商品");
					obj.setReturnURL("http://localhost:8080/EEIT111FinalProject/front_intro.jsp");
					obj.setNeedExtraPaidInfo("N");
					String form = all.aioCheckOut(obj, null);
					
					m.addAttribute("form", form);


					System.out.println(form);

					return form;
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
				return "greenTest";
			}
			
}
