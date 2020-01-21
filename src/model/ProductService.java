package model;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private ProductBeanDAO prDao;
	private ProductBean updateThisProduct;
	private String newProductName;
	private int newProductPrice;
	private int newProductStock;
	private String newProductDescription;
	private byte[] newProductImg;
	private Date newProductTimestamp;
	private String newProductCategory;
	private ProductBean selectThisProduct;
	
	@Autowired
	public ProductService(ProductBeanDAO prDao) {
		this.prDao = prDao;
	}

	public boolean insert(ProductBean bean) {
		return prDao.insertProduct(bean);
	}
<<<<<<< Updated upstream

=======
	
>>>>>>> Stashed changes
	public ProductBean select(ProductBean bean) {
		return prDao.selectProduct(selectThisProduct);
	}
	public  List<ProductBean> selectAll(ProductBean bean) {
		return prDao.selectAll();
	}
	
	public boolean updateProductName(ProductBean bean) {
		return prDao.updateProductName(updateThisProduct, newProductName);
	}

	public boolean updateProductPrice(ProductBean bean) {
		return prDao.updateProductPrice(updateThisProduct, newProductPrice);
	}
	public boolean updateProductStock(ProductBean bean) {
		return prDao.updateProductStock(updateThisProduct, newProductStock);
	}
	public boolean updateProductDescription(ProductBean bean) {
		return prDao.updateProductDescription(updateThisProduct, newProductDescription);
	}
	public boolean updateProductImg(ProductBean bean) {
		return prDao.updateProductImg(updateThisProduct, newProductImg);
	}
	public boolean updateProductTimestamp(ProductBean bean) {
		return prDao.updateProductTimestamp(updateThisProduct, newProductTimestamp);
	}
	public boolean updateProductCategory(ProductBean bean) {
		return prDao.updateProductCategory(updateThisProduct, newProductCategory);
	}

}
