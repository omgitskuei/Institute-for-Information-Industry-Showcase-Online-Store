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
	
    public SettingBean insert(SettingBean bean) {
		
		return null;
	}

}
