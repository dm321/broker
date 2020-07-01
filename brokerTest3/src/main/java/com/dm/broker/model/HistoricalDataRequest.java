package com.dm.broker.model;



public class HistoricalDataRequest {

	private String ticker;
	
	private String resolution;
	
	private long from;
	
	private long to;

	
	
	
	public HistoricalDataRequest() {
		super();
	}

	public String getTicker() {
		return ticker;
	}

	public String getResolution() {
		return resolution;
	}

	public long getFrom() {
		return from;
	}

	public long getTo() {
		return to;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public void setFrom(long from) {
		this.from = from;
	}

	public void setTo(long to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "HistoricalDataRequest [ticker=" + ticker + ", resolution=" + resolution + ", from=" + from + ", to="
				+ to + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (from ^ (from >>> 32));
		result = prime * result + ((resolution == null) ? 0 : resolution.hashCode());
		result = prime * result + ((ticker == null) ? 0 : ticker.hashCode());
		result = prime * result + (int) (to ^ (to >>> 32));
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
		HistoricalDataRequest other = (HistoricalDataRequest) obj;
		if (from != other.from)
			return false;
		if (resolution == null) {
			if (other.resolution != null)
				return false;
		} else if (!resolution.equals(other.resolution))
			return false;
		if (ticker == null) {
			if (other.ticker != null)
				return false;
		} else if (!ticker.equals(other.ticker))
			return false;
		if (to != other.to)
			return false;
		return true;
	}
	
	
}
