package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sun.xml.bind.CycleRecoverable.Context;

import model.order.OrderBean;
import model.order.OrderBeanService;
import model.product.ProductBean;
import model.product.ProductBeanService;
import model.profile.ProfileBean;
import model.profile.ProfileBeanService;
import model.user.UserBean;
import model.user.UserBeanService;

@Controller
@SessionAttributes(names = { "searchBar" })
public class SearchBarController {
	// fields
	private ProductBeanService pService;
	private UserBeanService uService;
	private OrderBeanService oService;
	private ProfileBeanService profileService;
	private HttpServletResponse response;
	// constructor
	@Autowired
	public SearchBarController(OrderBeanService oService, ProductBeanService pService, UserBeanService uService, ProfileBeanService profileService, HttpServletResponse response) {
		this.pService = pService;
		this.uService = uService;
		this.oService = oService;
		this.profileService = profileService;
		this.response = response;
	}
	// methods
	@RequestMapping(path = "/searchBarProducts", method = RequestMethod.POST)
	public String searchBarProducts(
			HttpServletRequest request,
			@RequestParam(name = "searchBar") String searchBar, 
			@RequestParam(name = "selectCategory") String selectCategory,
			Model nextPage) {
//		String s = request.getParameter("selectCategory");
//		System.out.println(s);
		
		System.out.println("BEGIN: /searchBarProducts");
		System.out.println("	User input (searchBar): [" + searchBar+"]");
		System.out.println("	User input (selectCategory): [" + selectCategory+"]");
		String category = "";
		List<ProductBean> fuzzyResults = new ArrayList<ProductBean>();
		if (selectCategory.equals("蔥類")) {
			category = "蔥類";
			fuzzyResults = pService.selectFuzzy(searchBar, category);
		} else if (selectCategory.equals("根菜類")) {
			category = "根菜類";
			fuzzyResults = pService.selectFuzzy(searchBar, category);
		} else if (selectCategory.equals("莖菜類")) {
			category = "莖菜類";
			fuzzyResults = pService.selectFuzzy(searchBar, category);
		} else if (selectCategory.equals("瓜果類")) {
			category = "瓜果類";
			fuzzyResults = pService.selectFuzzy(searchBar, category);
		} else {
			category = searchBar;
			fuzzyResults = pService.selectFuzzy(searchBar, searchBar, searchBar);
		}
		System.out.println("	QUERY RESULTS:");
		for (int index = 0; index < fuzzyResults.size(); index++) {
			System.out.println("	List index #"+index);
			System.out.println("		id: " + fuzzyResults.get(index).getProductID());
			System.out.println("		name: " + fuzzyResults.get(index).getProductName());
			System.out.println("		price: " + fuzzyResults.get(index).getProductPrice());
			System.out.println("		stock: " + fuzzyResults.get(index).getProductStock());

			System.out.println("		timestamp: " + fuzzyResults.get(index).getProductTimestamp());
			System.out.println("		category: " + fuzzyResults.get(index).getProductCategory());
		}
		nextPage.addAttribute("SearchResults", fuzzyResults);
		
		List<ProductBean> totalP = pService.selectAll();
		int totalPCount = totalP.size();
		String totalPCountString = Integer.toString(totalPCount);
		System.out.println("TOTAL PRODUCT COUNT: "+totalPCount);
		
		List<OrderBean> totalO = oService.selectAll();
		int totalOCount = totalO.size();
		String totalOCountString = Integer.toString(totalOCount);
		System.out.println("TOTAL ORDER COUNT: "+totalOCount);
		
		List<UserBean> totalU = uService.selectFuzzy("");
		int totalUCount = totalU.size();
		String totalUCountString = Integer.toString(totalUCount);
		System.out.println("TOTAL USER COUNT: "+totalUCount);
		
		Map<String, String> dataNum = new HashMap<String, String>();
		dataNum.put("product", totalPCountString);
		dataNum.put("order", totalOCountString);
		dataNum.put("user", totalUCountString);
		
		nextPage.addAttribute("dataNum", dataNum);
		
		System.out.println("FINISH: /searchBarProducts");
		return "AdminIndex";
	}
	
	@RequestMapping(path = "/AdminProduct/searchInventoryProducts", method = RequestMethod.POST)
	public String searchInventoryProducts(
			HttpServletRequest request,
			@RequestParam(name = "searchBar") String searchBar, 
			@RequestParam(name = "selectCategory") String selectCategory,
			Model nextPage) {
//		String s = request.getParameter("selectCategory");
//		System.out.println(s);
		
		System.out.println("BEGIN: /searchInventoryProducts");
		System.out.println("	User input (searchBar): [" + searchBar+"]");
		System.out.println("	User input (selectCategory): [" + selectCategory+"]");
		String category = "";
		List<ProductBean> fuzzyResults = new ArrayList<ProductBean>();
		if (selectCategory.equals("蔥類")) {
			category = "蔥類";
			fuzzyResults = pService.selectFuzzy(searchBar, category);
		} else if (selectCategory.equals("根菜類")) {
			category = "根菜類";
			fuzzyResults = pService.selectFuzzy(searchBar, category);
		} else if (selectCategory.equals("莖菜類")) {
			category = "莖菜類";
			fuzzyResults = pService.selectFuzzy(searchBar, category);
		} else if (selectCategory.equals("瓜果類")) {
			category = "瓜果類";
			fuzzyResults = pService.selectFuzzy(searchBar, category);
		} else {
			category = searchBar;
			fuzzyResults = pService.selectFuzzy(searchBar, searchBar, searchBar);
		}
		System.out.println("	QUERY RESULTS:");
		for (int index = 0; index < fuzzyResults.size(); index++) {
			System.out.println("	List index #"+index);
			System.out.println("		id: " + fuzzyResults.get(index).getProductID());
			System.out.println("		name: " + fuzzyResults.get(index).getProductName());
			System.out.println("		price: " + fuzzyResults.get(index).getProductPrice());
			System.out.println("		stock: " + fuzzyResults.get(index).getProductStock());
			System.out.println("		timestamp: " + fuzzyResults.get(index).getProductTimestamp());
			System.out.println("		category: " + fuzzyResults.get(index).getProductCategory());
		}
		nextPage.addAttribute("SearchResults", fuzzyResults);
	
		System.out.println("FINISH: /searchInventoryProducts");
		return "AdminInventory";
	}
	
	@RequestMapping(path = "/directservicesProducts", method = RequestMethod.POST)
	public String searchserviceProducts(
			HttpServletRequest request,
			@RequestParam(name = "searchBar") String searchBar, 
			@RequestParam(name = "selectCategory") String selectCategory,
			Model nextPage) {
//		String s = request.getParameter("selectCategory");
//		System.out.println(s);
		
		System.out.println("BEGIN: /searchserviceProducts");
		System.out.println("	User input (searchBar): [" + searchBar+"]");
		System.out.println("	User input (selectCategory): [" + selectCategory+"]");
		String category = "";
		List<ProductBean> fuzzyResults = new ArrayList<ProductBean>();
		if (selectCategory.equals("蔥類")) {
			category = "蔥類";
			fuzzyResults = pService.selectFuzzy(searchBar, category);
		} else if (selectCategory.equals("根菜類")) {
			category = "根菜類";
			fuzzyResults = pService.selectFuzzy(searchBar, category);
		} else if (selectCategory.equals("莖菜類")) {
			category = "莖菜類";
			fuzzyResults = pService.selectFuzzy(searchBar, category);
		} else if (selectCategory.equals("瓜果類")) {
			category = "瓜果類";
			fuzzyResults = pService.selectFuzzy(searchBar, category);
		} else {
			category = searchBar;
			fuzzyResults = pService.selectFuzzy(searchBar, searchBar, searchBar);
		}
		System.out.println("	QUERY RESULTS:");
		for (int index = 0; index < fuzzyResults.size(); index++) {
			System.out.println("	List index #"+index);
			System.out.println("		id: " + fuzzyResults.get(index).getProductID());
			System.out.println("		name: " + fuzzyResults.get(index).getProductName());
			System.out.println("		price: " + fuzzyResults.get(index).getProductPrice());
			System.out.println("		stock: " + fuzzyResults.get(index).getProductStock());
			System.out.println("		description: " + fuzzyResults.get(index).getProductDescription());
			System.out.println("		image: " + fuzzyResults.get(index).getProductImg());
			System.out.println("		timestamp: " + fuzzyResults.get(index).getProductTimestamp());
			System.out.println("		category: " + fuzzyResults.get(index).getProductCategory());
		}
		nextPage.addAttribute("SearchResults", fuzzyResults);
	
		System.out.println("FINISH: /searchserviceProducts");
		return "front_services";
	}
	
	@RequestMapping(path = "/AdminProfile/searchBarProfiles", method = RequestMethod.POST)
	public String searchBarProfiles(@RequestParam(name = "searchBar") String searchBar, Model nextPage) {
		System.out.println("BEGIN: /searchBarProfiles");
		System.out.println("	User input: " + searchBar);
		try {
			if (searchBar == null || searchBar =="" || searchBar ==" ") {
				System.out.println("		User inputted null, only space, or empty");
				searchBar = "";
			}
			ArrayList<ProfileBean> fuzzyResults = new ArrayList<ProfileBean>();
			fuzzyResults = profileService.selectFuzzy(searchBar);
			System.out.println("	QUERY RESULTS:");
			for (int index = 0; index < fuzzyResults.size(); index++) {
				System.out.println("	List index #"+index);
				System.out.println("		profileID: " + fuzzyResults.get(index).getProfileID());
				System.out.println("		userID: " + fuzzyResults.get(index).getUserID());
				System.out.println("		profileFullName: " + fuzzyResults.get(index).getProfileFullName());
				System.out.println("		profileJoinDate: " + fuzzyResults.get(index).getProfileJoinDate());
				System.out.println("		profileBirthdate: " + fuzzyResults.get(index).getProfileBirthdate());
				System.out.println("		profileSex: " + fuzzyResults.get(index).getProfileSex());
				System.out.println("		profilePhone: " + fuzzyResults.get(index).getProfilePhone());
				System.out.println("		profileAddress: " + fuzzyResults.get(index).getProfileAddress());
				System.out.println("		profileVIP: " + fuzzyResults.get(index).getProfileVIP());
			}
			// return results
			nextPage.addAttribute("SearchResults", fuzzyResults);
			System.out.println("FINISH: /searchBarProfiles");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "AdminViewAllUser"; 
	}
	
	@RequestMapping(path = "/searchBarUsers", method = RequestMethod.POST)
	public String searchBarUsers(@RequestParam(name = "searchBar") String searchBar, Model nextPage) {
		System.out.println("BEGIN: /searchBarUsers");
		System.out.println("	User input: " + searchBar);

		// call fuzzyQuery model method on name
		// call fuzzyQery model method on category
		// call fuzzyQery model method on description
		List<UserBean> fuzzyResults = uService.selectFuzzy(searchBar);
		// get rid of the overlaps
		// if results size() is 0, assume input has typos
		// look up dictionary for similar words to input
		// Reorder the fuzzyResults by relevance; search result
		// rank similar words by index
		// add top 3 similar words to empty results
		
		// print results
		System.out.println("	QUERY RESULTS:");
		for (int index = 0; index < fuzzyResults.size(); index++) {
			System.out.println("	List index #"+index);
			System.out.println("		name: " + fuzzyResults.get(index).getUserID());
			//nextPage.addAttribute("name", fuzzyResults.get(index).getProductName());
			
			System.out.println("		price: " + fuzzyResults.get(index).getUserEmail());
			//nextPage.addAttribute("price", fuzzyResults.get(index).getProductPrice());
			
			System.out.println("		stock: " + fuzzyResults.get(index).getUserPwd());
			//nextPage.addAttribute("stock", fuzzyResults.get(index).getProductStock());
			
			System.out.println("		description: " + fuzzyResults.get(index).getAdmin());
			//nextPage.addAttribute("description", fuzzyResults.get(index).getProductDescription());
		}
		
		// return results
		nextPage.addAttribute("SearchResults", fuzzyResults);
		System.out.println("FINISH: /searchBarUsers");
		return "AdminViewAllUser";
	}
}
