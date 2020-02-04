package model.orderDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import model.order.OrderBean;

// match table
@Entity
@Table(name = "OrderDetailsTable")
@Component
public class OrderDetailsBean {
	
	// variables, match the column
	private int orderDetailID;		// Not NULL
	private int productID;		// Not NULL
	private int productCount;		// Not NULL
	//private int orderID;		// Not NULL
	//ManyToOne
	private OrderBean orderBean;
	
	// Empty Constructor and Constructor
	public OrderDetailsBean() {
	}

	public OrderDetailsBean(int productID, int productCount) {
		this.productID = productID;
		this.productCount = productCount;
		//this.orderID = orderID;
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

//	@Column(name="OrderID")
//	public int getOrderID() {
//		return orderID;
//	}
//
//	public void setOrderID(int orderID) {
//		this.orderID = orderID;
//	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OrderID")
	public OrderBean getOrderBean() {
		return orderBean;
	}

	public void setOrderBean(OrderBean thisBean) {
		this.orderBean = thisBean;
	}
	
}
