package com.dm.broker.model;

public class WithdrawRequest {
	
	private String email;
	private double amount;
	
	
	
	public WithdrawRequest() {
		super();
	}
	public String getEmail() {
		return email;
	}
	public double getAmount() {
		return amount;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	

}
