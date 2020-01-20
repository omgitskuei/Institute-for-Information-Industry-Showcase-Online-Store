package model;

import java.util.Date;

public class ProfileBeanDAO implements ProfileBeanDAOInterface{

	@Override
	public boolean insertProfile(ProfileBean insertThisProfile) {
		System.out.println("Begin: ProfileBeanDAO.insertProfile(ProfileBean insertThisProfile)");
		
		System.out.println("Finish: ProfileBeanDAO.insertProfile(ProfileBean insertThisProfile)");
		return false;
	}

	@Override
	public ProfileBean selectProfile(ProfileBean insertThisProfile) {
		System.out.println("Begin: ProfileBeanDAO.selectProfile(ProfileBean insertThisProfile)");
		
		System.out.println("Finish: ProfileBeanDAO.selectProfile(ProfileBean insertThisProfile)");
		return null;
	}

	@Override
	public boolean updateFullName(ProfileBean updateThisProfile, String newFullName) {
		System.out.println("Begin: ProfileBeanDAO.updateFullName(ProfileBean updateThisProfile, String newFullName)");
		
		System.out.println("Finish: ProfileBeanDAO.updateFullName(ProfileBean updateThisProfile, String newFullName)");
		return false;
	}

	@Override
	public boolean updateBirthdate(ProfileBean updateThisProfile, Date newBirthdate) {
		System.out.println("Begin: ProfileBeanDAO.updateBirthdate(ProfileBean updateThisProfile, Date newBirthdate)");
		
		System.out.println("Finish: ProfileBeanDAO.updateBirthdate(ProfileBean updateThisProfile, Date newBirthdate)");
		return false;
	}

	@Override
	public boolean updateSex(ProfileBean updateThisProfile, String newSex) {
		System.out.println("Begin: ProfileBeanDAO.updateSex(ProfileBean updateThisProfile, String newSex)");
		
		System.out.println("Finish: ProfileBeanDAO.updateSex(ProfileBean updateThisProfile, String newSex)");
		return false;
	}

	@Override
	public boolean updatePhone(ProfileBean updateThisProfile, String newPhone) {
		System.out.println("Begin: ProfileBeanDAO.updatePhone(ProfileBean updateThisProfile, String newPhone)");
		
		System.out.println("Finish: ProfileBeanDAO.updatePhone(ProfileBean updateThisProfile, String newPhone)");
		return false;
	}

	@Override
	public boolean updateAddress(ProfileBean updateThisProfile, String newAddress) {
		System.out.println("Begin: ProfileBeanDAO.updateAddress(ProfileBean updateThisProfile, String newAddress)");
		
		System.out.println("Finish: ProfileBeanDAO.updateAddress(ProfileBean updateThisProfile, String newAddress)");
		return false;
	}

	@Override
	public boolean deleteProfile(ProfileBean deleteThisProfile) {
		System.out.println("Begin: ProfileBeanDAO.deleteProfile(ProfileBean deleteThisProfile)");
		
		System.out.println("Finish: UserBeanDAO.deleteUser(UserBean deleteThisUser)");
		return false;
	}

}
