package model.profile;

import java.util.Date;
import java.util.List;

public interface ProfileBeanDAOInterface {
	
	public List <ProfileBean> getProfiles();
	
	public void saveProfile(ProfileBean theProfile);
	
	public ProfileBean getProfile(int userId);
	
	public void deleteProfile(int userId);
}
