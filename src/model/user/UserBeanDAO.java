package model.user;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UserBeanDAO implements UserBeanDAOInterface {

	// Variables: Local Fields
	private SessionFactory sessionFactory;

	@Autowired
	public UserBeanDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertUser(UserBean insertThisUser) {
		// Create new Session
		System.out.println("BEGIN: UserBeanDAO.insertUser(UserBean insertThisUser)");
		Session session = sessionFactory.getCurrentSession();
		// Test if passed UserBean is not empty
		if (insertThisUser != null) {
			System.out.println("insertThisUser != null");
			// Insert the passed UserBean
			session.save(insertThisUser);
			System.out.println("UserBean Inserted:");
			System.out.println("userEmail: " + insertThisUser.getUserEmail());
			System.out.println("userPwd: " + insertThisUser.getUserPwd());
			System.out.println("FINISH: UserBeanDAO.insertUser(UserBean insertThisUser)");
			// Return True, for SUCCESSFUL INSERT
			return true;
		}
		System.out.println("ERROR: Insert UserBean FAILED; UserBean insertThisUser == null.");
		System.out.println("FINISH: UserBeanDAO.insertUser(UserBean insertThisUser)");
		// Return False, for FAILED INSERT
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public UserBean selectUser(UserBean selectThisUser) {
		// Get current Session
		System.out.println("Begin: UserBeanDAO.selectUser(UserBean selectThisUser)");
		// Check if selecThisUser is null
		try {
			if (selectThisUser != null) {
				// Try to find selectThisUser
				System.out.println("QUERY:");
				System.out.println("UserID:"+selectThisUser.getUserID());
				System.out.println("UserEmail:"+selectThisUser.getUserEmail());
				System.out.println("UserPwd:"+selectThisUser.getUserPwd());
				System.out.println("UserAdmin:"+selectThisUser.getAdmin());
				
				Session session = sessionFactory.getCurrentSession();
				String hqlString = "from UserBean where userEmail=:thisEmail and userPwd=:thisPwd";
				Query q = session.createQuery(hqlString);
				q.setParameter("thisEmail", selectThisUser.getUserEmail());
				q.setParameter("thisPwd", selectThisUser.getUserPwd());
				UserBean existingUser = (UserBean) q.uniqueResult();
				
				System.out.println("Query RESULTS:");
				System.out.println("UserID:"+existingUser.getUserID());
				System.out.println("UserEmail:"+existingUser.getUserEmail());
				System.out.println("UserPwd:"+existingUser.getUserPwd());
				System.out.println("UserAdmin:"+existingUser.getAdmin());
				
				//UserBean existingUser = session.get(UserBean.class, selectThisUser.getUserEmail());
				if (existingUser != null) {
					// If found, return the result UserBean existingUser
					System.out.println("User Found - Returning User "+ existingUser.getUserID());
					System.out.println("Finish: UserBeanDAO.selecUser(UserBean selectThisUser)");
					return existingUser;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// existingUser returned null meaning selectThisUser was not found
		System.out.println("updateThisUser OR existingUser was NULL");
		System.out.println("Finish: UserBeanDAO.selecUser(UserBean selectThisUser)");
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public int selectUserIDByEmail(String email) {
		System.out.println("BEGIN: UserBeanDAO.selectUserIDByEmail(String)");
		System.out.println("Looking for user with this email:"+email);

		Session session = sessionFactory.getCurrentSession();
		String hqlString = "from UserBean where userEmail=:thisEmail";
		Query q = session.createQuery(hqlString);
		q.setParameter("thisEmail", email);
		UserBean existingUser = (UserBean) q.uniqueResult();
		
		if (existingUser != null) {
			int userID = existingUser.getUserID();
			System.out.println("Result: "+userID);
			System.out.println("FINISH: UserBeanDAO.selectUserIDByEmail(String)");
			return userID;
		} else {
			System.out.println("User with this email ["+email+"] NOT FOUND.");
			System.out.println("Result: 0");
			System.out.println("FINISH: UserBeanDAO.selectUserIDByEmail(String)");
			return 0;
		}
	}

	// Override tag is only used if supertype UserBeanDAOInterface ...
	// ... also has this method.
	// @Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<UserBean> selectAll() {
		// Get current Session
		System.out.println("Begin: UserBeanDAO.selectAll()");
		Session session = sessionFactory.getCurrentSession();
		// Get all rows from UsersTable
		Query query = session.createQuery("From UserBean"); // This 'From' references UserBean.java
		// Store query results into List results
		List<UserBean> results = (List<UserBean>) query.list();
		System.out.println("SelectAll: " + results.get(0).getClass());
		// Print List results into console (for debugging)
		for (int index = 0; index < results.size(); index++) {
			System.out.println(results.get(index).getUserID());
			System.out.println(results.get(index).getUserEmail());
			System.out.println(results.get(index).getUserPwd());
			System.out.println(results.get(index).getAdmin());
			System.out.println();
		}
		// Return List results
		System.out.println("Finish: UserBeanDAO.selectAll()");
		return results;
	}

	@Override
	public boolean updateEmail(UserBean updateThisUser, String newEmail) {
		// Get current Session
		System.out.println("Begin: UserBeanDAO.updateEmail(UserBean updateThisUser, String newEmail)");
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisUser is null
		if (updateThisUser != null) {
			// Try to find updateThisUser
			UserBean existingUser = session.get(UserBean.class, updateThisUser.getUserID());
			if (existingUser != null) {
				// If found, update Email and return True
				String oldEmail = existingUser.getUserEmail();
				existingUser.setUserEmail(newEmail);
				System.out.println("User email " + oldEmail + " updated to " + existingUser.getUserEmail());
				System.out.println("Finish: UserBeanDAO.updateEmail(UserBean updateThisUser, String newEmail)");
				return true;
			}
		}
		// Return False because 1) updateThisUser was null OR 2) existingUser was null
		System.out.println("updateThisUser OR existingUser was NULL");
		System.out.println("Finish: UserBeanDAO.updateEmail(UserBean updateThisUser, String newEmail)");
		return false;
	}

	@Override
	public boolean updatePwd(UserBean updateThisUser, String newPwd) {
		// Get current Session
		Session session = sessionFactory.getCurrentSession();
		System.out.println("Begin: UserBeanDAO.updatePwd(UserBean updateThisUser, String newPwd)");
		// Check if updateThisUser is null
		if (updateThisUser != null) {
			// Try to find updateThisUser
			UserBean existingUser = session.get(UserBean.class, updateThisUser.getUserID());
			if (existingUser != null) {
				// If found, update Pwd and return True
				String oldPwd = existingUser.getUserPwd();
				existingUser.setUserPwd(newPwd);
				System.out.println("User password UPDATED " +oldPwd+ " to " + existingUser.getUserPwd());
				System.out.println("Finish: UserBeanDAO.updatePwd(UserBean updateThisUser, String newPwd)");
				return true;
			}
		}
		// Return False because 1) updateThisUser was null OR 2) existingUser was null
		System.out.println("updateThisUser OR existingUser was NULL");
		System.out.println("Finish: UserBeanDAO.updatePwd(UserBean updateThisUser, String newPwd)");
		return false;
	}

	@Override
	public boolean deleteUser(UserBean deleteThisUser) {
		// Get current Session
		System.out.println("Begin: UserBeanDAO.deleteUser(UserBean deleteThisUser)");
		Session session = sessionFactory.getCurrentSession();
		// Check if deleteThisUser is null
		if (deleteThisUser != null) {
			// Try to find deleteThisUser
			UserBean existingUser = session.get(UserBean.class, deleteThisUser.getUserID());
			if (existingUser != null) {
				// If found, delete, return True
				int deletedUserID = existingUser.getUserID();
				String deletedUserEmail = existingUser.getUserEmail();
				String deletedUserPwd = existingUser.getUserPwd();
				int deletedUserAdmin = existingUser.getAdmin();
				session.delete(existingUser);
				System.out.println("User DELETED:");
				System.out.println("User ID: " + deletedUserID);
				System.out.println("User Email: " + deletedUserEmail);
				System.out.println("User Pwd: " + deletedUserPwd);
				System.out.println("User Admin: " + deletedUserAdmin);
				System.out.println("Finish: UserBeanDAO.deleteUser(UserBean deleteThisUser)");
				return true;
			}
		}
		// Return False because 1) updateThisUser was null OR 2) existingUser was null
		System.out.println("updateThisUser OR existingUser was NULL");
		System.out.println("Finish: UserBeanDAO.deleteUser(UserBean deleteThisUser)");
		return false;
	}

}
