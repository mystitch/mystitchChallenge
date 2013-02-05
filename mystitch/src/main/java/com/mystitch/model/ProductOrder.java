package com.mystitch.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCT_ORDER")
public class ProductOrder {
	
	private long orderId;
	private double totalPrice;
	private String orderNumber;
	private Date orderDate;
	private float shippingCharge;
	private String orderStatus;
	
	private Customer customer;	
	
	private List<OrderItem> orderItems;
	
	public void addOrderItems(OrderItem orderItem){
		  this.orderItems.add(orderItem);
		  if(orderItem.getOrder()!=this){
			  orderItem.setOrder(this);
		  }
	  }

	@Id
	@Column(name = "ORDER_ID")
	@SequenceGenerator(name = "PRODUCT_ORDER_SEQ", sequenceName = "PRODUCT_ORDER_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_ORDER_SEQ")
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	@Column(name = "TOTAL_PRICE")
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Column(name = "ORDER_NUMBER")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	@Column(name = "ORDER_DATE")
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	@Column(name = "SHIPPING_CHARGE")
	public float getShippingCharge() {
		return shippingCharge;
	}

	public void setShippingCharge(float shippingCharge) {
		this.shippingCharge = shippingCharge;
	}
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Column(name = "ORDER_STATUS")
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	@OneToMany(cascade=CascadeType.ALL,mappedBy="order",fetch=FetchType.LAZY)
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	

}
