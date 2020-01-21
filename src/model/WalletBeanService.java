package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletBeanService {
	// Variables: Local Fields
	private WalletBeanDAO wDAO;
	
	// Constructors
	@Autowired
	public WalletBeanService(WalletBeanDAO wDAO) {
		this.wDAO = wDAO;
	}
	
	// Test validity* of Wallet amount changes
	// Validity means checking user input for proper $ money values
	public boolean updateAmount(WalletBean updateThisWallet) {
		System.out.println("BEGIN: WalletBeanService.insert(WalletBean thisWallet)");
		boolean success = false;
		
		
		if ( validateMoney() ) {
			wDAO.updateWalletAmount(updateThisWallet, updateThisWallet.getWalletAmount());
		}
		
		System.out.println("FINISH: WalletBeanService.insert(WalletBean thisWallet)");
		return success;
	}
	
	// Private Validation Methods
	private static boolean validateMoney() {
		boolean success = false;
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
}
