package model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UserBeanService {

	// Variables: Local Fields
	private SessionFactory sessionFactory;
	UserBeanDAO uDAO;
	
	// Constructors
	@Autowired
	public UserBeanService(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		// Create a new UserBeanDAO
		uDAO = new UserBeanDAO(sessionFactory);
	}
	
	// Test Validity* of newUser before doing UserBeanDAO.insertUser(UserBean)
	// Validity means checking all user inputs (userEmail, userPwd)
	public boolean insert(UserBean newUser) {
		System.out.println("BEGIN: UserBeanService.insert(UserBean insertThisUser)");
		// Check for valid userEmail
		newUser.getUserEmail().indexOf(".com");
		
		
		ArrayList<String> newUserEmail = new ArrayList<String>();
		for (int index=0; index<newUser.getUserEmail().length(); index++) {
			newUserEmail.add();
		}
		boolean validEmail = newUserEmail.contains("@");
		
		
		
		boolean validPwd;
		// Test if passed UserBean is not empty
		if (validEmail==true && validPwd==true) {
			uDAO.insertUser(newUser);
			System.out.println("FINISH: UserBeanService.insert(UserBean insertThisUser)");
			// Return True, for SUCCESSFUL INSERT
			return true;
		} else {
			System.out.println("ERROR: Insert UserBean FAILED; UserBean insertThisUser == null.");
			System.out.println("FINISH: UserBeanService.insertUser(UserBean insertThisUser)");
			// Return False, for FAILED INSERT
			return false;
		}
	}

	

}
