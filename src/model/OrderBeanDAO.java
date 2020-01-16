package model;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class OrderBeanDAO {

	private SessionFactory sessionFactory;
	
	@Autowired
	public OrderBeanDAO(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	public OrderBean insert(OrderBean bean) {
		
		return null;
	}
	
}
