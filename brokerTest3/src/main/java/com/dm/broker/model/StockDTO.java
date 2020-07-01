package com.dm.broker.model;

import java.math.BigDecimal;

public class StockDTO {

	private String Ticker;
	private BigDecimal currentPrice;
	
	
	
	public StockDTO() {
		super();
	}
	public String getTicker() {
		return Ticker;
	}
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
	public void setTicker(String ticker) {
		Ticker = ticker;
	}
	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	
	
}
