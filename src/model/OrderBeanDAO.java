package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class OrderBeanDAO implements OrderBeanDAOInterface {

	private SessionFactory sessionFactory;

	@Autowired
	public OrderBeanDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertOrder(OrderBean insertThisOrder) {
		System.out.println("BEGIN: OrderBeanDAO.insertOrder(OrderBean insertOrderBean)");

		Session session = sessionFactory.getCurrentSession();

		if (insertThisOrder != null) {
			System.out.println("insertThisOrder!=null");

			session.save(insertThisOrder);
			System.out.println("OrderBean inserted:");
			System.out.println("orderTotal:" + insertThisOrder.getTotal());
			System.out.println("orderMailingAddress:" + insertThisOrder.getMailingAddress());
			System.out.println("orderMailingPhone:" + insertThisOrder.getMailingPhone());
			System.out.println("orderOrderTime:" + insertThisOrder.getOrderTime());

			return true;
		}
		System.out.println("ERROR: insert OrderBean FAILED; OrderBean insertThisOrder==null");
		System.out.println("Finish: OrderBeanDAO.insertOrder(OrderBean insertThisOrder)");
		return false;
	}

	@Override
	public OrderBean selectOrder(OrderBean selectThisOrder) {
		System.out.println("Begin: OrderBeanDAO.selecUser(OrderBean selectThisOrder)");
		Session session = sessionFactory.getCurrentSession();
		if(selectThisOrder!=null) {
			OrderBean existingOrder=session.get(OrderBean.class, selectThisOrder.getOrderID());
			if(existingOrder!=null) {
				System.out.println("Finish: OrderBeanDAO.selectOrder(OrderBean selectThisOrder)");
				return existingOrder;
			}
		}
		System.out.println("Finish: OrderBeanDAO.selectOrder(OrderBean selectThisOrder)");
		return null;
	}

	@Override
	public boolean updateTotal(OrderBean updateThisOrder, int newTotal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateMailingAddress(OrderBean updateThisOrder, String newMailingAddress) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateMailingPhone(OrderBean updateThisOrder, String newMailingPhone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOrder(OrderBean deleteThisOrder) {
		// TODO Auto-generated method stub
		return false;
	}

}
