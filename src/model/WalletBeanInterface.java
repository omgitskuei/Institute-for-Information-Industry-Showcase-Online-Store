package model;

public interface WalletBeanInterface {
	// An abstract list of all must-have methods for UserBeanDAO; CRUD
	// C.reate
	public boolean insertWallet(WalletBean insertThisWallet);
	// R.ead
	public WalletBean selectWallet(WalletBean selectThisWallet);
	// U.pdate
	public boolean updateWallet(WalletBean updateThisWallet, float newwalletAmount);
	// D.elete
	public boolean deleteWallet(WalletBean deleteThisWallet);
}
