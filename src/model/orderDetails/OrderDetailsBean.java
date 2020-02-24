package model.orderDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

// match table
@Entity
@Table(name = "OrderDetailsTable")
@Component
public class OrderDetailsBean {

	// variables, match the column
	private int orderDetailID; // Not NULL
	private int productID; // Not NULL
	private String productName;
	private int productCount; // Not NULL
	private float productPrice;
	private int orderID;
//	private OrderBean orderBean; // ManyToOne

	// Empty Constructor and Constructor
	public OrderDetailsBean() {
	}

	public OrderDetailsBean(int OrderID, int ProductID, String ProductName, int ProductCount, float ProductPrice) {
		this.productID = ProductID;
		this.productName = ProductName;
		this.productCount = ProductCount;
		this.productPrice = ProductPrice;
		this.orderID = OrderID;
//		this.orderBean = orderBean;//ManyToOne
	}

	// GET/SET methods
	@Id
	@Column(name = "OrderDetailID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(int orderDetailID) {
		this.orderDetailID = orderDetailID;
	}

	@Column(name = "OrderID")
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	@Column(name = "ProductID")
	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	@Column(name = "ProductName")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "ProductCount")
	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	@Column(name = "ProductPrice")
	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "OrderID")
//	public OrderBean getOrderBean() {
//		return orderBean;
//	}
//
//	public void setOrderBean(OrderBean thisBean) {
//		this.orderBean = thisBean;
//	}
}
