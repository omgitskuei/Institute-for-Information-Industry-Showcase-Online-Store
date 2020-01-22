package model.profile;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileBeanDAO implements ProfileBeanDAOInterface {

	// Local fields
	private SessionFactory sessionFactory;

	// Constructors
	@Autowired
	public ProfileBeanDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// CRUD methods
	@Override
	public boolean insertProfile(ProfileBean insertThisProfile) {
		// Create new Session
		System.out.println("Begin: ProfileBeanDAO.insertProfile(ProfileBean insertThisProfile)");
		Session session = sessionFactory.getCurrentSession();

		// Test if passed ProfileBean is not empty
		if (insertThisProfile != null) {
			System.out.println("insertThisProfile != null");
			session.save(insertThisProfile);
			// Return True, for SUCCESSFUL INSERT
			return true;
		}
		System.out.println("ERROR: Insert profileBean Failed; ProfileBean insertThisProfile == null");
		System.out.println("Finish: ProfileBeanDAO.insertProfile(ProfileBean insertThisProfile)");
		return false;
	}

	@Override
	public ProfileBean selectProfile(ProfileBean selectThisProfile) {
		System.out.println("Begin: ProfileBeanDAO.selectProfile(ProfileBean insertThisProfile)");

		// Check if selectThisProfile is null
		try {
			if (selectThisProfile != null) {
				// Try to find selectThisUser
				System.out.println("Looking for this Profile:");
				System.out.println("UserID:" + selectThisProfile.getUserID());
				System.out.println("ProfileFullName:" + selectThisProfile.getProfileFullName());
				System.out.println("ProfileJoinDate:" + selectThisProfile.getProfileJoinDate());
				System.out.println("ProfileBirthdate:" + selectThisProfile.getProfileBirthdate());
				System.out.println("ProfileSex:" + selectThisProfile.getProfileSex());
				System.out.println("ProfilePhone:" + selectThisProfile.getProfilePhone());
				System.out.println("ProfileAddress:" + selectThisProfile.getProfileAddress());
				System.out.println("ProfileVIP:" + selectThisProfile.getProfileVIP());

				Session session = sessionFactory.getCurrentSession();
				String hqlString = "from ProfileBean where profileFullName=:thisFullName and profileJoinDate=:thisJoinDate and profileBirthdate=:thisBirthdate and profileSex=:thisSex and profilePhone=:thisPhone and profileAddress=:thisAddress and profileVIP=:thisVIP ";
				Query q = session.createQuery(hqlString);
				q.setParameter("thisFullName", selectThisProfile.getProfileFullName());
				q.setParameter("thisJoinDate", selectThisProfile.getProfileJoinDate());
				q.setParameter("thisBirthdate", selectThisProfile.getProfileBirthdate());
				q.setParameter("thisSex", selectThisProfile.getProfileSex());
				q.setParameter("thisPhone", selectThisProfile.getProfilePhone());
				q.setParameter("thisAddress", selectThisProfile.getProfileAddress());
				q.setParameter("thisVIP", selectThisProfile.getProfileVIP());

				ProfileBean existingPrifile = (ProfileBean) q.uniqueResult();

				// UserBean existingUser = session.get(UserBean.class,
				// selectThisUser.getUserEmail());
				if (existingPrifile != null) {
					// If found, return the result UserBean existingUser
					System.out.println("User Found - Returning User " + existingPrifile.getUserID());
					System.out.println("Finish: UserBeanDAO.selecProfile(ProfileBean selectThisProfile)");
					return existingPrifile;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// existingProfile returned null meaning selectThisProfile was not found
		System.out.println("updateThisProfile OR existingProfile was NULL");
		System.out.println("Finish: ProfileBeanDAO.selectProfile(ProfileBean insertThisProfile)");
		return null;
	}

	public List<ProfileBean> selectAll() {
		// Get current Session
		System.out.println("Begin: ProfileBeanDAO.selectAll()");
		Session session = sessionFactory.getCurrentSession();
		// Get all rows from ProfileTable
		Query query = session.createQuery("From ProfileBean"); // This 'From' references UserBean.java
		// Store query results into List results
		List<ProfileBean> results = (List<ProfileBean>) query.list();
		System.out.println("SelectAll: " + results.get(0).getClass());
		// Print List results into console (for debugging)
		for (int index = 0; index < results.size(); index++) {
			System.out.println(results.get(index).getUserID());
			System.out.println(results.get(index).getProfileFullName());
			System.out.println(results.get(index).getProfileJoinDate());
			System.out.println(results.get(index).getProfileBirthdate());
			System.out.println(results.get(index).getProfileSex());
			System.out.println(results.get(index).getProfilePhone());
			System.out.println(results.get(index).getProfileAddress());
			System.out.println(results.get(index).getProfileVIP());
			System.out.println();
		}
		// Return List results
		System.out.println("Finish: ProfileBeanDAO.selectAll()");
		return results;
	}

	@Override
	public boolean updateFullName(ProfileBean updateThisProfile, String newFullName) {
		System.out.println("Begin: ProfileBeanDAO.updateFullName(ProfileBean updateThisProfile, String newFullName)");
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisProfile is null
		if (updateThisProfile != null) {
			// Try to find updateThisProfile
			ProfileBean existingProfile = session.get(ProfileBean.class, updateThisProfile.getUserID());
			if (existingProfile != null) {
				// If found, update Email and return True
				String oldProfileFullName = existingProfile.getProfileFullName();
				existingProfile.setProfileFullName(newFullName);
				System.out.println("ProfileFullName " + oldProfileFullName + " updated to "
						+ existingProfile.getProfileFullName());
				System.out.println(
						"Finish: ProfileBeanDAO.updateFullName(ProfileBean updateThisProfile, String newFullName)");
				return true;
			}
		}
		// Return False because 1) updateThisUser was null OR 2) existingUser was null
		System.out.println("updateThisProfile OR existingProfile was NULL");
		System.out.println("Finish: ProfileBeanDAO.updateFullName(ProfileBean updateThisProfile, String newFullName)");
		return false;
	}

	@Override
	public boolean updateBirthdate(ProfileBean updateThisProfile, Date newBirthdate) {
		System.out.println("Begin: ProfileBeanDAO.updateBirthdate(ProfileBean updateThisProfile, Date newBirthdate)");
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisProfile is null
		if (updateThisProfile != null) {
			// Try to find updateThisProfile
			ProfileBean existingProfile = session.get(ProfileBean.class, updateThisProfile.getUserID());
			if (existingProfile != null) {
				// If found, update Birthdate and return True
				Date oldProfileBiryhday = existingProfile.getProfileBirthdate();
				existingProfile.setProfileBirthdate(newBirthdate);
				System.out.println("ProfileBirthdate " + oldProfileBiryhday + " updated to "
						+ existingProfile.getProfileBirthdate());
				System.out.println(
						"Finish: ProfileBeanDAO.updateBirthdate(ProfileBean updateThisProfile, Date newBirthdate)");
				return true;
			}
		}
		// Return False because 1) updateThisProfile was null OR 2) existingProfile was
		// NULL
		System.out.println("updateThisProfile OR existingProfile was NULL");
		System.out.println("Finish: ProfileBeanDAO.updateBirthdate(ProfileBean updateThisProfile, Date newBirthdate)");
		return false;
	}

	@Override
	public boolean updateSex(ProfileBean updateThisProfile, String newSex) {
		System.out.println("Begin: ProfileBeanDAO.updateSex(ProfileBean updateThisProfile, String newSex)");
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisProfile is null
		if (updateThisProfile != null) {
			// Try to find updateThisProfile
			ProfileBean existingProfile = session.get(ProfileBean.class, updateThisProfile.getUserID());
			if (existingProfile != null) {
				// If found, update Sex and return True
				String oldProfileSex = existingProfile.getProfileSex();
				existingProfile.setProfileSex(newSex);
				System.out.println("ProfileSex " + oldProfileSex + " updated to " + existingProfile.getProfileSex());
				System.out.println("Finish: ProfileBeanDAO.updateSex(ProfileBean updateThisProfile, String newSex)");

				return true;
			}
		}
		// Return False because 1) updateThisProfile was null OR 2) existingProfile was
		// NULL
		System.out.println("updateThisProfile OR existingProfile was NULL");
		System.out.println("Finish: ProfileBeanDAO.updateSex(ProfileBean updateThisProfile, String newSex)");
		return false;
	}

	@Override
	public boolean updatePhone(ProfileBean updateThisProfile, String newPhone) {
		System.out.println("Begin: ProfileBeanDAO.updatePhone(ProfileBean updateThisProfile, String newPhone)");
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisProfile is null
		if (updateThisProfile != null) {
			// Try to find updateThisProfile
			ProfileBean existingProfile = session.get(ProfileBean.class, updateThisProfile.getUserID());
			if (existingProfile != null) {
				// If found, update Sex and return True
				String oldProfilePhone = existingProfile.getProfilePhone();
				existingProfile.setProfilePhone(newPhone);
				System.out.println(
						"ProfilePhone " + oldProfilePhone + " updated to " + existingProfile.getProfilePhone());
				System.out
						.println("Finish: ProfileBeanDAO.updatePhone(ProfileBean updateThisProfile, String newPhone)");

				return true;
			}
		}
		// Return False because 1) updateThisProfile was null OR 2) existingProfile was
		// NULL
		System.out.println("updateThisProfile OR existingProfile was NULL");
		System.out.println("Finish: ProfileBeanDAO.updatePhone(ProfileBean updateThisProfile, String newPhone)");
		return false;
	}

	@Override
	public boolean updateAddress(ProfileBean updateThisProfile, String newAddress) {
		System.out.println("Begin: ProfileBeanDAO.updateAddress(ProfileBean updateThisProfile, String newAddress)");
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisProfile is null
		if (updateThisProfile != null) {
			// Try to find updateThisProfile
			ProfileBean existingProfile = session.get(ProfileBean.class, updateThisProfile.getUserID());
			if (existingProfile != null) {
				// If found, update Sex and return True
				String oldProfileAddress = existingProfile.getProfileAddress();
				existingProfile.setProfileAddress(newAddress);
				System.out.println(
						"Profile Address " + oldProfileAddress + " updated to " + existingProfile.getProfileAddress());
				System.out.println(
						"Finish: ProfileBeanDAO.updateAddress(ProfileBean updateThisProfile, String newAddress)");

				return true;
			}
		}
		// Return False because 1) updateThisProfile was null OR 2) existingProfile was
		// NULL
		System.out.println("updateThisProfile OR existingProfile was NULL");
		System.out.println("Finish: ProfileBeanDAO.updateAddress(ProfileBean updateThisProfile, String newAddress)");
		return false;
	}

	@Override
	public boolean deleteProfile(ProfileBean deleteThisProfile) {
		System.out.println("Begin: ProfileBeanDAO.deleteProfile(ProfileBean deleteThisProfile)");
		Session session = sessionFactory.getCurrentSession();
		// Check if deleteThisUser is null
		if (deleteThisProfile != null) {
			// Try to find deleteThisUser
			ProfileBean existingProfile = session.get(ProfileBean.class, deleteThisProfile.getUserID());
			if (existingProfile != null) {
				// If found, delete, return True
				int deletedUserID = existingProfile.getUserID();
				String deletedProfileFullName = existingProfile.getProfileFullName();
				Date deletedProfileJoinDate = existingProfile.getProfileJoinDate();
				Date deletedProfileBirthdate = existingProfile.getProfileBirthdate();
				String deletedProfileSex = existingProfile.getProfileSex();
				String deletedProfilePhone = existingProfile.getProfilePhone();
				String deletedProfileAddress = existingProfile.getProfileAddress();
				int deletedProfileVIP = existingProfile.getProfileVIP();
				session.delete(existingProfile);
				System.out.println("Profile DELETED:");
				System.out.println("User ID DELETED: " + deletedUserID);
				System.out.println("Profile FullName DELETED: " + deletedProfileFullName);
				System.out.println("Profile JoinDate DELETED: " + deletedProfileJoinDate);
				System.out.println("Profile Birthdate DELETED " + deletedProfileBirthdate);
				System.out.println("Profile Sex DELETED " + deletedProfileSex);
				System.out.println("Profile Phone DELETED " + deletedProfilePhone);
				System.out.println("Profile Address DELETED " + deletedProfileAddress);
				System.out.println("Profile VIP DELETED " + deletedProfileVIP);
				return true;
			}
		}
		// Return False because 1) updateThisProfile was null OR 2) existingProfile was
		// NULL
		System.out.println("deleteProfile OR existingProfile was NULL");
		System.out.println("Finish: ProfileBeanDAO.deleteProfile(ProfileBean deleteThisProfile)");
		return false;
	}

}
