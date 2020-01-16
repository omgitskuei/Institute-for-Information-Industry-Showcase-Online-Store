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
@Table(name = "OrderBean")
@Component
public class OrderBean {
	private int orderId;
	private int userId;
	private int total;
	private String mailingAddress;
	private String mailingPhone;
	private Date orderTime;

	public OrderBean() {
	}

	public OrderBean(int newUserId, int newTotal, String newMailingAddress, String newMailingPhone, Date newOrderTime) {
		this.userId=newUserId;
		this.total=newTotal;
		this.mailingAddress = newMailingAddress;
		this.mailingPhone = newMailingPhone;
		this.orderTime=newOrderTime;
	}

	@Id
	@Column(name="ORDERID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Column(name = "USERID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "TOTAL")
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Column(name = "MAILINGADDRESS")
	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	@Column(name = "MAILINGPHONE")
	public String getMailingPhone() {
		return mailingPhone;
	}

	public void setMailingPhone(String mailingPhone) {
		this.mailingPhone = mailingPhone;
	}

	@Column(name = "ORDERTIME")
	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

}