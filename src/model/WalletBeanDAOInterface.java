package model;

public interface WalletBeanDAOInterface {
	// An abstract list of all must-have methods for UserBeanDAO; CRUD
	// C.reate
	public boolean insertWallet(WalletBean insertThisWallet);
	// R.ead
	public WalletBean selectWallet(WalletBean selectThisWallet);
	// U.pdate
	public boolean updateWallet(WalletBean updateThisWallet, float newWalletAmount);
	// D.elete
	public boolean deleteWallet(WalletBean deleteThisWallet);
}
