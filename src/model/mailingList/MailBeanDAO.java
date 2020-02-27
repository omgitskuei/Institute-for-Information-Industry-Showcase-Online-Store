package model.mailingList;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MailBeanDAO {
	// Variables
	private SessionFactory sessionFactory;
	//private Session session;

	// Constructors
	@Autowired
	public MailBeanDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// Methods
	public boolean insertMail(MailBean bean) {
		System.out.println("		BEGIN: MailBeanDAO.insertMail(MailBean)");
		Session session = sessionFactory.getCurrentSession();
		boolean result;
		if (bean != null) {
			session.save(bean);
			result = true;
		} else {
			result = false;
		}
		System.out.println("		FINISH: MailBeanDAO.insertMail(MailBean)");
		return result;
	}

	@SuppressWarnings("rawtypes")
	public MailBean selectMail(String email) { // READ ONLY
		System.out.println("		BEGIN: MailBeanDAO.selectMail(String)");
		// Check if selecThisUser is null
		try {
			if (email != null) {
				Session session = sessionFactory.getCurrentSession();
				String hqlString = "from MailBean where email=:email";
				Query q = session.createQuery(hqlString);
				q.setParameter("email", email);
				MailBean result = (MailBean) q.uniqueResult();
				System.out.println("			selectMail Query RESULT:");
				// UserBean existingUser = session.get(UserBean.class,
				// selectThisUser.getUserEmail());
				if (result != null) {
					System.out.println("				MailingListID:　" + result.getMailingListID());
					System.out.println("				Email:　" + result.getEmail());
					// clone a copy of the selected bean so that the database row doesn't get
					// affected by the decryption
					MailBean clonedBean = new MailBean();
					clonedBean.setMailingListID(result.getMailingListID());
					clonedBean.setEmail(result.getEmail());
					// ---------------------------> RETURN THE CLONE BEAN
					// <--------------------------
					System.out.println("			User Found");
					System.out.println("		Finish: UserBeanDAO.selecUser(UserBean)");
					return clonedBean;
				} else {
					// ---------------------------> RETURN THE EMPTY BEAN
					// <--------------------------
					System.out.println("		MailBean NOT FOUND - Returning nullBean(0, domain@example.com)");
					System.out.println("	Finish: UserBeanDAO.selecUser(UserBean)");
				}
			} else {
				System.out.println("		Passed User is NULL - Returning nullBean(0, domain@example.com)");
				System.out.println("	Finish: UserBeanDAO.selecUser(UserBean)");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("		EXCEPTION - Returning nullBean(0, domain@example.com)");
			System.out.println("	Finish: UserBeanDAO.selecUser(UserBean)");
		}
		MailBean nullBean = new MailBean();
		nullBean.setMailingListID(0);
		nullBean.setEmail("domain@example.com");
		return nullBean;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<MailBean> selectAllMail() {
		System.out.println("		BEGIN: MailBeanDAO.selectAllMail()");
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("From MailBean"); // This 'From' references UserBean.java
		List<MailBean> results = (List<MailBean>) query.list();
		System.out.println("			SelectAll: " + results.get(0).getClass());
		// Print List results into console (for debugging)
		for (int index = 0; index < results.size(); index++) {
			System.out.println("				"+results.get(index).getMailingListID());
			System.out.println("				"+results.get(index).getEmail());
		}
		System.out.println("		FINISH: MailBeanDAO.selectAllMail()");
		return results;
	}
	
}
