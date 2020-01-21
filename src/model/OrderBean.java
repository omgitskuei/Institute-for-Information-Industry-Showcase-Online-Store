package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "OrdersTable")
@Component
public class OrderBean {
	// Local Variables
	private int orderID;
	private int userID;
	private int total;
	private String mailingAddress;
	private String mailingPhone;
	private Date orderTime;

	// Constructors
	public OrderBean() {
	}

	public OrderBean(int newUserID, int newTotal, String newMailingAddress, String newMailingPhone, Date newOrderTime) {
		this.userID=newUserID;
		this.total=newTotal;
		this.mailingAddress = newMailingAddress;
		this.mailingPhone = newMailingPhone;
		this.orderTime=newOrderTime;
	}
	
	// Getter/Setters
	@Id
	@Column(name="ORDERID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getOrderID() {
		return orderID;
	}

	public void setOrderId(int newOrderID) {
		this.orderID = newOrderID;
	}

	@Column(name = "USERID")
	public int getUserID() {
		return userID;
	}

	public void setUserId(int newUserID) {
		this.userID = newUserID;
	}

	@Column(name = "TOTAL")
	public int getTotal() {
		return total;
	}

	public void setTotal(int newTotal) {
		this.total = newTotal;
	}

	@Column(name = "MAILINGADDRESS")
	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String newMailingAddress) {
		this.mailingAddress = newMailingAddress;
	}

	@Column(name = "MAILINGPHONE")
	public String getMailingPhone() {
		return mailingPhone;
	}

	public void setMailingPhone(String newMailingPhone) {
		this.mailingPhone = newMailingPhone;
	}

	@Column(name = "ORDERTIME")
	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date newOrderTime) {
		this.orderTime = newOrderTime;
	}

}