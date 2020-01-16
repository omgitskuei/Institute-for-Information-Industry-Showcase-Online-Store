package model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "SettingBean")
@Component
public class SettingBean {
	// Variable
	private int settingID ;
	private int userID  ;
	private String settingSecurityQ;  
	private String settingSecurityA;  	
	private String settingDisplayName; 	
	private boolean settingsAllowMetadata;

	// Constructor
	public SettingBean() {
	}

	public SettingBean(String newsettingSecurityQ,String newsettingSecurityA, 
			String newsettingDisplayName, boolean newsettingsAllowMetadata ) {
		this.settingSecurityQ=newsettingSecurityQ;
		this.settingSecurityA=newsettingSecurityA;
		this.settingDisplayName = newsettingDisplayName;
		this.settingsAllowMetadata = newsettingsAllowMetadata;
		
	}
	
	
	// Getter/Setter
	@Id
	@Column(name="settingID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getSettingID() {
		return settingID;
	}

	public void setSettingID(int settingID) {
		this.settingID = settingID;
	}
	@Column(name="userID")
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	@Column(name="settingSecurityQ")
	public String getSettingSecurityQ() {
		return settingSecurityQ;
	}

	public void setSettingSecurityQ(String settingSecurityQ) {
		this.settingSecurityQ = settingSecurityQ;
	}
	@Column(name="settingSecurityA")
	public String getSettingSecurityA() {
		return settingSecurityA;
	}

	public void setSettingSecurityA(String settingSecurityA) {
		this.settingSecurityA = settingSecurityA;
	}
	@Column(name="settingDisplayName")
	public String getSettingDisplayName() {
		return settingDisplayName;
	}

	public void setSettingDisplayName(String settingDisplayName) {
		this.settingDisplayName = settingDisplayName;
	}
	@Column(name="settingsAllowMetadata")
	public boolean getSettingsAllowMetadata() {
		return settingsAllowMetadata;
	}

	public void setSettingsAllowMetadata(boolean settingsAllowMetadata) {
		this.settingsAllowMetadata = settingsAllowMetadata;
	}

	

}