package model.profile;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "ProfilesTable")
@Component
public class ProfileBean {

	// Variables, matches table columns
	private int userID;		// Not NULL
	
	private String profileFullName;
	
	@NotNull(message = "請輸入日期 yyyy-MM-dd ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date profileJoinDate;		// Not Null
    
    @NotNull(message = "請輸入日期 yyyy-MM-dd.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date profileBirthdate;
	
    private String profileSex;
	
    @NotNull(message = "請輸入電話.")
    private String profilePhone;		// Not NULL
	
    @NotNull(message = "請輸入地址.")
    private String profileAddress;		// Not NULL
	
    @NotNull(message = "請輸入 VIP: 0 或 1")
    private int profileVIP=0;		// Not NULL
//	OneToOne關係目前用不到先關閉
//	private UserBean userBean;

	// Constructors
	public ProfileBean() {
	}

	public ProfileBean(String profileFullName, Date profileBirthdate, String profileSexString,
			String profilePhoneString, String profileAddress) {
	}

	// Getter / Setters
	@Id
	@Column(name = "userID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	@Column(name = "profileFullName")
	public String getProfileFullName() {
		return profileFullName;
	}

	public void setProfileFullName(String profileFullName) {
		this.profileFullName = profileFullName;
	}

	@Column(name = "profileJoinDate")
	public Date getProfileJoinDate() {
		return profileJoinDate;
	}

	public void setProfileJoinDate(Date profileJoinDate) {
		this.profileJoinDate = profileJoinDate;
	}

	@Column(name = "profileBirthdate")
	public Date getProfileBirthdate() {
		return profileBirthdate;
	}

	public void setProfileBirthdate(Date profileBirthdate) {
		this.profileBirthdate = profileBirthdate;
	}

	@Column(name = "profileSex")
	public String getProfileSex() {
		return profileSex;
	}

	public void setProfileSex(String profileSex) {
		this.profileSex = profileSex;
	}

	@Column(name = "profilePhone")
	public String getProfilePhone() {
		return profilePhone;
	}

	public void setProfilePhone(String profilePhone) {
		this.profilePhone = profilePhone;
	}

	@Column(name = "profileAddress")
	public String getProfileAddress() {
		return profileAddress;
	}

	public void setProfileAddress(String profileAddress) {
		this.profileAddress = profileAddress;
	}

	@Column(name = "profileVIP")
	public int getProfileVIP() {
		return profileVIP;
	}

	public void setProfileVIP(int profileVIP) {
		this.profileVIP = profileVIP;
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

	@Override
	public String toString() {
		return "ProfileBean [userID=" + userID + ", profileFullName=" + profileFullName + ", profileJoinDate="
				+ profileJoinDate + ", profileBirthdate=" + profileBirthdate + ", profileSex=" + profileSex
				+ ", profilePhone=" + profilePhone + ", profileAddress=" + profileAddress + ", profileVIP=" + profileVIP
				+ "]";
	}

}