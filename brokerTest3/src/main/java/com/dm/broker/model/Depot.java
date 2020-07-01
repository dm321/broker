package com.dm.broker.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Component
public class Depot {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long depotId;
	private double balance;
	@OneToMany (cascade = {CascadeType.ALL},targetEntity = StockOrder.class)
	private List<StockOrder> stockOrderHistory = new ArrayList<>();
	
	@OneToMany (cascade = {CascadeType.ALL},targetEntity = CurrentHolding.class)
	private List<CurrentHolding> currentStocks = new ArrayList<>();
	
	
	public Depot() {
		
	}
	
	public boolean isStockPresent(Stock stock)
	{
		
	boolean isPresent =	currentStocks.stream().anyMatch(holding -> holding.getStock().getTicker().equals(stock.getTicker()));
	return isPresent;
	
	}
	
	public boolean canStockBeSold(StockOrder stockOrder) throws Exception
	{
		if(isStockPresent(stockOrder.getStock()))
		{
			boolean canAmountBeSold =  currentStocks.stream().filter(holding -> holding.getStock().getTicker().equals(stockOrder.getStock().getTicker()))
			                      .findFirst().map(CurrentHolding::getAmount)
			                      .map(amount -> amount >=stockOrder.getAmount()?true:false).get();
			if(canAmountBeSold)
				return true;
			else
				throw new Exception("not enough shares of "+stockOrder.getStock().getTicker());
		}
		else
			 throw new Exception("user doesn't have any shares of  "+stockOrder.getStock().getTicker());
		
	}
	
	
	
	public boolean canStockBeBought(StockOrder stockOrder) throws Exception
	{
		double totalCost = stockOrder.getAmount() * stockOrder.getPrice();
		
		if(balance >= totalCost)
			return true;
			else
				 throw new Exception("not enough cash");
	}

	
	public void buyStocks(StockOrder stockOrder) throws Exception
	{
		
		canStockBeBought(stockOrder);
		
		Stock innerStock = new Stock();
		innerStock.setPrice(stockOrder.getPrice());
		innerStock.setTicker(stockOrder.getStock().getTicker());
		
		CurrentHolding newestHolding = new CurrentHolding();
		boolean isTickerPresent;
		String orderTicker = stockOrder.getStock().getTicker();
		System.out.println("before loop Ticker");
		isTickerPresent = currentStocks.stream().anyMatch(holding -> holding.getStock().getTicker().equals(orderTicker));
		newestHolding.setAmount(stockOrder.getAmount());
		newestHolding.setStock(stockOrder.getStock()); 
		newestHolding.setValue(newestHolding.getAmount()*newestHolding.getStock().getPrice());
		
		if (!isTickerPresent)
		{
			currentStocks.add(newestHolding);
			
		}
		else
		{
			newestHolding.setAmount(stockOrder.getAmount());
			newestHolding.setStock(innerStock);
			newestHolding.setValue(innerStock.getPrice()*stockOrder.getAmount());
			
			
			
			for(CurrentHolding oldHolding: currentStocks)
			{ 
				
				if(oldHolding.getStock().getTicker().equals(newestHolding.getStock().getTicker()))
				{
					newestHolding.getStock().setPrice((oldHolding.getValue()+newestHolding.getValue())/(oldHolding.getAmount()+newestHolding.getAmount()));
				
					newestHolding.setAmount(newestHolding.getAmount()+oldHolding.getAmount());
		
					newestHolding.setValue(newestHolding.getStock().getPrice()*newestHolding.getAmount());		
				}
				
				currentStocks.removeIf(holding -> holding.getStock().getTicker().equals(stockOrder.getStock().getTicker()));
				currentStocks.add(newestHolding);
						
			}                    
		}
		
	}
	
	
	public void sellStocks(StockOrder stockOrder) throws Exception
	{
		
		canStockBeSold(stockOrder); //throws exception if stock cannot be sold
		
		CurrentHolding oldHolding = currentStocks.stream().filter(currentHolding -> currentHolding.getStock().getTicker().equals(stockOrder.getStock().getTicker()))
				                                          .findFirst().get();
		
		oldHolding.setAmount(oldHolding.getAmount()-stockOrder.getAmount());
		oldHolding.setValue(oldHolding.getAmount()*oldHolding.getStock().getPrice());
		
		
	}
	
	
	
	
	public void updateBalanceFromStockOrder(StockOrder stockOrder) throws Exception
	{
		if("SELL".equals(stockOrder.getType()))
		{
			if(canStockBeSold(stockOrder))
			{
				balance += stockOrder.getAmount() * stockOrder.getPrice();
			}
			else
			throw new Exception("not enough Stocks");
			
		}
		else
		{
			if(canStockBeBought(stockOrder))
			{
				balance -= stockOrder.getAmount() * stockOrder.getPrice();
			}
			else
				throw new Exception("not enough cash");
		}
	}
	
	
	public void updateCurrentStocks(StockOrder stockOrder) throws Exception
	{
		if("BUY".equals(stockOrder.getType()))
		{
			buyStocks(stockOrder);
		}
		else {
			if("SELL".equals(stockOrder.getType()))
		{
			sellStocks(stockOrder);
		}
			throw new Exception("unsupported operation");
		    }		
	}
	
	
	public void addStocksOrder(StockOrder stockOrder) throws Exception
	{
		System.out.println(stockOrder+"inside Stock Order");
		
		
		updateBalanceFromStockOrder(stockOrder);
		
		stockOrderHistory.add(stockOrder);
		
		
		updateCurrentStocks(stockOrder);
		
		System.out.println(this);
		
		
	}
	public void makeDeposit(double deposit)
	{
		balance += deposit;
	}
	public double withdraw(double withdrawAmount)
	{
		balance -= withdrawAmount;
		return balance;
	}
	
	public List<CurrentHolding> getCurrentStocks() {
		return currentStocks;
	}

	public void setCurrentStocks(List<CurrentHolding> currentStocks) {
		this.currentStocks = currentStocks;
	}

	@JsonIgnore
	public long getDepotId() {
		return depotId;
	}
	@JsonIgnore
	public void setDepotId(long depotId) {
		this.depotId = depotId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<StockOrder>  getStockOrderHistory() {
		return stockOrderHistory;
	}

	public void setStockOrderHistory(List<StockOrder> stockOrderHistory ) {
		this.stockOrderHistory = stockOrderHistory;
	}

	@Override
	public String toString() {
		return "Depot [depotId=" + depotId + ", balance=" + balance + ", stockOrderHistory=" + stockOrderHistory
				+ ", currentStocks=" + currentStocks + "]";
	}


	
}
