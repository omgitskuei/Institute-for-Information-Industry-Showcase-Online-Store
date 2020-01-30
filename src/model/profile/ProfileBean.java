package model.profile;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import model.user.UserBean;

@Entity
@Table(name = "ProfilesTable")
@Component
public class ProfileBean {

	// Variables, matches table columns
	private int userID;
	private String profileFullName;
	private Date profileJoinDate;
	private Date profileBirthdate;
	private String profileSex;
	private String profilePhone;
	private String profileAddress;
	private int profileVIP;
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

}