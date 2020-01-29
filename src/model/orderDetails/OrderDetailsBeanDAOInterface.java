package model.orderDetails;

public interface OrderDetailsBeanDAOInterface {
	// An abstract list of all must-have methods for UserBeanDAO; CRUD
	// C.reate
	public boolean insertOrderDetails(OrderDetailsBean insertThisOrderDetails);
	// R.ead
	public OrderDetailsBean selectOrderDetails(OrderDetailsBean selectThisOrderDetails);
	// U.pdate
	public boolean updateProductID(OrderDetailsBean updateThisOrderDetail, int newProductID);
	public boolean updateProductCount(OrderDetailsBean updateThisOrderDetail, int newProductCount);
	public boolean updateOrderID(OrderDetailsBean updateThisOrderDetail, int newOrderID);
	// D.elete
	public boolean deleteOrderDetails(OrderDetailsBean deteleThisOrderDetails);
}