package com.mystitch.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name="PRODUCT_ITEM")
public class ProductItem implements Serializable {
	
	
	private static final long serialVersionUID = -7216158204704651029L;
	
	private long itemId;;
	private String category;
	private int size;
	private String color;	
	private String brand;
	private String name;
	private String productCode;	
	private float price;
	
	@Id
	@Column(name = "ITEM_ID")
	@SequenceGenerator(name = "PRODUCT_ITEM_SEQ", sequenceName = "PRODUCT_ITEM_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_ITEM_SEQ")
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	
	@Column(name="CATEGORY")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Column(name="PRODUCT_SIZE")
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	@Column(name="COLOR")
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Column(name="BRAND")
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}	
	
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="PRODUCT_CODE")
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	@Column(name="PRICE")
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	

}
