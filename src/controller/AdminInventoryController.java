package controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import model.product.ProductBean;
import model.product.ProductBeanService;
import util.GetDateOrTime;
import util.ImgConversion;

@Controller
@RequestMapping(value = "/AdminProduct", method = RequestMethod.GET)
@SessionAttributes(names = {"userEmail"})
public class AdminInventoryController {

	@Autowired
	ProductBeanService productService;
	
	public AdminInventoryController(ProductBeanService productService) {
		this.productService = productService;
	}
	
	// 1)導到AdminInventory頁面，並傳庫存資料產生表格
	// 2)已完成
	// 3)Thomas
	@RequestMapping(value = "/inventories", method = RequestMethod.GET)
	public String showForm(@SessionAttribute("userEmail") String uEmail, Model model) {
		System.out.println("Directing to AdminInventory");
		List<ProductBean> Inventorylist=productService.selectAll();
		model.addAttribute("SearchResults", Inventorylist);
		return "AdminInventory";
	}
	
	@RequestMapping(value = "/addingProductForm", method = RequestMethod.GET)
	public String addNewProduct() {
		System.out.println("Directing to AdminAddNewProduct");
		return "AdminAddNewProduct";
	}
	
	// 管理者增加商品
	@PostMapping(value="/addProduct")
	public String addProduct(
			@RequestParam(name="productName", required = true) String productName,
			@RequestParam(name="productPrice", required = true) float productPrice,
			@RequestParam(name="productStock", required = true) int productStock,
			@RequestParam(name="productDescription", required = false) String productDescription,
			@RequestParam(name="file", required = true) MultipartFile file,
			@RequestParam(name="productCategory", required = true) String productCategory,
			HttpServletRequest request,
			Model model) {
		
		System.out.println("Beging add product");
		
		System.out.println("	User input: ");
		System.out.println("	productName: " + productName);
		System.out.println("	productPrice: " + productPrice);
		System.out.println("	productStock: " + productStock);
		System.out.println("	productDescription: " + productDescription);
		System.out.println("	file: " + file);
		System.out.println("	productCategory: " + productCategory);
		
		ProductBean productBean = new ProductBean();
		productBean.setProductName(productName);
		productBean.setProductPrice(productPrice);
		productBean.setProductStock(productStock);
		productBean.setProductDescription(productDescription);
		productBean.setProductCategory(productCategory);
		
		System.out.println("Beging add setProductTimestamp");
		try {
			GetDateOrTime dateUtil = new GetDateOrTime();
			Date y = dateUtil.generateDate();
			productBean.setProductTimestamp(y);
			System.out.println("Generate Product timestamp finish !!");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("Beging add productImg");
		
//		ImgConversion imgUtil = new ImgConversion();
//		String newFile = imgUtil.addProductImg(file, request);
		
		String fileName = file.getOriginalFilename();
		System.out.println("fileName=" + fileName);	
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		String savePath =  "images/productsImage/" + fileName;
		System.out.println("savePath=" + savePath);
//		
		
		productBean.setProductImg(savePath);
		productService.saveProduct(productBean);
		System.out.println("Finish adding productImg");
		
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
