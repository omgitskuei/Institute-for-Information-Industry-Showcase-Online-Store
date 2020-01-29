package model.product;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductBeanService {

	private ProductBeanDAO prDao;
	

	@Autowired
	public ProductBeanService(ProductBeanDAO prDao) {
		this.prDao = prDao;
	}
	
	public boolean insert(ProductBean newProduct) {
		System.out.println("BEGIN: ProductService.insert(Product insertThisProduct)");
		// Get values from newUser for validation
		String Name = newProduct.getProductName();
		float Price = newProduct.getProductPrice();
		int Stock = newProduct.getProductStock();
		String Description = newProduct.getProductDescription();
		byte[] Img = newProduct.getProductImg();
		Date Timestamp = newProduct.getProductTimestamp();
		String Category = newProduct.getProductCategory();
		boolean flag = false;
		
		
		// Validate values
		if (checkName(Name) && checkPrice(Price) && checkStock(Stock) && checkDescription(Description) && checkImg(Img) && checkTimestamp(Timestamp) && checkCategory(Category)) {
			System.out.println("correct");
			flag = prDao.insertProduct(newProduct); //Returns true
		} else {
			System.out.println("please insert the correct values");
		}
		System.out.println("FINISH: ProductService.insert(Product insertThisProduct)");
		return flag;
	}
	
	public ProductBean select(ProductBean selectThisProduct) {
		System.out.println("BEGIN: ProductBeanService.select(ProductBean insertThisProduct)");
		// Get values for validation
		String Name = selectThisProduct.getProductName();
		float Price = selectThisProduct.getProductPrice();
		int Stock = selectThisProduct.getProductStock();
		String Description = selectThisProduct.getProductDescription();
		byte[] Img = selectThisProduct.getProductImg();
		Date Timestamp = selectThisProduct.getProductTimestamp();
		String Category = selectThisProduct.getProductCategory();
		// Validate values
		if (checkName(Name) && checkPrice(Price) && checkStock(Stock) && checkDescription(Description) && checkImg(Img) && checkTimestamp(Timestamp) && checkCategory(Category)) {
			System.out.println("correct");
			return prDao.selectProduct(selectThisProduct);
		} else {
			System.out.println("please select the correct values");
		}
		System.out.println("FINISH: ProductService.select(Product insertThisProduct)");
		return null;
	}
	
	public boolean updateProductName(ProductBean updateThisProduct, String newProductName) {
		System.out.println("BEGIN: ProductService.updateProductName(ProductBean insertThisProduct)");
		// Validate values, if not valid, don't bother with update
		if (checkName(newProductName)) {
			System.out.println("New Name valid");
			return prDao.updateProductName(updateThisProduct, newProductName);
		} else {
			System.out.println("New Name valid");
		}
		System.out.println("FINISH: ProductService.updaete(Product insertThisProduct)");
		return false;
	}
	
	
	public boolean updateProductPrice(ProductBean updateThisProduct, float newProductPrice) {
		System.out.println("BEGIN: ProductService.updateProductPrice(ProductBean insertThisProduct)");
		// Validate values, if not valid, don't bother with update
		if (checkPrice(newProductPrice)) {
			System.out.println("New Price valid");
			return prDao.updateProductPrice(updateThisProduct, newProductPrice);
		} else {
			System.out.println("New Price valid");
		}
		System.out.println("FINISH: ProductService.updaete(Product insertThisProduct)");
		return false;
	}
	
	
	public boolean updateProductStock(ProductBean updateThisProduct, int newProductStock) {
		System.out.println("BEGIN: ProductService.updateProductStock(ProductBean insertThisProduct)");
		// Validate values, if not valid, don't bother with update
		if (checkStock(newProductStock)) {
			System.out.println("New Stock valid");
			return prDao.updateProductStock(updateThisProduct, newProductStock);
		} else {
			System.out.println("New Stock valid");
		}
		System.out.println("FINISH: ProductService.updaete(Product insertThisProduct)");
		return false;
	}
	
	
	public boolean updateProductDescription(ProductBean updateThisProduct, String newProductDescription) {
		System.out.println("BEGIN: ProductService.updateProductDescription(ProductBean insertThisProduct)");
		// Validate values, if not valid, don't bother with update
		if (checkDescription(newProductDescription)) {
			System.out.println("New Description valid");
			return prDao.updateProductDescription(updateThisProduct, newProductDescription);
		} else {
			System.out.println("New Description valid");
		}
		System.out.println("FINISH: ProductService.updaete(Product insertThisProduct)");
		return false;
	}
	

	public boolean updateProductImg(ProductBean updateThisProduct, byte[] newProductImg) {
		System.out.println("BEGIN: ProductService.updateProductImg(ProductBean insertThisProduct)");
		// Validate values, if not valid, don't bother with update
		if (checkImg(newProductImg)) {
			System.out.println("New Img valid");
			return prDao.updateProductImg(updateThisProduct, newProductImg);
		} else {
			System.out.println("New Img valid");
		}
		System.out.println("FINISH: ProductService.updaete(Product insertThisProduct)");
		return false;
	}
	
	public boolean updateProductTimestamp(ProductBean updateThisProduct, Date newProductTimestamp) {
		System.out.println("BEGIN: ProductService.updateProductTimestamp(ProductBean insertThisProduct)");
		// Validate values, if not valid, don't bother with update
		if (checkTimestamp(newProductTimestamp)) {
			System.out.println("New GetDateOrTime valid");
			return prDao.updateProductTimestamp(updateThisProduct, newProductTimestamp);
		} else {
			System.out.println("New GetDateOrTime valid");
		}
		System.out.println("FINISH: ProductService.updaete(Product insertThisProduct)");
		return false;
	}
	
	
	public boolean updateProductCategory(ProductBean updateThisProduct, String newProductCategory) {
		System.out.println("BEGIN: ProductService.updateProductCategory(ProductBean insertThisProduct)");
		// Validate values, if not valid, don't bother with update
		if (checkCategory(newProductCategory)) {
			System.out.println("New Category valid");
			return prDao.updateProductCategory(updateThisProduct, newProductCategory);
		} else {
			System.out.println("New Category valid");
		}
		System.out.println("FINISH: ProductService.updaete(Product insertThisProduct)");
		return false;
	}
	
	
	
private static boolean checkName(String Name) {
	return false;
	}
	
private static boolean checkPrice(float Price) {
	return false;
		
	}

private static boolean checkStock(int Stock) {
	return false;
	
}

private static boolean checkDescription(String Description) {
	return false;
	
}

private static boolean checkImg(byte[] Img) {
	return false;
	
}

private static boolean checkTimestamp(Date Timestamp) {
	return false;
	
}

private static boolean checkCategory(String Category) {
	return false;
	
}
	
}