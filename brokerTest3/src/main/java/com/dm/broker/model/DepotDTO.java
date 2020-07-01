package com.dm.broker.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class DepotDTO {

	

	private double balance;
	private List<StockOrder> stockOrderHistory;
	
	
	public DepotDTO() {
		
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<StockOrder>  getStockOrderHistory() {
		return  stockOrderHistory;
	}

	public void setStockOrderHistory(List<StockOrder> stockOrderHistory) {
		this.stockOrderHistory = stockOrderHistory;
	}

	
	
}
