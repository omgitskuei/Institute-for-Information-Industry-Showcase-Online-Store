package model;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ProductBeanDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public ProductBeanDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public boolean insertUser(ProductBean insertThisProduct) {
		// Create new Session
		System.out.println("BEGIN: ProductBeanDAO.insertProduct(ProductBean insertThisUser)");
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
			
			System.out.println("FINISH: UserBeanDAO.inserProduct(ProductBean insertThisProduct)");
			// Return True, for SUCCESSFUL INSERT
			return true;
		}
		System.out.println("ERROR: Insert ProductBean FAILED; ProductBean insertThisProduct == null.");
		System.out.println("FINISH: ProductBeanDAO.insertProduct(ProductBean insertThisProduct)");
		// Return False, for FAILED INSERT
		return false;
	}

	@Override
	public ProductBean selectProduct(ProductBean selectThisProduct) {
		// Get current Session
		System.out.println("Begin: ProductBeanDAO.selecUser(ProductBean selectThisProduct)");
		Session session = sessionFactory.getCurrentSession();
		// Check if selecThisUser is null
		if (selectThisProduct != null) {
			// Try to find selectThisUser
			ProductBean existingProduct = session.get(ProductBean.class, selectThisProduct.getProductID());
			if (existingProduct != null) {
				// If found, return the result ProductBean existingProduct
				System.out.println("Finish: ProductBeanDAO.selecUser(ProductBean selectThisProduct)");
				return existingProduct;
			}
		}
		//existingProduct returned null meaning selectThisProduct was not found
		System.out.println("Finish: ProductBeanDAO.selecUser(ProductBean selectThisProduct)");
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
	public boolean updateEmail(ProductBean updateThisProduct, String newEmail) {
		// Get current Session
		System.out.println("Begin: ProductBeanDAO.updateEmail(UserBean updateThisUser, String newEmail)");
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisUser is null
		if (updateThisProduct != null) {
			// Try to find updateThisUser
			UserBean existingUser = session.get(UserBean.class, updateThisProduct.getUserID());
			if (existingUser != null) {
				// If found, update Email and return True
				String oldEmail = existingUser.getUserEmail();
				existingUser.setUserEmail(newEmail);
				System.out.println("Finish: UserBeanDAO.updateEmail(UserBean updateThisUser, String newEmail)");
				return true;
			}
		}
		// Return False because 1) updateThisUser was null OR 2) existingUser was null
		System.out.println("Finish: UserBeanDAO.updateEmail(UserBean updateThisUser, String newEmail)");
		return false;
	}

	@Override
	public boolean updatePwd(UserBean updateThisUser, String newPwd) {
		// Get current Session
		Session session = sessionFactory.getCurrentSession();
		System.out.println("Begin: UserBeanDAO.updatePwd(UserBean updateThisUser, String newPwd)");
		// Check if updateThisUser is null
		if (updateThisUser != null) {
			// Try to find updateThisUser
			UserBean existingUser = session.get(UserBean.class, updateThisUser.getUserID());
			if (existingUser != null) {
				// If found, update Pwd and return True
				String oldPwd = existingUser.getUserPwd();
				existingUser.setUserPwd(newPwd);
				System.out.println("Finish: UserBeanDAO.updatePwd(UserBean updateThisUser, String newPwd)");
				return true;
			}
		}
		// Return False because 1) updateThisUser was null OR 2) existingUser was null
		System.out.println("Finish: UserBeanDAO.updatePwd(UserBean updateThisUser, String newPwd)");
		return false;
	}

	@Override
	public boolean deleteProduct(ProductBean deleteThisProduct) {
		// Get current Session
		Session session = sessionFactory.getCurrentSession();
		// Check if deleteThisUser is null
		if (deleteThisProduct != null) {
			// Try to find deleteThisUser
			UserBean existingProduct = session.get(ProductBean.class, deleteThisProduct.getProductID());
			if (existingProduct != null) {
				// If found, delete, return True
				int deletedUserID = existingProduct.getProductrID();
				String deleted = existingProduct.get();
				String deleted = existingProduct.getd();
				int deleted = existingProduct.get();
				session.delete(existingProduct);
				return true;
			}
		}
		// Return False because 1) updateThisProduct was null OR 2) existingProduct was null
		return false;
	}

}
