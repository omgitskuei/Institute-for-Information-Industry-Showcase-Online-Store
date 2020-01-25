package model.orderDetails;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import model.user.UserBean;

/* TODO
*
*
* !!!!!!)NOT YET HQL 
*
*
*/

@Repository
public class OrderDetailsBeanDAO implements OrderDetailsBeanDAOInterface{

	private SessionFactory sessionFactory;
	
	@Autowired
	public OrderDetailsBeanDAO(@Qualifier("sessionFactroy") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public boolean insertOrderDetails(OrderDetailsBean insertThisOrderDetails) {
		// Create new Session 
		System.out.println("BEGIN: OrdedrDetailsBean.insertOrderDetails(OrderDetailsBean insertThisOrderDetails)");
		Session session = sessionFactory.getCurrentSession();
		// Test if passed OrderDtailsBean is not empty
		if (insertThisOrderDetails != null) {
			System.out.println("insertThisOrderDatails != null");
			// Insert the passed OrderDetailsBean
			session.save(insertThisOrderDetails);
			System.out.println("OrderDetailsBean Inserted: ");
			
			System.out.println("ProductID: " + insertThisOrderDetails.getProductID());
			System.out.println("ProductCount: " + insertThisOrderDetails.getProductCount());
			System.out.println("OrderID: " + insertThisOrderDetails.getOrderID());
			
			System.out.println("FINISH: OrderDetailsBean.insertOrderDetails(OrderDetailsBean insertThisOrderDetails)");
			// Return True, for SUCCESSFUL INSERT
			return true;
		}
		System.out.println("ERROR: Insert OrderBeanDetails FAILED; OrderDetailsBean insertThisOrderDetails == null.");
		System.out.println("FINISH: OrderDetailsBeanDAO.insertOrderDetails(OrderDetailsBean insertThisOrderDetails)");
		// Return False, for FAILED INSERT
		return false;
	}


	@Override
	public OrderDetailsBean selectOrderDetails(OrderDetailsBean selectThisOrderDetails) {
		// TODO HQL Query
			
		
		return null;
	}


	@Override
	public boolean updateProductID(OrderDetailsBean updateThisOrderDetail, int newProductID) {
		// Get current Session
		System.out.println(
				"Begin: OrderDetailsBeanDAO.updateProductID(OrderDetailsBean updateThisOrderDetails, int newProductID)");
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisOrderDetails is null
		if (updateThisOrderDetail != null) {
			// Try to find updateThisOrderDetails TODO this need to change HQL
			OrderDetailsBean existingOrderDetails = session.get(OrderDetailsBean.class, updateThisOrderDetail.getOrderDetailID());
			if (existingOrderDetails != null) {
				// If found, update ProductID and return
				int oldProductID = existingOrderDetails.getOrderDetailID();
				existingOrderDetails.setProductID(newProductID);
				System.out.println(
						"Finish: OrderDetailsBeanDAO.updateProductID(OrderDetailsBean updateThidOrderDetails, int newProductID)");
				return true;
				
			}
		}
		// Return False	
		System.out.println(
				"Finish: OrderDetailsBeanDAO.updateProductID(OrderDetailsIDBean updateThisOrderDetails, int newProductID)");
		return false;
	}


	@Override
	public boolean updateProductCount(OrderDetailsBean updateThisOrderDetail, int newProductCount) {
		// Get current Session
		System.out.println(
				"Begin: OrderDetailsBeanDAO.updateProductCount(OrderDetailsBean updateThisOrderDetails, int newProductCount)");
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisOrderDetails is null
		if (updateThisOrderDetail != null) {
			// Try to find updateThisProduct
			OrderDetailsBean existingOrderDetails = session.get(OrderDetailsBean.class, updateThisOrderDetail.getOrderDetailID());
			if (existingOrderDetails != null) {
				// If found, update ProductCount and return
				int oldProductCount = existingOrderDetails.getProductCount();
				existingOrderDetails.setProductCount(newProductCount);
				System.out.println(
						"Finish: OrderDetailsBeanDAO.updateProductCount(OrderDetailsBean updateThisOrderDetail, int newProductCount)");
			}
		}
		System.out.println(
				"Finsih: ProductBean.updateProductStock(OrderDetailBean updateThisOrderDetail, int newProductCount)");
		return false;
	}


	@Override
	public boolean updateOrderID(OrderDetailsBean updateThisOrderDetail, int newOrderID) {
		// Get current Session
		System.out.println(
				"Begin: OrderDetailsBeanDAO.update");
		// Check if updateThisProduct is null
		if (updateThisOrderDetail != null) {
			// Try to find updateThisProduct
			
			Session session = sessionFactory.getCurrentSession();
			String hqlString = "from UserBean where userEmail=:thisEmail and userPwd=:thisPwd";
			Query q = session.createQuery(hqlString);
			q.setParameter("thisEmail", selectThisUser.getUserEmail());
			q.setParameter("thisPwd", selectThisUser.getUserPwd());
			UserBean existingUser = (UserBean) q.uniqueResult();
			
			OrderDetailsBean existingOrderDetails = session.get(OrderDetailsBean.class, updateThisOrderDetail.getOrderID());
			if (existingOrderDetails != null) {
				// If found, update OrderID and return True
				int oldProductOrderID = existingOrderDetails.getOrderID();
				existingOrderDetails.setProductCount(newOrderID);
				System.out.println(
						"Finish: OrderDetailsBeanDAO.updateProductCount(OrderDetailsBean updateThisOrderDetail, int newProductOrderID)");
			}
		}
		System.out.println(
				"Finish: OrderDatilsBeanDAO.updateOrderID(OrderDetailsBean updateThisOrderDetail, int newOrderID)");
		return false;
	}


	@Override
	public boolean deleteOrderDetails(OrderDetailsBean deteleThisOrderDetails) {
		// GET current Session 
		Session session = sessionFactory.getCurrentSession();
		// Check if deleteThisOrderDetails is null
		if (deteleThisOrderDetails != null) {
			// Try to find deleteThisOrderDetails
			OrderDetailsBean existingOrderDetailsBean = session.get(OrderDetailsBean.class, deteleThisOrderDetails.getOrderDetailID());
			// If found, delete, return True
			int deleteOrderDetailID = existingOrderDetailsBean.getOrderDetailID();
			int deleteProductID = existingOrderDetailsBean.getProductID();
			int deleteProductCount = existingOrderDetailsBean.getProductCount();
			int deleteOrderID = existingOrderDetailsBean.getOrderID();
			session.delete(existingOrderDetailsBean);
			return true;
		}
		return false;
	}

	

}
