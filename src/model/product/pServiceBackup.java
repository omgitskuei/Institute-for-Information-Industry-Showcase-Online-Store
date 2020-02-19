//package model.product;
//
//import java.util.ArrayList;
//import java.util.Date;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import util.CheckSubstring;
//
//@Service
//public class ProductBeanService {
//
//	private ProductBeanDAO prDao;
//	
//
//	@Autowired
//	public ProductBeanService(ProductBeanDAO prDao) {
//		this.prDao = prDao;
//	}
//	
//	public boolean insert(ProductBean newProduct) {
//		System.out.println("BEGIN: ProductService.insert(Product insertThisProduct)");
//		// Get values from newUser for validation
//		String Name = newProduct.getProductName();
//		float Price = newProduct.getProductPrice();
//		int Stock = newProduct.getProductStock();
//		String Description = newProduct.getProductDescription();
//		byte[] Img = newProduct.getProductImg();
//		Date Timestamp = newProduct.getProductTimestamp();
//		String Category = newProduct.getProductCategory();
//		boolean flag = false;
//		
//		
//		// Validate values
//		if (validName(Name) && validPrice(Price) && validStock(Stock) && validDescription(Description) && validImg(Img) && validTimestamp(Timestamp) && validCategory(Category)) {
//			System.out.println("correct");
//			flag = prDao.insertProduct(newProduct); //Returns true
//		} else {
//			System.out.println("please insert the correct values");
//		}
//		System.out.println("FINISH: ProductService.insert(Product insertThisProduct)");
//		return flag;
//	}
//	
//	public ProductBean select(ProductBean selectThisProduct) {
//		System.out.println("BEGIN: ProductBeanService.select(ProductBean insertThisProduct)");
//		// Get values for validation
//		String Name = selectThisProduct.getProductName();
////		float Price = selectThisProduct.getProductPrice();
////		int Stock = selectThisProduct.getProductStock();
////		String Description = selectThisProduct.getProductDescription();
////		byte[] Img = selectThisProduct.getProductImg();
////		Date Timestamp = selectThisProduct.getProductTimestamp();
//		String Category = selectThisProduct.getProductCategory();
//		// Validate values
//		if (validName(Name) && 
////				validPrice(Price) && 
////				validStock(Stock) && 
////				validDescription(Description) && 
////				validImg(Img) && 
////				validTimestamp(Timestamp) && 
//				validCategory(Category)
//				) {
//			System.out.println("correct");
//			return prDao.selectByName(selectThisProduct);
//		} else {
//			System.out.println("please select the correct values");
//		}
//		System.out.println("FINISH: ProductService.select(Product insertThisProduct)");
//		return null;
//	}
//	
//	public ProductBean select(String productName, String category) {
//		System.out.println("BEGIN: ProductBeanService.select(ProductBean insertThisProduct)");
//		ProductBean newBean = new ProductBean();
//		// Validate values
//		if (validName(productName))
//		
//		
//		newBean.setProductName(productName);
//		newBean.setProductCategory(category);
//		
//		// Get values for validation
//		String Name = selectThisProduct.getProductName();
//		float Price = selectThisProduct.getProductPrice();
//		int Stock = selectThisProduct.getProductStock();
//		// String Description = selectThisProduct.getProductDescription();
//		byte[] Img = selectThisProduct.getProductImg();
//		Date Timestamp = selectThisProduct.getProductTimestamp();
//		String Category = selectThisProduct.getProductCategory();
//		// Validate values
//		if (validName(Name) && 
//				validPrice(Price) && 
//				validStock(Stock) && 
////				validDescription(Description) && 
//				validImg(Img) && 
//				validTimestamp(Timestamp) && 
//				validCategory(Category)
//				) {
//			System.out.println("correct");
//			return prDao.selectByName(selectThisProduct);
//		} else {
//			System.out.println("please select the correct values");
//		}
//		System.out.println("FINISH: ProductService.select(Product insertThisProduct)");
//		return null;
//	}
//	
//	public ProductBean select(String productName) {
//		System.out.println("BEGIN: ProductBeanService.select(ProductBean insertThisProduct)");
//		// Get values for validation
//		String Name = selectThisProduct.getProductName();
////		float Price = selectThisProduct.getProductPrice();
////		int Stock = selectThisProduct.getProductStock();
////		String Description = selectThisProduct.getProductDescription();
////		byte[] Img = selectThisProduct.getProductImg();
////		Date Timestamp = selectThisProduct.getProductTimestamp();
////		String Category = selectThisProduct.getProductCategory();
//		// Validate values
//		if (validName(Name) && 
//				validPrice(Price) && 
//				validStock(Stock) && 
////				validDescription(Description) && 
//				validImg(Img) && 
//				validTimestamp(Timestamp) && 
//				validCategory(Category)
//				) {
//			System.out.println("correct");
//			return prDao.selectByName(selectThisProduct);
//		} else {
//			System.out.println("please select the correct values");
//		}
//		System.out.println("FINISH: ProductService.select(Product insertThisProduct)");
//		return null;
//	}
//	
//	public ArrayList<ProductBean> selectAllFromCategory(String category) {
//		System.out.println("BEGIN: ProductBeanService.select(ProductBean insertThisProduct)");
//		// Get values for validation
//		String Name = selectThisProduct.getProductName();
//		float Price = selectThisProduct.getProductPrice();
//		int Stock = selectThisProduct.getProductStock();
//		// String Description = selectThisProduct.getProductDescription();
//		byte[] Img = selectThisProduct.getProductImg();
//		Date Timestamp = selectThisProduct.getProductTimestamp();
//		String Category = selectThisProduct.getProductCategory();
//		// Validate values
//		if (validName(Name) && 
//				validPrice(Price) && 
//				validStock(Stock) && 
////				validDescription(Description) && 
//				validImg(Img) && 
//				validTimestamp(Timestamp) && 
//				validCategory(Category)
//				) {
//			System.out.println("correct");
//			return prDao.selectByName(selectThisProduct);
//		} else {
//			System.out.println("please select the correct values");
//		}
//		System.out.println("FINISH: ProductService.select(Product insertThisProduct)");
//		return null;
//	}
//	
//	public boolean updateProductName(ProductBean updateThisProduct, String newProductName) {
//		System.out.println("BEGIN: ProductService.updateProductName(ProductBean insertThisProduct)");
//		// Validate values, if not valid, don't bother with update
//		if (validName(newProductName)) {
//			System.out.println("New Name valid");
//			return prDao.updateProductName(updateThisProduct, newProductName);
//		} else {
//			System.out.println("New Name valid");
//		}
//		System.out.println("FINISH: ProductService.updaete(Product insertThisProduct)");
//		return false;
//	}
//	
//	public boolean updateProductPrice(ProductBean updateThisProduct, float newProductPrice) {
//		System.out.println("BEGIN: ProductService.updateProductPrice(ProductBean insertThisProduct)");
//		// Validate values, if not valid, don't bother with update
//		if (validPrice(newProductPrice)) {
//			System.out.println("New Price valid");
//			return prDao.updateProductPrice(updateThisProduct, newProductPrice);
//		} else {
//			System.out.println("New Price valid");
//		}
//		System.out.println("FINISH: ProductService.updaete(Product insertThisProduct)");
//		return false;
//	}
//	
//	public boolean updateProductStock(ProductBean updateThisProduct, int newProductStock) {
//		System.out.println("BEGIN: ProductService.updateProductStock(ProductBean insertThisProduct)");
//		// Validate values, if not valid, don't bother with update
//		if (validStock(newProductStock)) {
//			System.out.println("New Stock valid");
//			return prDao.updateProductStock(updateThisProduct, newProductStock);
//		} else {
//			System.out.println("New Stock valid");
//		}
//		System.out.println("FINISH: ProductService.updaete(Product insertThisProduct)");
//		return false;
//	}
//	
//	public boolean updateProductDescription(ProductBean updateThisProduct, String newProductDescription) {
//		System.out.println("BEGIN: ProductService.updateProductDescription(ProductBean insertThisProduct)");
//		// Validate values, if not valid, don't bother with update
//		if (validDescription(newProductDescription)) {
//			System.out.println("New Description valid");
//			return prDao.updateProductDescription(updateThisProduct, newProductDescription);
//		} else {
//			System.out.println("New Description valid");
//		}
//		System.out.println("FINISH: ProductService.updaete(Product insertThisProduct)");
//		return false;
//	}
//	
//	public boolean updateProductImg(ProductBean updateThisProduct, byte[] newProductImg) {
//		System.out.println("BEGIN: ProductService.updateProductImg(ProductBean insertThisProduct)");
//		// Validate values, if not valid, don't bother with update
//		if (validImg(newProductImg)) {
//			System.out.println("New Img valid");
//			return prDao.updateProductImg(updateThisProduct, newProductImg);
//		} else {
//			System.out.println("New Img valid");
//		}
//		System.out.println("FINISH: ProductService.updaete(Product insertThisProduct)");
//		return false;
//	}
//	
//	public boolean updateProductTimestamp(ProductBean updateThisProduct, Date newProductTimestamp) {
//		System.out.println("BEGIN: ProductService.updateProductTimestamp(ProductBean insertThisProduct)");
//		// Validate values, if not valid, don't bother with update
//		if (validTimestamp(newProductTimestamp)) {
//			System.out.println("New GetDateOrTime valid");
//			return prDao.updateProductTimestamp(updateThisProduct, newProductTimestamp);
//		} else {
//			System.out.println("New GetDateOrTime valid");
//		}
//		System.out.println("FINISH: ProductService.updaete(Product insertThisProduct)");
//		return false;
//	}
//	
//	public boolean updateProductCategory(ProductBean updateThisProduct, String newProductCategory) {
//		System.out.println("BEGIN: ProductService.updateProductCategory(ProductBean insertThisProduct)");
//		// Validate values, if not valid, don't bother with update
//		if (validCategory(newProductCategory)) {
//			System.out.println("New Category valid");
//			return prDao.updateProductCategory(updateThisProduct, newProductCategory);
//		} else {
//			System.out.println("New Category valid");
//		}
//		System.out.println("FINISH: ProductService.updaete(Product insertThisProduct)");
//		return false;
//	}
//	
//	
//	
//private static boolean validName(String thisName) {
//	if (thisName != null) {
//		try {
//			CheckSubstring util = new CheckSubstring();
//			ArrayList<String> dotIndexes = util.delimitAtDot(thisName);
//			if (util.countSpecialCharacters(thisName) == 0 && util.countNums(thisName) == 0 && dotIndexes.size()==0) {
//				return true;
//			}
//			return false;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	} else {
//		return false;
//	}
//}
//	
//private static boolean validPrice(float Price) {
//	boolean valid = false;
//	
//	try {
//		String  ThisPrice = Float.toString( Price);
//		if (ThisPrice !=null) {
//			valid = true;
//		} else {
//			valid = false;
//		}
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	return valid;
//		
//	}
//
//private static boolean validStock(int Stock) {
//boolean valid = false;
//	
//	try {
//		String  ThisStock = Float.toString( Stock);
//		if (ThisStock != null) {
//			valid = true;
//		} else {
//			valid = false;
//		}
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	return valid;
//		
//	
//}
//
//private static boolean validDescription(String Description) {
//	boolean valid = false;
//	if (Description != null) {
//		try {
//			CheckSubstring util = new CheckSubstring();
//			int countSpec = util.countSpecialCharacters(Description);
//			int countNum = util.countNums(Description);
//			ArrayList<String> dotIndexes = util.delimitAtDot(Description);
//			if (countSpec == 0 && countNum == 0 && dotIndexes.size()==0) {
//				valid = true;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return valid;
//	} else {
//		return valid;
//	}
//	
//}
//
//private static boolean validImg(byte[] Img) {
//	boolean valid = false;
//	
//	try {
//		if (Img != null) {
//			valid = true;
//		} else {
//			valid = false;
//		}
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	return valid;
//	
//}
//
//private static boolean validTimestamp(Date Timestamp) {
//	boolean valid = false;
//	
//	try {
//		if (Timestamp != null) {
//			valid = true;
//		} else {
//			valid = false;
//		}
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	return valid;
//	
//}
//
//private static boolean validCategory(String Category) {
//	boolean valid = false;
//	if (Category != null) {
//		try {
//			CheckSubstring util = new CheckSubstring();
//			int countSpec = util.countSpecialCharacters(Category);
//			int countNum = util.countNums(Category);
//			ArrayList<String> dotIndexes = util.delimitAtDot(Category);
//			if (countSpec == 0 && countNum == 0 && dotIndexes.size()==0) {
//				valid = true;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return valid;
//	} else {
//		return valid;
//	}
//	
//}
//	
//}