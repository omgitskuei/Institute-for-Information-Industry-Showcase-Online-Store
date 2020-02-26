package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sun.xml.bind.CycleRecoverable.Context;

import model.product.ProductBean;
import model.product.ProductBeanService;
import model.profile.ProfileBean;
import model.profile.ProfileBeanService;
import model.user.UserBean;
import model.user.UserBeanService;

@Controller
@SessionAttributes(names = { "searchBar" })
public class SearchBarController {

	private ProductBeanService pService;
	private UserBeanService uService;
	private ProfileBeanService profileService;
	private HttpServletResponse response;

	@Autowired
	public SearchBarController(ProductBeanService pService, UserBeanService uService, ProfileBeanService profileService, HttpServletResponse response) {
		this.pService = pService;
		this.uService = uService;
		this.profileService = profileService;
		this.response = response;
	}

	@RequestMapping(path = "/searchBarProducts", method = RequestMethod.POST)
	public String searchBarProducts(@RequestParam(name = "searchBar") String searchBar, Model nextPage) {
		System.out.println("BEGIN: /searchBarProducts");
		System.out.println("	User input: " + searchBar);
		List<ProductBean> fuzzyResults = pService.selectFuzzy(searchBar, searchBar, searchBar);
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
		// return results
		nextPage.addAttribute("SearchResults", fuzzyResults);
		System.out.println("FINISH: /searchBarProducts");
		return "AdminIndex";
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
