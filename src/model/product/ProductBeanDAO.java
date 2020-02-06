package model.product;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ProductBeanDAO implements ProductBeanDAOInterface {

	private SessionFactory sessionFactory;

	@Autowired
	public ProductBeanDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertProduct(ProductBean insertThisProduct) {
		// Create new Session
		System.out.println("BEGIN: ProductBeanDAO.insertProduct(ProductBean insertThisProduct)");
		Session session = sessionFactory.getCurrentSession();
		// Test if passed ProductBean is not empty
		if (insertThisProduct != null) {
			System.out.println("insertThisProduct != null");
			// Insert the passed ProductBean
			session.save(insertThisProduct);
			System.out.println("ProductBean Inserted:");
			System.out.println("ProductName: " + insertThisProduct.getProductName());
			System.out.println("ProductPrice: " + insertThisProduct.getProductPrice());
			System.out.println("ProductStock: " + insertThisProduct.getProductStock());
			System.out.println("ProductDescription: " + insertThisProduct.getProductDescription());
			System.out.println("ProductImg: " + insertThisProduct.getProductImg());
			System.out.println("ProductTimestamp: " + insertThisProduct.getProductTimestamp());
			System.out.println("ProductCategory: " + insertThisProduct.getProductCategory());

			System.out.println("FINISH: ProductBeanDAO.insertProduct(ProductBean insertThisProduct)");
			// Return True, for SUCCESSFUL INSERT
			return true;
		} else {
			System.out.println("ERROR: Insert ProductBean FAILED; ProductBean insertThisProduct == null.");
			System.out.println("FINISH: ProductBeanDAO.insertProduct(ProductBean insertThisProduct)");
			// Return False, for FAILED INSERT
			return false;
		}
	}

	@Override
	public ProductBean selectProduct(ProductBean selectThisProduct) {
		// Get current Session
		System.out.println("Begin: ProductBeanDAO.selectProduct(ProductBean selectThisProduct)");
		
		// Check if selectThisUser is null
		if (selectThisProduct != null) {
			// Try to find selectThisProduct
			System.out.println("Looking for this Product: ");
//			System.out.println("Product ID:" + selectThisProduct.getProductID());
			System.out.println("Product Name:" + selectThisProduct.getProductName());
//			System.out.println("Product Price:" + selectThisProduct.getProductPrice());
//			System.out.println("Product Stock:" + selectThisProduct.getProductStock());
//			System.out.println("Product Description:" + selectThisProduct.getProductDescription());
//			System.out.println("Product Img:" + selectThisProduct.getProductImg());
//			System.out.println("Product ProductTimestamp:" + selectThisProduct.getProductTimestamp());
			System.out.println("Product Category:" + selectThisProduct.getProductCategory());
			
			Session session = sessionFactory.getCurrentSession();
			// HQL
			String hqlString = "from ProductBean where ProductName=:thisProductName and ProductCategory=:thisProductCategory";
			Query q = session.createQuery(hqlString);
			q.setParameter("thisProductID", selectThisProduct.getProductID());
			q.setParameter("thisProductName", selectThisProduct.getProductName());
			q.setParameter("thisProductPrice", selectThisProduct.getProductPrice());
			q.setParameter("thisProductStock", selectThisProduct.getProductStock());
			q.setParameter("thisProductDescription", selectThisProduct.getProductDescription());
			q.setParameter("thisProductImg", selectThisProduct.getProductImg());
			q.setParameter("thisProductTimestamp", selectThisProduct.getProductTimestamp());
			q.setParameter("thisProductCategory", selectThisProduct.getProductCategory());
			
			ProductBean existingProduct = (ProductBean) q.uniqueResult();
			
			if (existingProduct != null) {
				// If found, return the result ProductBean existingProduct
				System.out.println("Product FOUND: ProductID: "+existingProduct.getProductID());
				System.out.println("Finish: ProductBeanDAO.selectProduct(ProductBean selectThisProduct)");
				return existingProduct;
			}
		}
		// existingProduct returned null meaning selectThisProduct was not found
		System.out.println("Product NOT FOUND");
		System.out.println("Finish: ProductBeanDAO.selectProductr(ProductBean selectThisProduct)");
		return null;
	}

	// Override tag is only used if supertype ProductBeanDAOInterface ...
	// ... also has this method.
	// @Override
	public List<ProductBean> selectAll() {
		// Get current Session
		System.out.println("Begin: ProductBeanDAO.selectAll()");
		Session session = sessionFactory.getCurrentSession();
		// Get all rows from ProductTable
		Query query = session.createQuery("From ProductBean"); // This 'From' references ProductBean.java
		// Store query results into List results
		List<ProductBean> results = (List<ProductBean>) query.list();
		System.out.println("SelectAll: " + results.get(0).getClass());
		// Print List results into console (for debugging)
		for (int index = 0; index < results.size(); index++) {
			System.out.println(results.get(index).getProductID());
			System.out.println(results.get(index).getProductName());
			System.out.println(results.get(index).getProductPrice());
			System.out.println(results.get(index).getProductStock());
			System.out.println(results.get(index).getProductDescription());
			System.out.println(results.get(index).getProductImg());
			System.out.println(results.get(index).getProductTimestamp());
			System.out.println(results.get(index).getProductCategory());
		}
		// Return List results
		System.out.println("Finish: ProductBeanDAO.selectAll()");
		return results;
	}

	@Override
	public boolean updateProductName(ProductBean updateThisProduct, String newProductName) {
		// Get current Session
		System.out.println(
				"Begin: ProductBeanDAO.updateProductName(ProductBean updateThisProduct, String newProductName)");
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisProduct is null
		if (updateThisProduct != null) {
			// Try to find updateThisProduct
			ProductBean existingProduct = session.get(ProductBean.class, updateThisProduct.getProductID());
			if (existingProduct != null) {
				// If found, update ProductName and return True
				String oldProductName = existingProduct.getProductName();
				existingProduct.setProductName(newProductName);
				System.out.println(
						"Finish: ProductBeanDAO.updateProductName(ProductBean updateThisProductr, String newProductName)");
				return true;
			}
		}
		// Return False because 1) updateThisProduct was null OR 2) existingProduct was
		// null
		System.out.println(
				"Finish: ProductBeanDAO.updateProductName(ProductBean updateThisProduct, String newProductName)");
		return false;
	}

	@Override
	public boolean updateProductPrice(ProductBean updateThisProduct, float newProductPrice) {
		// Get current Session
		System.out.println(
				"Begin: ProductBeanDAO.updateProductPrice(ProductBean updateThisProduct, int newProductPrice)");
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisProduct is null
		if (updateThisProduct != null) {
			// Try to find updateThisProduct
			ProductBean existingProduct = session.get(ProductBean.class, updateThisProduct.getProductID());
			if (existingProduct != null) {
				// If found, update ProductPrice and return True
				float oldProductPrice = existingProduct.getProductPrice();
				existingProduct.setProductPrice(newProductPrice);
				System.out.println(
						"Finish: ProductBeanDAO.updateProductPrice(ProductBean updateThisProductr, int newProductPrice)");
				return true;
			}
		}
		// Return False because 1) updateThisProduct was null OR 2) existingProduct was
		// null
		System.out.println(
				"Finish: ProductBeanDAO.updateProductPrice(ProductBean updateThisProduct, int newProductPrice)");
		return false;
	}

	@Override
	public boolean updateProductStock(ProductBean updateThisProduct, int newProductStock) {
		// Get current Session
		System.out.println(
				"Begin: ProductBeanDAO.updateProductStock(ProductBean updateThisProduct, int newProductStock)");
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisProduct is null
		if (updateThisProduct != null) {
			// Try to find updateThisProduct
			ProductBean existingProduct = session.get(ProductBean.class, updateThisProduct.getProductID());
			if (existingProduct != null) {
				// If found, update ProductStock and return True
				int oldProductStock = existingProduct.getProductStock();
				existingProduct.setProductStock(newProductStock);
				System.out.println(
						"Finish: ProductBeanDAO.updateProductStock(ProductBean updateThisProduct, int newProductStock)");
				return true;
			}
		}
		// Return False because 1) updateThisProduct was null OR 2) existingProduct was
		// null
		System.out.println(
				"Finish: ProductBeanDAO.updateProductStock(ProductBean updateThisProduct, int newProductStock/)");
		return false;
	}

	@Override
	public boolean updateProductDescription(ProductBean updateThisProduct, String newProductDescription) {
		// Get current Session
		System.out.println(
				"Begin: ProductBeanDAO.updateProductDescription(ProductBean updateThisProduct, String newProductDescription)");
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisProduct is null
		if (updateThisProduct != null) {
			// Try to find updateThisProduct
			ProductBean existingProduct = session.get(ProductBean.class, updateThisProduct.getProductID());
			if (existingProduct != null) {
				// If found, update ProductDescription and return True
				String oldProductName = existingProduct.getProductDescription();
				existingProduct.setProductDescription(newProductDescription);
				System.out.println(
						"Finish: ProductBeanDAO.updateProductDescription(ProductBean updateThisProductr, String newProductDescription)");
				return true;
			}
		}
		// Return False because 1) updateThisProduct was null OR 2) existingProduct was
		// null
		System.out.println(
				"Finish: ProductBeanDAO.updateProductDescription(ProductBean updateThisProduct, String newProductDescription)");
		return false;
	}

	@Override
	public boolean updateProductImg(ProductBean updateThisProduct, byte[] newProductImg) {
		// Get current Session
		System.out
				.println("Begin: ProductBeanDAO.updateProductImg(ProductBean updateThisProduct, byte[] newProductImg)");
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisProduct is null
		if (updateThisProduct != null) {
			// Try to find updateThisProduct
			ProductBean existingProduct = session.get(ProductBean.class, updateThisProduct.getProductID());
			if (existingProduct != null) {
				// If found, update ProductImg and return True
				byte[] oldProductImg = existingProduct.getProductImg();
				existingProduct.setProductImg(newProductImg);
				System.out.println(
						"Finish: ProductBeanDAO.updateProductImg(ProductBean updateThisProductr, byte[] newProductImg)");
				return true;
			}
		}
		// Return False because 1) updateThisProduct was null OR 2) existingProduct was
		// null
		System.out.println(
				"Finish: ProductBeanDAO.updateProductImg(ProductBean updateThisProduct, byte[] newProductImg)");
		return false;
	}

	@Override
	public boolean updateProductTimestamp(ProductBean updateThisProduct, Date newProductTimestamp) {
		// Get current Session
		System.out.println(
				"Begin: ProductBeanDAO.updateProductTimestamp(ProductBean updateThisProduct, DATE newProductTimestamp)");
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisProduct is null
		if (updateThisProduct != null) {
			// Try to find updateThisProduct
			ProductBean existingProduct = session.get(ProductBean.class, updateThisProduct.getProductID());
			if (existingProduct != null) {
				// If found, update ProductTimestamp and return True
				Date oldProductTimestamp = existingProduct.getProductTimestamp();
				existingProduct.setProductTimestamp(newProductTimestamp);
				System.out.println(
						"Finish: ProductBeanDAO.updateProductTimestamp(ProductBean updateThisProductr, DATE newProductTimestamp)");
				return true;
			}
		}
		// Return False because 1) updateThisProduct was null OR 2) existingProduct was
		// null
		System.out.println(
				"Finish: ProductBeanDAO.updateProductTimestamp(ProductBean updateThisProduct, byte[] newProductTimestamp)");
		return false;
	}

	@Override
	public boolean updateProductCategory(ProductBean updateThisProduct, String newProductCategory) {
		// Get current Session
		System.out.println(
				"Begin: ProductBeanDAO.updateProductCategory(ProductBean updateThisProduct, String newProductCategory)");
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisProduct is null
		if (updateThisProduct != null) {
			// Try to find updateThisProduct
			ProductBean existingProduct = session.get(ProductBean.class, updateThisProduct.getProductID());
			if (existingProduct != null) {
				// If found, update ProductCategory and return True
				String oldProductName = existingProduct.getProductCategory();
				existingProduct.setProductCategory(newProductCategory);
				System.out.println(
						"Finish: ProductBeanDAO.updateProductCategory(ProductBean updateThisProductr, String newProductCategory)");
				return true;
			}
		}
		// Return False because 1) updateThisProduct was null OR 2) existingProduct was
		// null
		System.out.println(
				"Finish: ProductBeanDAO.updateProductCategory(ProductBean updateThisProduct, String newProductCategory)");
		return false;
	}

	@Override
	public boolean deleteProduct(ProductBean deleteThisProduct) {
		// Get current Session
		Session session = sessionFactory.getCurrentSession();
		// Check if deleteThisUser is null
		if (deleteThisProduct != null) {
			// Try to find deleteThisUser
			ProductBean existingProduct = session.get(ProductBean.class, deleteThisProduct.getProductID());
			if (existingProduct != null) {
				// If found, delete, return True
				int deletedProductID = existingProduct.getProductID();
				String deletedProductName = existingProduct.getProductName();
				float deletedProductPrice = existingProduct.getProductPrice();
				int deletedProductStock = existingProduct.getProductStock();
				String deletedProductDescription = existingProduct.getProductDescription();
				byte[] deletedProductImg = existingProduct.getProductImg();
				Date deletedProductTimestamp = existingProduct.getProductTimestamp();
				String deletedProductCategory = existingProduct.getProductCategory();
				session.delete(existingProduct);
				return true;
			}

		}
		return false;
	}
}