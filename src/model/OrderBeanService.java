package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.CheckSubstring;

@Service
public class OrderBeanService {
	// Variables: Local Fields
	private OrderBeanDAO oDAO;

	// Constructors
	@Autowired
	public OrderBeanService(OrderBeanDAO oDAO) {
		this.oDAO = oDAO;
	}

	// Test validity of OrderBean user input
	// Validity means making sure values make sense
	public boolean insert(OrderBean insertThisBean) {
		System.out.println("BEGIN: OrderBeanService.insert(OrderBean oDAO)");
		// Local variables
		boolean success = false;
		String address = insertThisBean.getMailingAddress();
		String phone = insertThisBean.getMailingPhone();
		// Validate input values
		if (validateAddress(address) && validatePhone(phone)) {
			oDAO.insertOrder(insertThisBean);
		}
		System.out.println("FINISH: OrderBeanService.insert(OrderBean oDAO)");
		return success;
	}

	private static boolean validateAddress(String thisAddress) {
		boolean valid = false;
		try {
			CheckSubstring util = new CheckSubstring();
			int countSpec = util.countSpecialCharacters(thisAddress);
			if(countSpec >1) {
				
			}
		} catch (Exception e) {

		}
		return valid;
	}

	private static boolean validatePhone(String thisPhone) {
		boolean valid = false;
		try {

		} catch (Exception e) {

		}
		return valid;
	}
}