package controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import model.product.ProductBean;
import model.product.ProductBeanService;

@Controller
@SessionAttributes(names = { "searchBar" })
public class SearchBarController {
	
	private ProductBeanService pService;
	
	@Autowired
	public SearchBarController(ProductBeanService pService, HttpServletResponse response) {
		this.pService = pService;
	}
	
	@RequestMapping(path = "/searchBar", method = RequestMethod.POST)
	public String signUpStep1(
			@RequestParam(name = "searchBar") String searchBar, 
			Model nextPage) {
		System.out.println("BEGIN /searchBar");
		System.out.println("	User input: "+searchBar);
		
		// call fuzzyQuery model method (which is WIP) on name
		
		// call fuzzyQery model method on category
		
		// call fuzzyQery model method on description
		
		// get rid of the overlaps
		
		
		// if results size() is 0, assume input has typos
		// - look up dictionary for similar words to input
		
		// - rank similar words by index
		
		// - add top 3 similar words to empty results
		
		
		// return results
		
		
		
		// prelim testing:
		// selectAll products by productName and return products with name==input
		List<ProductBean> resultsAll = pService.selectAll();
		for(int index=0; index<resultsAll.size(); index++) {
			if (resultsAll.get(index).getProductName().equalsIgnoreCase(searchBar)) {
				System.out.println(resultsAll.get(index).getProductName());
				nextPage.addAttribute("name", resultsAll.get(index).getProductName());
				
				System.out.println(resultsAll.get(index).getProductPrice());
				nextPage.addAttribute("price", resultsAll.get(index).getProductPrice());
				
				System.out.println(resultsAll.get(index).getProductStock());
				nextPage.addAttribute("stock", resultsAll.get(index).getProductStock());
				
				System.out.println(resultsAll.get(index).getProductDescription());
				nextPage.addAttribute("description", resultsAll.get(index).getProductDescription());
				
				System.out.println(resultsAll.get(index).getProductTimestamp());
				nextPage.addAttribute("timestamp", resultsAll.get(index).getProductTimestamp());
				
				System.out.println(resultsAll.get(index).getProductCategory());
				nextPage.addAttribute("category", resultsAll.get(index).getProductCategory());
				
			}
		}
		return "product";
	}
}
