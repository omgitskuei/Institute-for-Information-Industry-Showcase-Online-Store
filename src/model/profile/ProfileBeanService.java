package model.profile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.user.UserBean;

@Service
public class ProfileBeanService implements ProfileBeanServiceInterface {
	
	@Autowired
	private ProfileBeanDAO profileDAO;
	
	@Override
	public List<ProfileBean> getProfiles() {
		return profileDAO.getProfiles();
	}

	@Override
	public void saveProfile(ProfileBean theProfile) {
		profileDAO.saveProfile(theProfile);
	}
	
	@Override
	public void updateProfile(ProfileBean theProfile) {
		profileDAO.updateProfile(theProfile);
	}

	@Override
	public ProfileBean getProfile(int userID) {
		return profileDAO.getProfile(userID);
	}
	
//	public ProfileBean getProfileByUserID(int userID) {
//		return profileDAO.getProfileByUserID(userID);
//	}

	public ArrayList<ProfileBean> selectFuzzy(String searchQuery) {
		System.out.println("	BEGIN: ProfileBeanService.selectFuzzy");
		ArrayList<ProfileBean> results = profileDAO.selectFuzzy(searchQuery);
		System.out.println("	FINISH: ProfileBeanService.selectFuzzy");
		return results;
	}
	
//	@Override
//	public void deleteProfile(int userID) {
//		profileDAO.deleteProfile(userID);
//	}

}
