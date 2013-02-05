package com.mystitch.model;

import java.util.List;

public class ShoppingCart {
	
	private int shoppingCardId;
	private List<OrderItem> orderItems;
	private double subtotal;
	
	public int getShoppingCardId() {
		return shoppingCardId;
	}
	public void setShoppingCardId(int shoppingCardId) {
		this.shoppingCardId = shoppingCardId;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	

}
