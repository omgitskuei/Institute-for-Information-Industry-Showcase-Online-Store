package model.profile;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
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
		System.out.println("BEGIN: ProfileBeanDAO.insertProfile(ProfileBean)");
		Session session = sessionFactory.getCurrentSession();

		if (insertThisProfile != null) {
			// passed Bean was empty
			System.out.println("insertThisProfile != null");

			// IF empty or too short, Auto-generate profilePhone value, because it is a NOT-NULL field
			insertThisProfile.setProfilePhone("0000-000-000");
			
			// Auto-generate profileJoinDate
			LocalDate now = LocalDate.now();
			Date convertedDate = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
			insertThisProfile.setProfileJoinDate(convertedDate);

			// Auto-generate profileVIP, default 0
			// Is already done in ProfileBean private properties

			session.save(insertThisProfile);
			// Return True, for SUCCESSFUL INSERT
			System.out.println("FINISH: ProfileBeanDAO.insertProfile(ProfileBean)");
			return true;
		} else {
			// Failed insert, passed Bean was NULL
			System.out.println("ERROR: Insert Failed; ProfileBean insertThisProfile was null");
			System.out.println("FINISH: ProfileBeanDAO.insertProfile(ProfileBean)");
			return false;
		}
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
//				System.out.println("ProfileFullName:" + selectThisProfile.getProfileFullName());
//				System.out.println("ProfileJoinDate:" + selectThisProfile.getProfileJoinDate());
//				System.out.println("ProfileBirthdate:" + selectThisProfile.getProfileBirthdate());
//				System.out.println("ProfileSex:" + selectThisProfile.getProfileSex());
//				System.out.println("ProfilePhone:" + selectThisProfile.getProfilePhone());
//				System.out.println("ProfileAddress:" + selectThisProfile.getProfileAddress());
//				System.out.println("ProfileVIP:" + selectThisProfile.getProfileVIP());

				Session session = sessionFactory.getCurrentSession();
				String hqlString = "from ProfileBean where userID=:thisUserID";
				Query q = session.createQuery(hqlString);
				q.setParameter("thisUserID", selectThisProfile.getUserID());

				ProfileBean existingProfile = (ProfileBean) q.uniqueResult();

				if (existingProfile != null) {
					// If found, return the result ProfileBean existingProfile
					System.out.println("Profile Found - Returning User " + existingProfile.getUserID());
					System.out.println("Finish: ProfileBeanDAO.selecProfile(ProfileBean selectThisProfile)");
					return existingProfile;
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

	// Overloading selectProfile to also select based on int
	@SuppressWarnings("rawtypes")
	public ProfileBean selectProfile(int userID) {
		System.out.println("BEGIN: ProfileBeanDAO.selectProfileByUserID(int)");
		System.out.println("Looking for Profile with this userID: " + userID);

		Session session = sessionFactory.getCurrentSession();
		String hqlString = "from ProfileBean where userID=:thisID";
		Query q = session.createQuery(hqlString);
		q.setParameter("thisID", userID);
		ProfileBean result = (ProfileBean) q.uniqueResult();

		if (result != null) {
			System.out.println("Result:");
			System.out.println("Full Name: " + result.getProfileFullName());
			System.out.println("Address: " + result.getProfileAddress());
			System.out.println("Phone: " + result.getProfilePhone());
			System.out.println("Sex: " + result.getProfileSex());
			System.out.println("VIP status: " + result.getProfileVIP());
			System.out.println("FINISH: ProfileBeanDAO.selectProfileByUserID(int)");
			return result;
		} else {
			System.out.println("Profile with this userID [" + userID + "] NOT FOUND.");
			System.out.println("Result: null");
			System.out.println("FINISH: ProfileBeanDAO.selectProfileByUserID(int)");
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
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
			System.out.println("Result #" + index);
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

	public boolean updateJoinDate(ProfileBean updateThisProfile) {
		System.out.println("BEGIN: ProfileBeanDAO.updateJoinDate(ProfileBean)");
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisProfile is null
		if (updateThisProfile != null) {
			// Try to find updateThisProfile
			ProfileBean existingProfile = session.get(ProfileBean.class, updateThisProfile.getUserID());
			if (existingProfile != null) {
				// If found, update JoinDate and return True
				Date oldJoinDate = existingProfile.getProfileJoinDate();
				// Auto-generate (new) JoinDate value
				LocalDate now = LocalDate.now();
				Date convertedDate = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
				existingProfile.setProfileJoinDate(convertedDate);
				System.out.println("JoinDate updated from"+oldJoinDate+" to "
						+ existingProfile.getProfileJoinDate());
				System.out.println("FINISH: ProfileBeanDAO.updateJoinDate(ProfileBean)");
				return true;
			} else {
				// return false existingUser was null
				System.out.println("ERROR: query result existingUser was NULL");
				System.out.println("FINISH: ProfileBeanDAO.updateJoinDate(ProfileBean)");
				return false;
			}
		} else {
			// Return false updateThisUser was null
			System.out.println("ERROR: Passed updateThisProfile was NULL");
			System.out.println("FINISH: ProfileBeanDAO.updateJoinDate(ProfileBean)");
			return false;
		}
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

	public boolean updateVIP(ProfileBean bean, int newVIP) {
		boolean updateSuccess = false;
		System.out.println("BEGIN: ProfileBeanDAO.updateVIP(ProfileBean, int)");
		// Check passed bean is not empty
		if (bean != null) {
			// Check passed VIP value is valid
			if (newVIP >= 0 && newVIP <= 3) {
				// Look for profileBean bean is in DB
				Session session = sessionFactory.getCurrentSession();
				String hqlString = "from ProfileBean where userID=:thisUserID";
				Query q = session.createQuery(hqlString);
				int userID = bean.getUserID();
				q.setParameter("thisUserID", userID);
				ProfileBean result = (ProfileBean) q.uniqueResult();
				// Check if query result is empty
				if (result!=null) {
					result.setProfileVIP(newVIP);
					System.out.println("Update successful");
					updateSuccess=true;
				} else {
					System.out.println("ERROR: ProfileBean with userID ["+userID+"] NOT FOUND");
					updateSuccess= false;
				}
			} else {
				System.out.println("ERROR: Passed VIP not valid (between 0 and 3)");
				updateSuccess= false;
			}
		} else {
			System.out.println("ERROR: Passed bean was NULL");
			updateSuccess= false;
		}
		System.out.println("FINISH: ProfileBeanDAO.updateVIP(ProfileBean, int)");
		return updateSuccess;
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
