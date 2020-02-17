package model.wallet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

import model.user.UserBean;

@Entity
@Table(name = "WalletsTable")
@Component
public class WalletBean {

	// Variables, matches table columns
	private int walletID;
	private int userID;		// Not NULL
	private float walletAmount;		// Not NULL
//	OneToOne關係目前用不到先關閉
//	private UserBean userBean;
	
	
	// Constructors
	public WalletBean() {
	}
	
	public WalletBean(int walletID) {
		this.walletID = walletID;
	}
	
	public WalletBean(int walletID, int userID, float walletAmount) {
		this.userID = userID;
		this.walletAmount = walletAmount;
	}

	// Get/Set Methods
	//@GenericGenerator(name = "generator", parameters = @Parameter(name = "property", value = "UserBean "), strategy = "foreign")
	@Id
	@Column(name = "walletID")
	//@GeneratedValue(generator = "generator")
	public int getWalletID() {
		return walletID;
	}

	public void setWalletID(int walletID) {
		this.walletID = walletID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	@Column(name = "walletAmount")
	public float getWalletAmount() {
		return walletAmount;
	}

	public void setWalletAmount(float walletAmount) {
		this.walletAmount = walletAmount;
	}
//	OneToOne關係目前用不到先關閉
//	@OneToOne(fetch = FetchType.LAZY)
//	@PrimaryKeyJoinColumn
//	public UserBean getUserBean() {
//		return userBean;
//	}
//
//	public void setUserBean(UserBean userBean) {
//		this.userBean = userBean;
//	}

}
