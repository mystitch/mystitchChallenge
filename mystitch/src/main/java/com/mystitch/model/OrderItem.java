package com.mystitch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ORDER_ITEM")
public class OrderItem {

	private int orderItemId;
	private int quantity;
	private ProductItem item;	
	private ProductOrder order;
	
	@Id
	@Column(name = "ORDER_ITEM_ID")
	@SequenceGenerator(name = "ORDER_ITEM_SEQ", sequenceName = "ORDER_ITEM_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ITEM_SEQ")
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	@Column(name = "QUANTITY")
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@ManyToOne
	@JoinColumn(name="ITEM_ID")
	public ProductItem getItem() {
		return item;
	}
	public void setItem(ProductItem item) {
		this.item = item;
	}
	@ManyToOne
	@JoinColumn(name="ORDER_ID")
	public ProductOrder getOrder() {
		return order;
	}
	public void setOrder(ProductOrder order) {
		this.order = order;
		this.order = order;
		if(!order.getOrderItems().contains(this)){
			order.getOrderItems().add(this);
		}
	}
	
	
	
	
}
