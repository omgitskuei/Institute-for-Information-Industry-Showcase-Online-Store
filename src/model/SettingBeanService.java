package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.CheckSubstring;

@Service
public class SettingBeanService {
	// Variables: Local Fields
	private SettingBeanDAO sDAO;

	// Constructors
	@Autowired
	public SettingBeanService(SettingBeanDAO sDAO) {
		this.sDAO = sDAO;
	}

	// Test validity of SettingBean user input
	// Validity means making sure values make sense
	public boolean insert(SettingBean insertThisBean) {
		System.out.println("BEGIN: SettingBeanService.insert(SettingBean)");
		// Local variables
		boolean success = false;
		String displayName = insertThisBean.getSettingDisplayName();
		// Validate input values
		if (validateDisplayName(displayName)) {
			success = true;
			sDAO.insertSetting(insertThisBean);
		}
		System.out.println("FINISH: SettingBeanService.insert(SettingBean)");
		return success;
	}

	// Make sure Display Name doesn't have special Letters
	private static boolean validateDisplayName(String thisName) {
		boolean valid = false;
		try {
			// Count how many special characters
			CheckSubstring util = new CheckSubstring();
			int countSpec = util.countSpecialCharacters(thisName);
			if(countSpec==0) {
				valid = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid;
	}

}