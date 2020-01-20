package model;

import java.util.Date;

public interface ProfileBeanDAOInterface {
	// An abstract list of all must-have methods for ProductBeanDAO; CRUD
	// C.reate
	public boolean insertProfile(ProfileBean insertThisProfile);
	// R.ead
	public ProfileBean selectProfile(ProfileBean insertThisProfile);
	// U.pdate
	public boolean updateFullName(ProfileBean updateThisProfile, String newFullName);
	public boolean updateBirthdate(ProfileBean updateThisProfile, Date newBirthdate);
	public boolean updateSex(ProfileBean updateThisProfile, String newSex);
	public boolean updatePhone(ProfileBean updateThisProfile, String newPhone);
	public boolean updateAddress(ProfileBean updateThisProfile, String newAddress);
	// D.elete
	public boolean deleteProfile(ProfileBean deleteThisProfile);
}
