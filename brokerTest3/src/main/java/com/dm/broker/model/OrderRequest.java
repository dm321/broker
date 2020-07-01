package com.dm.broker.model;

public class OrderRequest {
	
	private String email;
	private String ticker;
	private double price;
	private double amount;
	private String type;
	
	public OrderRequest()
	{
		
	}
	
	public String getEmail() {
		return email;
	}
	public String getTicker() {
		return ticker;
	}
	public double getPrice() {
		return price;
	}
	public double getAmount() {
		return amount;
	}
	public String getType() {
		return type;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "OrderRequest [email=" + email + ", ticker=" + ticker + ", price=" + price + ", amount=" + amount
				+ ", type=" + type + "]";
	}
	
	
	

}
