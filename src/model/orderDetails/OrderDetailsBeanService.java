package model.orderDetails;

import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@WebServlet("/orderDetails/orderDetails.do")
public class OrderDetailsBeanService {
	// Variables: Local Fields
	private OrderDetailsBeanDAO odDAO;
	
	// Constructors
	@Autowired
	public OrderDetailsBeanService(OrderDetailsBeanDAO odDAO) {
		this.odDAO = odDAO;
	}
	
	// Test validity of OrderDetailsBean user input
	public boolean insert(OrderDetailsBean thisBean) {
		return odDAO.insertOrderDetails(thisBean);
	}
	
	public OrderDetailsBean select(OrderDetailsBean thisBean) {
		return odDAO.selectOrderDetails(thisBean);
	}
	
	public boolean delete(OrderDetailsBean thisBean) {
		return odDAO.deleteOrderDetails(thisBean);
	}
}
