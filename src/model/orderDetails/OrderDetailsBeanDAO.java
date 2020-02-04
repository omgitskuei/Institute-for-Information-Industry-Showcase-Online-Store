//package model.orderDetails;
//
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class OrderDetailsBeanDAO implements OrderDetailsBeanDAOInterface {
//
//	// Local Fields
//	private SessionFactory sessionFactory;
//
//	// Constructors
//	@Autowired
//	public OrderDetailsBeanDAO(@Qualifier("sessionFactroy") SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
//
//	// CRUD Methods
//	@Override
//	public boolean insertOrderDetails(OrderDetailsBean insertThisOrderDetails) {
//		// Create new Session
//		System.out.println("BEGIN: OrdedrDetailsBean.insertOrderDetails(OrderDetailsBean)");
//		Session session = sessionFactory.getCurrentSession();
//		// Test if passed OrderDtailsBean is not empty
//		if (insertThisOrderDetails != null) {
//			System.out.println("insertThisOrderDatails != null");
//			// Insert the passed OrderDetailsBean
//			session.save(insertThisOrderDetails);
//			// Console print results
//			System.out.println("OrderDetailsBean Inserted: ");
//			System.out.println("OrderDetailID: " + insertThisOrderDetails.getOrderDetailID());
//			System.out.println("ProductID: " + insertThisOrderDetails.getProductID());
//			System.out.println("ProductCount: " + insertThisOrderDetails.getProductCount());
//			System.out.println("OrderID: " + insertThisOrderDetails.getOrderID());
//
//			System.out.println("FINISH: OrderDetailsBean.insertOrderDetails(OrderDetailsBean)");
//			// Return True, for SUCCESSFUL INSERT
//			return true;
//		}
//		System.out.println(
//				"ERROR: OrderBeanDetails.insertOrderDetails(OrderDetailsBean) FAILED; insertThisOrderDetails == null.");
//		System.out.println("FINISH: OrderDetailsBeanDAO.insertOrderDetails(OrderDetailsBean)");
//		// Return False, for FAILED INSERT
//		return false;
//	}
//
//	@Override
//	@SuppressWarnings("rawtypes")
//	public OrderDetailsBean selectOrderDetails(int orderID) {
//		// Create new Session
//		System.out.println("BEGIN: OrderBeanDAO.selectOrder(OrderBean)");
//
//		System.out.println("Looking for OrderDetail with Order ID:" + orderID);
//
//		Session session = sessionFactory.getCurrentSession();
//		String hqlString = "FROM OrderDetailBean WHERE OrderID=thisOrderID";
//		Query q = session.createQuery(hqlString);
//		q.setParameter("thisOrderID", orderID);
//		OrderDetailsBean existingOrderDetail = (OrderDetailsBean) q.uniqueResult();
//
//		if (existingOrderDetail != null) {
//			System.out.println("OrderDetail found: ");
//			System.out.println("OrderDetail ID: " + existingOrderDetail.getOrderDetailID());
//			System.out.println("Product ID: " + existingOrderDetail.getProductID());
//			System.out.println("Product Count: " + existingOrderDetail.getProductCount());
//			System.out.println("Order ID: " + existingOrderDetail.getOrderID());
//
//			System.out.println("FINISH: OrderBeanDAO.selectOrder(OrderBean)");
//			return existingOrderDetail;
//		} else {
//			System.out.println("ERROR: OrderDetail NOT FOUND");
//			System.out.println("OrderDetailsBean not found, query returned NULL");
//			System.out.println("FINISH: OrderBeanDAO.selectOrder(OrderBean)");
//			return null;
//		}
//
//	}
//
//	public OrderDetailsBean selectOrderDetails(OrderDetailsBean bean) {
//		// Create new Session
//		System.out.println("BEGIN: OrderBeanDAO.selectOrder(OrderBean)");
//
//		System.out.println("Looking for OrderDetail with Order ID:" + bean.getOrderID());
//
//		Session session = sessionFactory.getCurrentSession();
//		String hqlString = "FROM OrderDetailBean WHERE OrderID=thisOrderID";
//		Query q = session.createQuery(hqlString);
//		q.setParameter("thisOrderID", bean.getOrderID());
//		OrderDetailsBean existingOrderDetail = (OrderDetailsBean) q.uniqueResult();
//
//		if (existingOrderDetail != null) {
//			System.out.println("OrderDetail found: ");
//			System.out.println("OrderDetail ID: " + existingOrderDetail.getOrderDetailID());
//			System.out.println("Product ID: " + existingOrderDetail.getProductID());
//			System.out.println("Product Count: " + existingOrderDetail.getProductCount());
//			System.out.println("Order ID: " + existingOrderDetail.getOrderID());
//
//			System.out.println("FINISH: OrderBeanDAO.selectOrder(OrderBean)");
//			return existingOrderDetail;
//		} else {
//			System.out.println("ERROR: OrderDetail NOT FOUND");
//			System.out.println("OrderDetailsBean not found, query returned NULL");
//			System.out.println("FINISH: OrderBeanDAO.selectOrder(OrderBean)");
//			return null;
//		}
//
//	}
//	
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public List<OrderDetailsBean> selectAll() {
//		System.out.println("BEGIN: OrderDetailsBeanDAO.selectAll()");
//		Session session = sessionFactory.getCurrentSession();
//
//		Query query = session.createQuery("From OrderDetailsBean");
//
//		List<OrderDetailsBean> results = (List<OrderDetailsBean>) query.list();
//
//		System.out.println("selectAll:" + results.get(0).getClass());
//		System.out.println("Found Order Details results:");
//		for (int index = 0; index < results.size(); index++) {
//			System.out.println("#" + index);
//			System.out.println(results.get(index).getOrderDetailID());
//			System.out.println(results.get(index).getProductID());
//			System.out.println(results.get(index).getProductCount());
//			System.out.println(results.get(index).getOrderID());
//		}
//		System.out.println("FINISH: OrderBeanDAO.selectAll()");
//		return results;
//	}
//
//	@Override
//	public boolean updateProductID(OrderDetailsBean updateThisOrderDetail, int newProductID) {
//		// Get current Session
//		System.out.println(
//				"Begin: OrderDetailsBeanDAO.updateProductID(OrderDetailsBean updateThisOrderDetails, int newProductID)");
//		Session session = sessionFactory.getCurrentSession();
//		// Check if updateThisOrderDetails is null
//		if (updateThisOrderDetail != null) {
//			// Try to find updateThisOrderDetails TODO this need to change HQL
//			OrderDetailsBean existingOrderDetails = session.get(OrderDetailsBean.class,
//					updateThisOrderDetail.getOrderDetailID());
//			if (existingOrderDetails != null) {
//				// If found, update ProductID and return
//				int oldProductID = existingOrderDetails.getOrderDetailID();
//				existingOrderDetails.setProductID(newProductID);
//				System.out.println(
//						"Product ID updated from " + oldProductID + " to " + existingOrderDetails.getProductID());
//				System.out.println(
//						"Finish: OrderDetailsBeanDAO.updateProductID(OrderDetailsBean updateThidOrderDetails, int newProductID)");
//				return true;
//			}
//		}
//		// Return False
//		System.out.println(
//				"Finish: OrderDetailsBeanDAO.updateProductID(OrderDetailsIDBean updateThisOrderDetails, int newProductID)");
//		return false;
//	}
//
//	@Override
//	public boolean updateProductCount(OrderDetailsBean updateThisOrderDetail, int newProductCount) {
//		// Get current Session
//		System.out.println(
//				"Begin: OrderDetailsBeanDAO.updateProductCount(OrderDetailsBean updateThisOrderDetails, int newProductCount)");
//		Session session = sessionFactory.getCurrentSession();
//		// Check if updateThisOrderDetails is null
//		if (updateThisOrderDetail != null) {
//			// Try to find updateThisProduct
//			OrderDetailsBean existingOrderDetails = session.get(OrderDetailsBean.class,
//					updateThisOrderDetail.getOrderDetailID());
//			if (existingOrderDetails != null) {
//				// If found, update ProductCount and return
//				int oldProductCount = existingOrderDetails.getProductCount();
//				existingOrderDetails.setProductCount(newProductCount);
//				System.out.println("Product Count updated from " + oldProductCount + " to "
//						+ existingOrderDetails.getProductCount());
//				System.out.println(
//						"Finish: OrderDetailsBeanDAO.updateProductCount(OrderDetailsBean updateThisOrderDetail, int newProductCount)");
//			}
//		}
//		System.out.println(
//				"Finsih: ProductBean.updateProductStock(OrderDetailBean updateThisOrderDetail, int newProductCount)");
//		return false;
//	}
//
//	@SuppressWarnings("rawtypes")
//	@Override
//	public boolean updateOrderID(OrderDetailsBean updateThisOrderDetail, int newOrderID) {
//		System.out.println("BEGIN: OrderDetailsBeanDAO.updateOrderID(OrderDetailsBean, int)");
//		// Check if updateThisProduct is null
//		if (updateThisOrderDetail != null) {
//			// Try to find updateThisProduct
//			Session session = sessionFactory.getCurrentSession();
//			String hqlString = "from OrderDetailBean where orderID=:thisOrderID and productID=:thisProductID";
//			Query q = session.createQuery(hqlString);
//			q.setParameter("thisOrderID", updateThisOrderDetail.getOrderID());
//			q.setParameter("thisProductID", updateThisOrderDetail.getProductID());
//			OrderDetailsBean results = (OrderDetailsBean) q.uniqueResult();
//			if (results != null) {
//				// If found, update OrderID and return True
//				System.out.println("OrderDetail found: ");
//				System.out.println("OrderDetail ID: " + results.getOrderDetailID());
//				System.out.println("Product ID: " + results.getProductID());
//				System.out.println("Product Count: " + results.getProductCount());
//				System.out.println("Order ID: " + results.getOrderID());
//				System.out.println("");
//				int oldOrderID = results.getOrderID();
//				results.setOrderID(newOrderID);
//				System.out.println("Order Details updated! New Order Details:");
//				System.out.println("OrderDetail ID: " + results.getOrderDetailID());
//				System.out.println("Product ID: " + results.getProductID());
//				System.out.println("Product Count: " + results.getProductCount());
//				System.out.println("Order ID: " + oldOrderID + " to " + results.getOrderID());
//				System.out.println("FINISH: OrderDetailsBeanDAO.updateOrderID(OrderDetailsBean, int)");
//				return true;
//			} else {
//				System.out.println("OrderDetail NOT FOUND!");
//			}
//		}
//		System.out.println("FINISH: OrderDetailsBeanDAO.updateOrderID(OrderDetailsBean, int)");
//		return false;
//	}
//
//	@Override
//	public boolean deleteOrderDetails(OrderDetailsBean deteleThisOrderDetails) {
//		System.out.println("BEGIN: OrderDetailsBeanDAO.deleteOrderDetails(OrderDetailsBean)");
//		try {
//			// GET current Session
//			Session session = sessionFactory.getCurrentSession();
//			// Check if deleteThisOrderDetails is null
//			if (deteleThisOrderDetails != null) {
//				// Try to find deleteThisOrderDetails
//				OrderDetailsBean resultBean = session.get(OrderDetailsBean.class,
//						deteleThisOrderDetails.getOrderDetailID());
//				if (resultBean != null) {
//					// If found, delete, return True
//					int deletedOrderDetailID = resultBean.getOrderDetailID();
//					int deletedProductID = resultBean.getProductID();
//					int deletedProductCount = resultBean.getProductCount();
//					int deletedOrderID = resultBean.getOrderID();
//					session.delete(resultBean);
//					System.out.println("OrderDetails Successfully DELETED:");
//					System.out.println("OrderDetail ID: " + deletedOrderDetailID);
//					System.out.println("OrderDetail Product ID " + deletedProductID);
//					System.out.println("OrderDetail Product count" + deletedProductCount);
//					System.out.println("OrderDetail Order ID " + deletedOrderID);
//					return true;
//				} else {
//					System.out.println("OrderDetails NOT FOUND");
//				}
//			} else {
//				System.out.println("ERROR: DeleteOrderDetails was passed null OrderDetailsBean");
//				return false;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("ERROR: Exception! See Console for details.");
//		return false;
//	}
//
//}
