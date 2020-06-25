package com.dm.broker.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Depot {

	@Id
	@GeneratedValue
	private long depotId;
	private BigDecimal balance;
	@OneToMany (cascade = {CascadeType.ALL})
	private List<Stock> purchasedStocks = new ArrayList<Stock>();
	
	
	
	public Depot() {
		
	}
	public Depot(long depotId, BigDecimal balance, List<Stock> purchasedStocks) {
		super();
		this.depotId = depotId;
		this.balance = balance;
		this.purchasedStocks = purchasedStocks;
	}
	@Override
	public String toString() {
		return "Depot [depotId=" + depotId + ", balance=" + balance + ", purchasedStocks=" + purchasedStocks + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + (int) (depotId ^ (depotId >>> 32));
		result = prime * result + ((purchasedStocks == null) ? 0 : purchasedStocks.hashCode());
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
		Depot other = (Depot) obj;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (depotId != other.depotId)
			return false;
		if (purchasedStocks == null) {
			if (other.purchasedStocks != null)
				return false;
		} else if (!purchasedStocks.equals(other.purchasedStocks))
			return false;
		return true;
	}
	public long getDepotId() {
		return depotId;
	}
	public void setDepotId(long depotId) {
		this.depotId = depotId;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public List<Stock> getPurchasedStocks() {
		return purchasedStocks;
	}
	public void setPurchasedStocks(List<Stock> purchasedStocks) {
		this.purchasedStocks = purchasedStocks;
	}
	
	
	
	
}
