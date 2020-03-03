package model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductBeanService implements ProductBeanServiceInterface {
	
	@Autowired
	private ProductBeanDAO productDAO;
	
	@Override
	public List<ProductBean> selectAll() {
		return productDAO.selectAll();
	}

	@Override
	public void saveProduct(ProductBean theProduct) {
		productDAO.insertProduct(theProduct);
	}
	
	public boolean updateProductName(ProductBean thisProduct, String newProductName) {
		return productDAO.updateProductName(thisProduct, newProductName);
	}
	
	public List<ProductBean> selectFuzzy(String productName, String productCategory, String description) {
		return productDAO.selectFuzzy(productName, productCategory, description);
	}
	
	public List<ProductBean> selectFuzzy(String productName, String productCategory) {
		return productDAO.selectFuzzy(productName, productCategory);
	}

	@Override
	public ProductBean getProduct(int productID) {
		return productDAO.getProduct(productID);
	}

	@Override
	public void deleteProduct(int productID) {
		productDAO.deleteProduct(productID);
	}

}
