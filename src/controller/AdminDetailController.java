package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

// SessionAttributes應該還要抓資料庫的庫存欄位名稱導到JSP當標題
@Controller
@SessionAttributes(names = {"userEmail"})
public class AdminDetailController {

	@Autowired
	public AdminDetailController() {
		
	}
	
	// URL address for the controller, method POST/GET, what data
	@RequestMapping(path = "/controller.AdminInventoryDetailController", method = RequestMethod.POST)
	public String proccessActionInventoryDetail(@SessionAttribute("userEmail") String uEmail) {
		System.out.println("Directing to InventoryDetail");
		return "AdminInventoryDetail";
	}
	
	@RequestMapping(path = "/controller.AdminOrderDetailController", method = RequestMethod.POST)
	public String proccessActionOrderDetail(@SessionAttribute("userEmail") String uEmail) {
		System.out.println("Directing to OrderDetail");
		return "AdminOrderDetail";
	}
	
}
