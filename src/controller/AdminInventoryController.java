package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.product.ProductBean;
import model.product.ProductBeanDAO;

@Controller
@SessionAttributes(names = {"userEmail"})
public class AdminInventoryController {

	@Autowired
	ProductBeanDAO dao;
	
	public AdminInventoryController() {
	}
	
	@RequestMapping(value = "/inventories", method = RequestMethod.GET)
	public String showForm(Model model) {
		System.out.println("Directing to AdminInventory");
		List<ProductBean> Inventorylist=dao.selectAll();
		model.addAttribute("InventoryList", Inventorylist);
		return "AdminInventory";
	}

//	// URL address for this controller, method POST/GET, what data fields
//	@RequestMapping(path = "/controller.AdminInventoryController", method = RequestMethod.POST)
//	public String processAction(@SessionAttribute("userEmail") String uEmail, Model nextPage) {
//		
//		List<ProductBean> list= dao.selectAll();
//		nextPage.addAttribute("list",list);
//		System.out.println("Directing to AdminInventory");
////		nextPage.addAttribute("userEmail", uEmail);
//		return "AdminInventory";
//	}



}
