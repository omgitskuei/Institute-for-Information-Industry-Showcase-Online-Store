package model.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ProficeService implements ProfileBeanServiceInterface {
	
	@Autowired
	private ProfileBeanDAO profileDAO;
	
	@Override
	@Transactional
	public List<ProfileBean> getProfiles() {
		// TODO Auto-generated method stub
		return profileDAO.getProfiles();
	}

	@Override
	@Transactional
	public void saveProfile(ProfileBean theProfile) {
		profileDAO.saveProfile(theProfile);
	}

	@Override
	@Transactional
	public ProfileBean getProfile(int userId) {
		return profileDAO.getProfile(userId);
	}

	@Override
	@Transactional
	public void deleteProfile(int userId) {
		profileDAO.deleteProfile(userId);
	}

}
