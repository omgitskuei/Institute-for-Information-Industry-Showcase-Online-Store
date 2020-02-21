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
		
		// call fuzzyQuery model method on name
		// call fuzzyQery model method on category
		// call fuzzyQery model method on description
		List<ProductBean> fuzzyResults = pService.selectFuzzy(searchBar, searchBar, searchBar);
		// 
		// get rid of the overlaps
		// if results size() is 0, assume input has typos
		// look up dictionary for similar words to input
		// Reorder the fuzzyResults by relevance; search result
		// rank similar words by index
		// add top 3 similar words to empty results
		
		
		// return results
		
		for(int index=0; index<fuzzyResults.size(); index++) {
			
				System.out.println(fuzzyResults.get(index).getProductName());
				nextPage.addAttribute("name", fuzzyResults.get(index).getProductName());
				
				System.out.println(fuzzyResults.get(index).getProductPrice());
				nextPage.addAttribute("price", fuzzyResults.get(index).getProductPrice());
				
				System.out.println(fuzzyResults.get(index).getProductStock());
				nextPage.addAttribute("stock", fuzzyResults.get(index).getProductStock());
				
				System.out.println(fuzzyResults.get(index).getProductDescription());
				nextPage.addAttribute("description", fuzzyResults.get(index).getProductDescription());
				
				System.out.println(fuzzyResults.get(index).getProductTimestamp());
				nextPage.addAttribute("timestamp", fuzzyResults.get(index).getProductTimestamp());
				
				System.out.println(fuzzyResults.get(index).getProductCategory());
				nextPage.addAttribute("category", fuzzyResults.get(index).getProductCategory());
			
		}
		return "product";
	}
}
