package model.user;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import util.EncodeHexString;
import util.EncryptString;

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
			// encrypt
			EncryptString utilTink = new EncryptString();
			EncodeHexString utilHex = new EncodeHexString();
			byte[] cipher = utilTink.encryptGoogleTinkAEAD(insertThisUser.getUserPwd(), "OMGiloveyou");
			String encrypted = utilHex.byteArrayToHexString(cipher);
			insertThisUser.setUserPwd(encrypted);
			
			// Insert the passed UserBean
			session.save(insertThisUser);
			System.out.println("UserBean Inserted:");
			System.out.println("	userID"+insertThisUser.getUserID());
			System.out.println("	userEmail: " + insertThisUser.getUserEmail());
			System.out.println("	userPwd: " + insertThisUser.getUserPwd());
			System.out.println("	userAdmin: " + insertThisUser.getAdmin());
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
	public UserBean selectUser(UserBean beanWithEmailAndPwd) {			// clones result bean, AKA READ ONLY
		// Get current Session
		System.out.println("Begin: UserBeanDAO.selectUser(UserBean selectThisUser)");
		// Check if selecThisUser is null
		try {
			if (beanWithEmailAndPwd != null) {
				// Try to find selectThisUser
				System.out.println("	About to QUERY:");
				System.out.println("		UserID:"+beanWithEmailAndPwd.getUserID());
				System.out.println("		UserEmail:"+beanWithEmailAndPwd.getUserEmail());
				System.out.println("		UserPwd:"+beanWithEmailAndPwd.getUserPwd());
				System.out.println("		UserAdmin:"+beanWithEmailAndPwd.getAdmin());
				
				Session session = sessionFactory.getCurrentSession();
				String hqlString = "from UserBean where userEmail=:thisEmail";
				Query q = session.createQuery(hqlString);
				q.setParameter("thisEmail", beanWithEmailAndPwd.getUserEmail());
				//q.setParameter("thisPwd", beanWithEmailAndPwd.getUserPwd());
				
				UserBean existingUser = (UserBean) q.uniqueResult();
				System.out.println("	selectUser Query RESULT:");
				//UserBean existingUser = session.get(UserBean.class, selectThisUser.getUserEmail());
				if (existingUser != null) {
					System.out.println("		UserID:　"+existingUser.getUserID());
					System.out.println("		UserEmail:　"+existingUser.getUserEmail());
					System.out.println("		UserPwd:　"+existingUser.getUserPwd());
					System.out.println("		UserAdmin:　"+existingUser.getAdmin());
					// clone a copy of the selected bean so that the database row doesn't get affected by the decryption
					UserBean clonedBean = new UserBean();
					clonedBean.setUserID(existingUser.getUserID());
					clonedBean.setUserEmail(existingUser.getUserEmail());
					clonedBean.setUserPwd(existingUser.getUserPwd());
					clonedBean.setAdmin(existingUser.getAdmin());
					// decrypt password on the clone
					EncryptString util1 = new EncryptString();
					EncodeHexString hexConvert = new EncodeHexString();
					byte[] cipher = hexConvert.HexStringToByteArray(clonedBean.getUserPwd());
					clonedBean.setUserPwd(util1.decryptGoogleTinkAEAD(cipher, "OMGiloveyou"));
					// Compare decrypted password with passed password
					System.out.println("	Decrypted password: "+clonedBean.getUserPwd());
					
					
					// ---------------------------> RETURN THE CLONE BEAN <--------------------------
					System.out.println("User Found - Returning User "+ clonedBean.getUserID());
					System.out.println("Finish: UserBeanDAO.selecUser(UserBean)");
					return clonedBean;
				} else {
					// ---------------------------> RETURN THE EMPTY BEAN <--------------------------
					System.out.println("		User NOT FOUND - Returning nullBean(0, domain@example.com, Testing123!, 0)");
					System.out.println("Finish: UserBeanDAO.selecUser(UserBean)");
					UserBean nullBean = new UserBean();
					nullBean.setUserID(0);
					nullBean.setUserEmail("domain@example.com");
					nullBean.setUserPwd("Testing123!");
					nullBean.setAdmin(0);
					return nullBean;
				}
			} else {
				System.out.println("Passed User is NULL - Returning NULL");
				System.out.println("Finish: UserBeanDAO.selecUser(UserBean)");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION");
			System.out.println("Finish: UserBeanDAO.selecUser(UserBean)");
			return null;
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public UserBean selectUserByID(int userID) {		// clones result bean, AKA READ ONLY
		// Try to find selectThisUser
		System.out.println("	About to QUERY:");
		System.out.println("		UserID:"+userID);
		System.out.println("		UserEmail:");
		System.out.println("		UserPwd:");
		System.out.println("		UserAdmin:");
		
		Session session = sessionFactory.getCurrentSession();
		String hqlString = "from UserBean where userID=:thisID";
		Query q = session.createQuery(hqlString);
		q.setParameter("thisID", userID);
		//q.setParameter("thisPwd", beanWithEmailAndPwd.getUserPwd());
		
		UserBean existingUser = (UserBean) q.uniqueResult();
		System.out.println("	selectUser Query RESULT:");
		if (existingUser != null) {
			System.out.println("		UserID:　"+existingUser.getUserID());
			System.out.println("		UserEmail:　"+existingUser.getUserEmail());
			System.out.println("		UserPwd:　"+existingUser.getUserPwd());
			System.out.println("		UserAdmin:　"+existingUser.getAdmin());
			
			// clone a copy of the selected bean so that the database row doesn't get affected by the decryption
			UserBean clonedBean = new UserBean();
			clonedBean.setUserID(existingUser.getUserID());
			clonedBean.setUserEmail(existingUser.getUserEmail());
			clonedBean.setUserPwd(existingUser.getUserPwd());
			clonedBean.setAdmin(existingUser.getAdmin());
			
			//decrypt password
			EncryptString util1 = new EncryptString();
			EncodeHexString hexConvert = new EncodeHexString();
			byte[] cipher = hexConvert.HexStringToByteArray(clonedBean.getUserPwd());
			clonedBean.setUserPwd(util1.decryptGoogleTinkAEAD(cipher, "OMGiloveyou"));
			
			return clonedBean;
		} else {
			System.out.println("		USER NOT FOUND - returning null");
//			existingUser = new UserBean();
//			existingUser.setUserID(0);
//			existingUser.setUserEmail("");
//			existingUser.setUserPwd("");
//			existingUser.setAdmin(0);
			return null;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public int selectUserIDByEmail(String email) {		// only returns userID, not bean
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
			// decrypt password
			EncryptString util1 = new EncryptString();
			EncodeHexString hexConvert = new EncodeHexString();
			byte[] cipher = hexConvert.HexStringToByteArray(results.get(index).getUserPwd());
			results.get(index).setUserPwd(util1.decryptGoogleTinkAEAD(cipher, "OMGiloveyou"));
			System.out.println(results.get(index).getAdmin());
			System.out.println();
		}
		// Return List results
		System.out.println("Finish: UserBeanDAO.selectAll()");
		return results;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<UserBean> selectFuzzy(String searchQuery) {
		System.out.println("BEGIN: UserBeanDAO.selectFuzzy(String)");
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "From UserBean where userID like '%"+searchQuery+"%' or userEmail like '%"+searchQuery+"%'";
		Query query = session.createQuery(hql); 
		// Store query results into List results
		List<UserBean> results = (List<UserBean>) query.list();
		System.out.println("	# of results: " + results.size());
		// Return List results
		System.out.println("FINISH: UserBeanDAO.selectFuzzy(String)");
		return results;
	}
	
	@Override
	public boolean updateEmail(UserBean beanWithID, String newEmail) {
		// Get current Session
		System.out.println("Begin: UserBeanDAO.updateEmail(UserBean updateThisUser, String newEmail)");
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisUser is null
		if (beanWithID != null) {
			// Try to find updateThisUser
			UserBean existingUser = session.get(UserBean.class, beanWithID.getUserID());
			if (existingUser != null) {
				// If found, update Email and return True
				String oldEmail = existingUser.getUserEmail();
				existingUser.setUserEmail(newEmail);
				session.update(existingUser);
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
	public boolean updatePwd(UserBean beanWithID, String newPwd) {
		// Get current Session
		Session session = sessionFactory.getCurrentSession();
		System.out.println("Begin: UserBeanDAO.updatePwd(UserBean updateThisUser, String newPwd)");
		// Check if updateThisUser is null
		if (beanWithID != null) {
			// Try to find updateThisUser
			UserBean existingUser = session.get(UserBean.class, beanWithID.getUserID());
			if (existingUser != null) {
				// If found, update Pwd and return True
				String oldPwd = existingUser.getUserPwd();
				// encrypt password
				EncryptString util1 = new EncryptString();
				EncodeHexString hexConvert = new EncodeHexString();
				byte[] cipher = util1.encryptGoogleTinkAEAD(newPwd, "OMGiloveyou");
				String encrypted = hexConvert.byteArrayToHexString(cipher);
				
				existingUser.setUserPwd(encrypted);
				session.update(existingUser);
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
	public boolean deleteUser(UserBean beanWithEmail) {
		// Get current Session
		System.out.println("Begin: UserBeanDAO.deleteUser(UserBean)");
		// Check if deleteThisUser is null
		if (beanWithEmail != null) {
			// Try to find deleteThisUser
			Session session = sessionFactory.getCurrentSession();
			String hqlString = "from UserBean where userEmail=:thisEmail";
			Query q = session.createQuery(hqlString);
			q.setParameter("thisEmail", beanWithEmail.getUserEmail());
			UserBean existingUser = (UserBean) q.uniqueResult();
			if (existingUser != null) {
				// If found, delete, return True
				int deletedUserID = existingUser.getUserID();
				String deletedUserEmail = existingUser.getUserEmail();
				String deletedUserPwdEncrpted = existingUser.getUserPwd();
				// decrypt password
				EncryptString util1 = new EncryptString();
				EncodeHexString hexConvert = new EncodeHexString();
				byte[] cipher = hexConvert.HexStringToByteArray(deletedUserPwdEncrpted);
				String deletedUserPwd = util1.decryptGoogleTinkAEAD(cipher, "OMGiloveyou");
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
