package com.mystitch.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="CREDITCARD")
public class CreditCard implements Serializable{

	 
	private static final long serialVersionUID = 3723740476096223216L;
	
	private long creditCardId;	 
	  private String creditCardNumber;	 	  
	  private String cardType;	
	  private Date expiry;
	  private int ccv;
	  
	  private Customer customer;
	  
	  
	  
  	@Id
	@Column(name = "CREDITCARD_ID")
	@SequenceGenerator(name = "CREDITCARD_SEQ", sequenceName = "CREDITCARD_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDITCARD_SEQ") 
	public long getCreditCardId() {
		return creditCardId;
	}
	public void setCreditCardId(long creditCardId) {
		this.creditCardId = creditCardId;
	}
	@Column(name = "CREDITCARD_NUMBER")
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	@Column(name = "CARDTYPE")
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	@Column(name = "EXPIRY")
	public Date getExpiry() {
		return expiry;
	}
	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}
	@Column(name = "CCV")
	public int getCcv() {
		return ccv;
	}
	public void setCcv(int ccv) {
		this.ccv = ccv;
	}
	
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
