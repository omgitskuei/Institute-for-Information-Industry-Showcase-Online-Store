package model;

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

@Entity
@Table(name = "WalletsTable")
@Component
public class WalletBean {

	// Variables, matches table columns
	private int userID;
	private float walletAmount;
	private UserBean userBean;

	// Constructors
	public WalletBean() {
	}

	public WalletBean(float walletAmount) {
		this.walletAmount = walletAmount;
	}

	// Get/Set Methods
	@GenericGenerator(name = "generator", parameters = @Parameter(name = "property", value = "UserBean "), strategy = "foreign")
	@Id
	@Column(name = "userID")
	@GeneratedValue(generator = "generator")
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
<<<<<<< HEAD

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

=======
	
	testpush
	
	
>>>>>>> b11f9adfd4d76130e19ede981ea61e4e342f4b1f
}
