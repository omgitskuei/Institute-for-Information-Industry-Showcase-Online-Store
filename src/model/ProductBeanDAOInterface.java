package model;

import java.util.Date;

public interface ProductBeanDAOInterface {
	// An abstract list of all must-have methods for ProductBeanDAO; CRUD
	// C.reate
	public boolean insertProduct(ProductBean insertThisProduct);
	// R.ead
	public ProductBean selectProduct(ProductBean selectThisProduct);
	// U.pdate
	public boolean updateProductName(ProductBean updateThisProduct, String ProductName);
	public boolean updateProductPrice(ProductBean updateThisProduct, float ProductPrice);
	public boolean updateProductStock(ProductBean updateThisProduct, int ProductStock);
	public boolean updateProductDescription(ProductBean updateThisProduct, String ProductDescription);
	public boolean updateProductImg(ProductBean updateThisProduct, byte[] ProductImg);
	public boolean updateProductTimestamp(ProductBean updateThisProduct, Date ProductTimestamp);
	public boolean updateProductCategory(ProductBean updateThisProduct, String ProductCategory);
	// D.elete
	public boolean deleteProduct(ProductBean deleteThisProduct);
	
}