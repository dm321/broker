package com.dm.broker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Quote {

	@JsonProperty("c")
	private double currentPrice;
	@JsonProperty("h")
	private double highPriceOfDay;
	@JsonProperty("l")
	private double lowPriceOfDay;
	@JsonProperty("o")
	private double openPriceOfDay;
	@JsonProperty("pc")
	private double previousClosePrice;
	@JsonProperty("t")
	private double timestamp;
	
	
	
	public Quote() {
		super();
	}

	@JsonProperty("c")
	public double getCurrentPrice() {
		return currentPrice;
	} 
	
	@JsonProperty("h")
	public double getHighPriceOfDay() {
		return highPriceOfDay;
	}

	@JsonProperty("l")
	public double getLowPriceOfDay() {
		return lowPriceOfDay;
	}

	@JsonProperty("o")
	public double getOpenPriceOfDay() {
		return openPriceOfDay;
	}

	@JsonProperty("pc")
	public double getPreviousClosePrice() {
		return previousClosePrice;
	}


	@JsonProperty("t")
	public double getTimestamp() {
		return timestamp;
	}


	@JsonProperty("c")
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}


	@JsonProperty("h")
	public void setHighPriceOfDay(double highPriceOfDay) {
		this.highPriceOfDay = highPriceOfDay;
	}


	@JsonProperty("l")
	public void setLowPriceOfDay(double lowPriceOfDay) {
		this.lowPriceOfDay = lowPriceOfDay;
	}


	@JsonProperty("o")
	public void setOpenPriceOfDay(double openPriceOfDay) {
		this.openPriceOfDay = openPriceOfDay;
	}


	@JsonProperty("pc")
	public void setPreviousClosePrice(double previousClosePrice) {
		this.previousClosePrice = previousClosePrice;
	}


	@JsonProperty("t")
	public void setTimestamp(double timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Quote [currentPrice=" + currentPrice + ", highPriceOfDay=" + highPriceOfDay + ", lowPriceOfDay="
				+ lowPriceOfDay + ", openPriceOfDay=" + openPriceOfDay + ", previousClosePrice=" + previousClosePrice
				+ ", timestamp=" + timestamp + "]";
	}




	
	

}
