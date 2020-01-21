package model;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class SettingBeanDAO implements SettingBeanDAOInterface {
	// local variable
	private SessionFactory sessionFactory;
	
	@Autowired
	public SettingBeanDAO(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	@Override
	public boolean insertSetting(SettingBean insertThisSetting) {
		System.out.println("BEGIN: SettingBeanDAO.insertSetting(SettingBean insertSettingBean)");
		Session session = sessionFactory.getCurrentSession();
		
		if (insertThisSetting != null) {
			System.out.println("insertThisSetting!=null");
			
			session.save(insertThisSetting);
			System.out.println("SettingBean inserted:");
			System.out.println("settingSecurityQ:" + insertThisSetting.getSettingSecurityQ());
			System.out.println("settingSecurityA:" + insertThisSetting.getSettingSecurityA());
			System.out.println("settingDisplayName:" + insertThisSetting.getSettingDisplayName());
			System.out.println("settingAllowMetadata:" + insertThisSetting.getSettingAllowMetadata());
			
			return true;
		}
		System.out.println("ERROR: insert SettingBean FAILED; SettingBean insertThisSetting==null");
		System.out.println("FINISH: SettingBeanDAO.insertSetting(SettingBean insertThisSetting)");
		return false;
	}

	@Override
	public SettingBean selectSetting(SettingBean selectThisSetting) {
		System.out.println("BEGIN: SettingBeanDAO.selectSetting(SettingBean selectThisSetting)");
		Session session = sessionFactory.getCurrentSession();
		if(selectThisSetting!=null) {
			SettingBean existingSetting=session.get(SettingBean.class, selectThisSetting.getSettingID());
			if(existingSetting!=null) {
				System.out.println("FINISH: SettingBeanDAO.selectSetting(OrderBean selectThisSetting)");
				return existingSetting;
			}
		}
		System.out.println("FINISH: SettingBeanDAO.selectSetting(OrderBean selectThisSetting)");
		return null;
	}
	
	@Override
	public boolean updateSettingSecurityQ(SettingBean updateThisSetting, String newSettingSecurityQ) {
		System.out.println("BEGIN: OrderBeanDAO.updateSettingSecurityQ(SettingBean updateThisSetting, String newSettingSecurityQ)");
		Session session = sessionFactory.getCurrentSession();
		if(updateThisSetting!=null) {
			SettingBean existingSetting=session.get(SettingBean.class, updateThisSetting.getSettingID());
			if(existingSetting!=null) {
				String oldSettingSecurityQ=existingSetting.getSettingSecurityQ();
				existingSetting.setSettingSecurityQ(newSettingSecurityQ);
				
				System.out.println("FINISH: SettingBean.updateSettingSecurityQ(SettingBean updateThisSetting, String newSettingSecurityQ)");
				return true;
			}
		}
		System.out.println("FINISH: SettingBean.updateSettingSecurityQ(SettingBean updateThisSetting, String newSettingSecurityQ)");
		
		return false;
	}

	@Override
	public boolean updateSettingSecurityA(SettingBean updateThisSetting, String newSettingSecurityA) {			
		System.out.println("BEGIN: SettingBeanDAO.updateSettingSecurityA(OrderBean updateThisSetting, String newSettingSecurityA)");
		Session session = sessionFactory.getCurrentSession();
		if(updateThisSetting!=null) {
			SettingBean existingSetting=session.get(SettingBean.class, updateThisSetting.getSettingID());
			if(existingSetting!=null) {
				String oldSettingSecurityA=existingSetting.getSettingSecurityA();
				existingSetting.setSettingSecurityA(newSettingSecurityA);
					
				System.out.println("FINISH: SettingBean.updateSettingSecurityA(SettingBean updateThisSetting, String newSettingSecurityA)");
				return true;
			}
		}
		System.out.println("FINISH: SettingBean.updateSettingSecurityA(SettingBean updateThisSetting, String newSettingSecurityA)");
			
		return false;
	}

	@Override
	public boolean updateSettingDisplayName(SettingBean updateThisSetting, String newSettingDisplayName) {
		System.out.println("BEGIN: SettingBeanDAO.updateSettingDisplayName(SettingBean updateThisSetting, String newSettingDisplayName)");
		Session session = sessionFactory.getCurrentSession();
		if(updateThisSetting!=null) {
			SettingBean existingSetting=session.get(SettingBean.class, updateThisSetting.getSettingID());
			if(existingSetting!=null) {
				String oldSettingDisplayName=existingSetting.getSettingDisplayName();
				existingSetting.setSettingDisplayName(newSettingDisplayName);
					
				System.out.println("FINISH: SettingBean.updateSettingDisplayName(SettingBean updateThisSetting, String newSettingDisplayName)");
				return true;
			}
		}
		System.out.println("FINISH: SettingBean.updateSettingDisplayName(SettingBean updateThisSetting, String newSettingDisplayName)");
			
		return false;
	}

	@Override
	public boolean updateSettingAllowMetadata(SettingBean updateThisSetting, boolean newSettingAllowMetadata) {
		System.out.println("BEGIN: SettingBeanDAO.updateSettingAllowMetadata(SettingBean updateThisSetting, boolean newSettingAllowMetadata)");
		Session session = sessionFactory.getCurrentSession();
		if(updateThisSetting!=null) {
			SettingBean existingSetting=session.get(SettingBean.class, updateThisSetting.getSettingID());
			if(existingSetting!=null) {
				boolean oldSettingAllowMetadata=existingSetting.getSettingAllowMetadata();
				existingSetting.setSettingAllowMetadata(newSettingAllowMetadata);
					
				System.out.println("FINISH: SettingBean.updateSettingAllowMetadata(SettingBean updateThisSetting, boolean newSettingAllowMetadata)");
				return true;
			}
		}
		System.out.println("FINISH: SettingBean.updateSettingDisplayName(SettingBean updateThisSetting, boolean newSettingAllowMetadata)");
			
		return false;
	}

	@Override
	public boolean deleteSetting(SettingBean deleteThisSetting) {
		System.out.println("BEGIN: SettingBeanDAO.deleteSetting(SettingBean deleteThisSetting)");
		Session session = sessionFactory.getCurrentSession();
		if(deleteThisSetting!=null) {
			SettingBean existingSetting=session.get(SettingBean.class, deleteThisSetting.getClass());
			if(existingSetting!=null) {
				int deleteSettingID=existingSetting.getSettingID();
				System.out.println("FINISH: "+deleteSettingID);
				
				int deleteUserID=existingSetting.getUserID();
				System.out.println("FINISH: "+deleteUserID);
				
				String deleteSettingSecurityQ=existingSetting.getSettingSecurityQ();
				System.out.println("FINISH: "+deleteSettingSecurityQ);
				
				String deleteSettingSecurityA=existingSetting.getSettingSecurityA();
				System.out.println("FINISH: "+deleteSettingSecurityA);
				
				String deleteSettingDisplayName=existingSetting.getSettingDisplayName();
				System.out.println("FINISH: "+deleteSettingDisplayName);
				
				boolean deleteSettingAllowMetadata=existingSetting.getSettingAllowMetadata();
				System.out.println("FINISH: "+deleteSettingAllowMetadata);
				
				session.delete(existingSetting);
				System.out.println("FINISH: SettingBeanDAO.deleteSetting(SettingBean deleteThisSetting)");
				return true;
			}
		}
		System.out.println("FINISH: SettingBeanDAO.deleteSetting(SettingBean deleteThisSetting)");
		return false;
	}

}