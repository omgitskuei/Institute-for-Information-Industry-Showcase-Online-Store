package model.mailingList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "MailingListTable")
@Component
public class MailBean {
	// Variables, matches table columns
	private int mailingListID;
	private String email;
	
	// Constructors
	public MailBean() {
	}
	public MailBean(int id, String email) {
		this.mailingListID = id;
		this.email = email;
	}
	
	// Get/Set Methods
	@Id
	@Column(name="mailingListID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getMailingListID() {
		System.out.println("MailBean.getMailingListID() Returning ["+mailingListID+"]");
		return mailingListID;
	}
	public void setMailingListID(int mailingListID) {
		System.out.println("MailBean.setMailingListID() Setting ["+mailingListID+"]");
		this.mailingListID = mailingListID;
	}
	
	@Column(name="email")
	public String getEmail() {
		System.out.println("MailBean.getEmail() Returning ["+email+"]");
		return email;
	}
	public void setEmail(String email) {
		System.out.println("MailBean.setEmail() Setting ["+email+"]");
		this.email = email;
	}
}
