package model.order;

import java.util.List;

public interface OrderBeanServiceInterface {

	public List<OrderBean> selectAll();

	public void insertOrder(OrderBean insertThisOrder);

	public OrderBean selectOrder(int orderID);

	public void deleteOrder(int orderID);

	public boolean insert(OrderBean insertThisBean);

	public boolean updateAddress(OrderBean thisBean);

	public boolean updatePhone(OrderBean thisBean);

}
