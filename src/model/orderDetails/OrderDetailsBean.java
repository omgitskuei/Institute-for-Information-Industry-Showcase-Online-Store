package model.orderDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

// match table
@Entity
@Table(name = "OrderDetailsTable")
@Component
public class OrderDetailsBean {
	
	// variables, match the column
	private int orderDetailID;
	private int productID;
	private int productCount;
	@OneToMany(mappedBy="OrderBean")
	private int orderID;
	
	// Empty Constructor and Constructor
	public OrderDetailsBean() {
	}

	public OrderDetailsBean(int productID, int productCount, int orderID) {
		this.productID = productID;
		this.productCount = productCount;
		this.orderID = orderID;
	}

	// GET/SET methods
	@Id
	@Column(name="OrderDetailID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(int orderDetailID) {
		this.orderDetailID = orderDetailID;
	}

	@Column(name="ProductID")
	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	@Column(name="ProductCount")
	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	@Column(name="OrderID")
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
}
