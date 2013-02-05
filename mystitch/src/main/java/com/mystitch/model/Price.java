package com.mystitch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PRICE")
public class Price {

	private long priceId;
	private String category;
	private float seasonalDiscount;
	private float promoDiscount;	
	private double priceAmount;
	
	
		
	@Id
	@Column(name = "PRICE_ID")
	@SequenceGenerator(name = "PRICE_SEQ", sequenceName = "PRICE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRICE_SEQ")
	public long getPriceId() {
		return priceId;
	}
	public void setPriceId(long priceId) {
		this.priceId = priceId;
	}
	@Column(name = "CATEGORY")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Column(name = "PROMO_DISCOUNT")
	public float getPromoDiscount() {
		return promoDiscount;
	}
	public void setPromoDiscount(float promoDiscount) {
		this.promoDiscount = promoDiscount;
	}
	@Column(name = "PRICE_AMOUNT")
	public double getPriceAmount() {
		return priceAmount;
	}
	public void setPriceAmount(double priceAmount) {
		this.priceAmount = priceAmount;
	}
	@Column(name = "SEASONAL_DISCOUNT")
	public float getSeasonalDiscount() {
		return seasonalDiscount;
	}
	public void setSeasonalDiscount(float seasonalDiscount) {
		this.seasonalDiscount = seasonalDiscount;
	}
	
}
