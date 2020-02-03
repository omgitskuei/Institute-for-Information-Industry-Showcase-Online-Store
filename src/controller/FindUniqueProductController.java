package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes()
public class FindUniqueProductController {
	public FindUniqueProductController() {
		
	}
	
	@RequestMapping(path="/controller.FindUniqueProductController", method=RequestMethod.GET)
	public String processAction(@SessionAttribute("productCategory")String ProductCategory, Model nextPage) {
		nextPage.getAttribute("ProductName", productName);
		System.out.println("Directing to Product");
		return "UniqueProduct";
	}
}
