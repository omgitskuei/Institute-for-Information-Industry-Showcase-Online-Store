package model.profile;

import java.util.ArrayList;
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

		// TODO JOIN DATE, there is a type problem
		Date birthDate = newProfile.getProfileBirthdate();

		boolean success = false;
		// Validate values
		if (validateFullName(fullName) && validateSex(sex) && validatePhone(phone) && validateAddress(address)
				&& validBirthdate(birthDate)) {
			pDAO.insertProfile(newProfile);
		}

		System.out.println("FINISH: ProfileBeanService.insert(ProfileBean newProfile)");
		return success;
	}

	public ProfileBean select(ProfileBean newProfile) {
		ProfileBean results = new ProfileBean();
		results = pDAO.selectProfile(newProfile);
		return results;
	}

	public boolean updateFullName(ProfileBean thisPB) {
		boolean success = false;
		// See if thisPB values are valid
		String newFullName = thisPB.getProfileFullName();
		if( validateFullName(newFullName) ) {
			ProfileBean result = pDAO.selectProfile(thisPB);
			success = pDAO.updateFullName(result, newFullName );
		}
		// See if thisPB exists in DB
		return success;
	}
	
	public boolean updateAddress(ProfileBean thisPB) {
		boolean success = false;
		// See if thisPB values are valid
		String newAddress = thisPB.getProfileAddress();
		if( validateAddress(newAddress) ) {
			ProfileBean result = pDAO.selectProfile(thisPB);
			success = pDAO.updateFullName(result, newAddress );
		}
		// See if thisPB exists in DB
		return success;
	}

	public boolean delete(ProfileBean thisPB) {
		boolean success = false;
		// See if thisPB exists in DB

		return success;
	}

	private static boolean validateFullName(String fullName) {
		boolean valid = false;
		if (fullName != null) {
			try {
				CheckSubstring util = new CheckSubstring();
				int countSpec = util.countSpecialCharacters(fullName);
				int countNum = util.countNums(fullName);
				ArrayList<String> dotIndexes = util.delimitAtDot(fullName);
				if (countSpec == 0 && countNum == 0 && dotIndexes.size() == 0) {
					valid = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return valid;
		} else {
			return valid;
		}
	}

	public boolean validBirthdate(Date birthDate) {
		boolean valid = false;

		try {
			if (birthDate != null) {
				valid = true;
			} else {
				valid = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid;
	}

	private static boolean validateSex(String sex) {
		boolean valid = false;
		if (sex != null) {
			try {
				CheckSubstring util = new CheckSubstring();
				int countSpec = util.countSpecialCharacters(sex);
				// Sex is a letter "m", "f", etc. No Special characters, only 1 letter
				if (countSpec == 0 && sex.length() == 1) {
					valid = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return valid;
		} else {
			return valid;
		}
	}

	private static boolean validatePhone(String phone) {
		boolean valid = false;
		if (phone != null) {
			try {
				// checks to make sure only numbers have been entered
				CheckSubstring util = new CheckSubstring();
				if (util.countNums(phone) == phone.length()) {
					valid = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return valid;
		} else {
			return valid;
		}
	}

	private static boolean validateAddress(String address) {
		boolean valid = false;
		if (address != null) {
			try {
				CheckSubstring util = new CheckSubstring();
				int countSpec = util.countSpecialCharacters(address);
				if (countSpec > 1) {
					valid = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return valid;
		} else {
			return valid;
		}
	}

}
