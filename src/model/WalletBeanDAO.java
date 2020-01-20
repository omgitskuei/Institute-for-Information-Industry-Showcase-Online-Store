package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class WalletBeanDAO implements WalletBeanDAOInterface {

	private SessionFactory sessionFactory;

	@Autowired
	public WalletBeanDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertWallet(WalletBean insertThisWallet) {
		// Create new Session
		System.out.println("BEGIN: WalletBeanDAO.insertWallet(WalletBean insertThisWallet)");
		Session session = sessionFactory.getCurrentSession();
		// Test if passed WalletBean is not empty
		if (insertThisWallet != null) {
			System.out.println("insertThisWallet != null");
			// Insert the passed WalletBean
			session.save(insertThisWallet);
			System.out.println("WalletBean Inserted:");
			System.out.println("walletAmount: " + insertThisWallet.getWalletAmount());
			System.out.println("FINISH: WalletBeanDAO.insertWallet(WalletBean insertThisWallet)");
			// Return True, for SUCCESSFUL INSERT
			return true;
		}
		System.out.println("ERROR: Insert WalletBean FAILED; WalletBean insertThisWallet == null.");
		System.out.println("FINISH: WalletBeanDAO.insertThisWallet(WalletBean insertThisWallet)");
		// Return False, for FAILED INSERT
		return false;
	}

	@Override
	public WalletBean selectWallet(WalletBean selectThisWallet) {
		// Get current Session
		Session session = sessionFactory.getCurrentSession();
		// Check if selectThisWallet is null
		if (selectThisWallet != null) {
			// Try to find selectThisWallet
			WalletBean existingWallet = session.get(WalletBean.class, selectThisWallet.getUserID());
			if (existingWallet != null) {
				// If found, return the result WalletBean existingWallet
				return existingWallet;
			}
		}
		// existingWallet returned null meaning selectThisWallet was not found
		return null;
	}

	@Override
	public boolean updateWallet(WalletBean updateThisWallet, float newwalletAmount) {
		// Get current Session
		Session session = sessionFactory.getCurrentSession();
		// Check if updateThisWallet is null
		if (updateThisWallet != null) {
			// Try to find updateThisWallet
			WalletBean existingWallet = session.get(WalletBean.class, updateThisWallet.getUserID());
			if (existingWallet != null) {
				// If found, update Wallets and return True
				float oldwalletAmount = existingWallet.getWalletAmount();
				existingWallet.setWalletAmount(newwalletAmount);
				return true;
			}
		}
		// Return False because 1) updateThisWallet was null OR 2) existingWallet was
		// null
		return false;
	}

	@Override
	public boolean deleteWallet(WalletBean deleteThisWallet) {
		// Get current Session
		Session session = sessionFactory.getCurrentSession();
		// Check if deleteThisWallet is null
		if (deleteThisWallet != null) {
			// Try to find deleteThisWallet
			WalletBean existingWallet = session.get(WalletBean.class, deleteThisWallet.getUserID());
			if (existingWallet != null) {
				// If found, delete, return True
				float deletewalletAmount = existingWallet.getWalletAmount();
				session.delete(existingWallet);
				return true;
			}
		}
		// Return False because 1) updateThisWallet was null OR 2) existingWallet was
		// null
		return false;
	}

	

}
