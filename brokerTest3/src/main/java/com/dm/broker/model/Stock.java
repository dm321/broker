package com.dm.broker.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Component
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long StockId;
	private String Ticker;
	private String fullname;
	private double price;
	
	
	
	
	public Stock() {
		
	}
	public Stock(long stockId, String ticker, String fullname, double price) {
		super();
		StockId = stockId;
		Ticker = ticker;
		this.fullname = fullname;
		this.price = price;
	}

	
	public long getStockId() {
		return StockId;
	}
	
	public void setStockId(long stockId) {
		StockId = stockId;
	}
	public String getTicker() {
		return Ticker;
	}
	public void setTicker(String ticker) {
		Ticker = ticker;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (StockId != other.StockId)
			return false;
		if (Ticker == null) {
			if (other.Ticker != null)
				return false;
		} else if (!Ticker.equals(other.Ticker))
			return false;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stock [StockId=" + StockId + ", Ticker=" + Ticker + ", fullname=" + fullname + ", price=" + price + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (StockId ^ (StockId >>> 32));
		result = prime * result + ((Ticker == null) ? 0 : Ticker.hashCode());
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
}
