package model;

public interface OrderDetailsBeanDAOInterface {
	// An abstract list of all must-have methods for UserBeanDAO; CRUD
	// C.reate
	public boolean insertOrderDetails(OrderDetailsBean insertThisOrderDetails);
	// R.ead
	public OrderDetailsBean selectOrderDetails(OrderDetailsBean selectThisOrderDetails);
	// U.pdate
	public boolean updateProductID(OrderDetailsBean updateThisOrderDetail);
	public boolean updateProductCount(OrderDetailsBean updateThisOrderDetail);
	public boolean updateOrderID(OrderDetailsBean updateThisOrderDetail);
	// D.elete
	public boolean deleteOrderDetails(OrderDetailsBean deteleThisOrderDetails);
}
