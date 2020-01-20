package model;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class SettingBeanDAO implements SettingBeanDAOInterface {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public SettingBeanDAO(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	@Override
	public boolean insertSetting(SettingBean insertThisSetting) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SettingBean selectSetting(SettingBean selectThisSetting) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateSettingSecurityQ(SettingBean updateThisSetting, String newSettingSecurityQ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSettingSecurityA(SettingBean updateThisSetting, String newSettingSecurityA) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSettingDisplayName(SettingBean updateThisSetting, String newSettingDisplayName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSettingAllowMetadata(SettingBean updateThisSetting, boolean newSettingAllowMetadata) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSetting(SettingBean deleteThisSetting) {
		// TODO Auto-generated method stub
		return false;
	}
}
