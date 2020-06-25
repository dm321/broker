package com.dm.broker.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Stock {
	
	@Id
	@GeneratedValue
	private long StockId;
	private String Ticker;
	private String fullname;
	private BigDecimal price;
	
	
	
	
	public Stock() {
		
	}
	public Stock(long stockId, String ticker, String fullname, BigDecimal price) {
		super();
		StockId = stockId;
		Ticker = ticker;
		this.fullname = fullname;
		this.price = price;
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
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
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
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	
	
}
