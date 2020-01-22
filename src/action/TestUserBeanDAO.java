package action;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import model.user.UserBean;
import model.user.UserBeanDAO;

public class TestUserBeanDAO {

	public static void main(String[] args) {

		try {
			StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
			SessionFactory f = new MetadataSources(sr).buildMetadata().buildSessionFactory();
			//Session session = f.openSession();
			//Transaction t = session.beginTransaction();
			UserBeanDAO uDAO = new UserBeanDAO(f);
			
			UserBean u = new UserBean();
			u.setUserEmail("www.newUser123@gmail.com");
			u.setUserPwd("goodPassword");
			u.setAdmin(0);
			
			// Test UserBeanDAO.insertUser(UserBean)
			
			uDAO.insertUser(u);
			System.out.println("FINISH: UserBeanDAO.insertUser(UserBean)");
			
			// Test userBeanDAO.selectUser(UserBean)
			UserBean selectedUserBean = uDAO.selectUser(u);
			System.out.println(selectedUserBean.getUserID());
			System.out.println(selectedUserBean.getUserEmail());
			System.out.println(selectedUserBean.getUserPwd());
			System.out.println(selectedUserBean.getAdmin());
			
			// Close
			f.close();
			sr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
