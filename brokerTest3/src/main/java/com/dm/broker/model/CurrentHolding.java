package com.dm.broker.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CurrentHolding {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private long currentHoldingId;
	
	private double amount;
	@OneToOne (cascade = {CascadeType.ALL})
	private Stock stock;
	
	private double value;
	
	

	public CurrentHolding() {	
		super();
	}
	@JsonIgnore
	public long getCurrentHoldingId() {
		return currentHoldingId;
	}

	public double getAmount() {
		return amount;
	}

	public Stock getStock() {
		return stock;
	}

	@JsonIgnore
	public void setCurrentHoldingId(long currentHoldingId) {
		this.currentHoldingId = currentHoldingId;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "CurrentHolding [currentHoldingId=" + currentHoldingId + ", amount=" + amount + ", stock=" + stock
				+ ", value=" + value + "]";
	}
	
	
}
