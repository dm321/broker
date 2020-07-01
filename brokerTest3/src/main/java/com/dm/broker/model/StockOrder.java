package com.dm.broker.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

@Entity
public class StockOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long StockOderId;
	
	private String type;
	
	private double price;
	
	private double amount;
	
	private LocalDateTime orderTime;
	
	@OneToOne (orphanRemoval = true,cascade = CascadeType.ALL)
	private Stock stock;
	
	

	public StockOrder() {

	}

	
	public StockOrder(String type, double price, double amount, String ticker, Stock stock) {
		super();
		this.type = type;
		this.price = price;
		this.amount = amount;
		orderTime = LocalDateTime.now();
		this.stock = stock;
		stock.setTicker(ticker);
		stock.setPrice(price);
	}


	@JsonIgnore
	public long getStockOderId() {
		return StockOderId;
	}



	public LocalDateTime getOrderTime() {
		return orderTime;
	}


	@JsonProperty("orderTime")
	public String getOrderTimeJson()
	{
		return orderTime.toString();
	}

	public Stock getStock() {
		return stock;
	}


	@JsonIgnore
	public void setStockOderId(long stockOderId) {
		StockOderId = stockOderId;
	}



	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}


    
	public void setStock(Stock stock) {
		this.stock = stock;
	}



	public String getType() {
		return type;
	}

	public double getPrice() {
		return price;
	}

	public double getAmount() {
		return amount;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "StockOrder [StockOderId=" + StockOderId + ", type=" + type + ", price=" + price + ", amount=" + amount
				+ ", orderTime=" + orderTime + ", stock=" + stock + "]";
	}
	
	
	

}
