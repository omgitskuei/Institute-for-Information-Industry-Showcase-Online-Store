package model;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.CheckSubstring;

@Service
public class ProfileBeanService {
	// Varibles: Local Fields
	private ProfileBeanDAO pDAO;

	// Constructors
	@Autowired
	public ProfileBeanService(ProfileBeanDAO pDAO) {
		this.pDAO = pDAO;
	}
	
	// Test validity of ProfileBean user input
	// Validity means making sure values make sense
	public boolean insert(ProfileBean newProfile) {
		System.out.println("BEGIN: ProfileBeanService.insert(ProfileBean newProfile)");
		// Get values from newProfile for validation
		String fullName = newProfile.getProfileFullName();
		String sex = newProfile.getProfileSex();
		String phone = newProfile.getProfilePhone();
		String address = newProfile.getProfileAddress();
		
		// TODO JOIN DATE LocalDateTime.now(), there is a type problemg
		Date birthDate = newProfile.getProfileBirthdate();
		
		
		boolean success = false;
		// Validate values
		// not yet check : joinDate, birthday, userVIP
		if (validateFullName(fullName) && validateSex(sex) && validatePhone(phone) && validateAddress(address) && validateBirthdate(birthdate)) {
			pDAO.insertProfile(newProfile);
		}
		
		System.out.println("FINISH: ProfileBeanService.insert(ProfileBean newProfile)");
		return success;
	}
	
	public boolean updateBirthdate(ProfileBean thisBean) {
		boolean valid = false;
		Date birthdate = thisBean.getProfileBirthdate();
		
		if (v) {
			pDAO.updateBirthdate(thisBean, birthdate);
		} else {
			;
		}
		return valid;
	}
	
	private static boolean validateFullName(String fullName) {
		boolean valid = false;
		try {
			CheckSubstring util = new CheckSubstring();
			int countSpec = util.countSpecialCharacters(fullName);
			if(countSpec==0) {
				valid = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid;
	}
	
	private static boolean validateSex(String sex) {
		boolean valid = false;
		try {
			CheckSubstring util = new CheckSubstring();
			int countSpec = util.countSpecialCharacters(sex);
			// Sex is a letter "m", "f", etc. No Special characters, only 1 letter
			if(countSpec==0 && sex.length()==1) {
				valid = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid;
	}
	
	private static boolean validatePhone(String phone) {
		boolean valid = false;
		try {
			// checks to make sure only numbers have been entered
			CheckSubstring util = new CheckSubstring();
			if(util.countNums(phone)==phone.length()) {
				valid = true;
			}
		} catch (Exception e) {

		}
		return valid;
	}
	
	private static boolean validateAddress(String address) {
		boolean valid = false;
		try {
			CheckSubstring util = new CheckSubstring();
			int countSpec = util.countSpecialCharacters(address);
			if(countSpec>1) {
				valid = true;
			}
		} catch (Exception e) {

		}
		return valid;
	}
	
	private static boolean validateBirth
	
}
