package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "WalletsTable")
@Component
public class WalletBean {
	// Variables, matches table columns
	private int userID;
	private float walletAmount;
	
	// Constructors
	public WalletBean() {}
	public WalletBean(float walletAmount) {}
	
	// Get/Set Methods
	@Id
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public float getWalletAmount() {
		return walletAmount;
	}
	public void setWalletAmount(float walletAmount) {
		this.walletAmount = walletAmount;
	}
	
	
	
	
}
