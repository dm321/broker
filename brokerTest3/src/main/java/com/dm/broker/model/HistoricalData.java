package com.dm.broker.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.Mapping;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeName;

public class HistoricalData {


@JsonProperty("c")
private List<Double> CandleClosePriceList;

@JsonProperty("h")
private List<Double> CandleHighPriceList;	

@JsonProperty("l")
private List<Double> CandleLowPriceList;	

@JsonProperty("o")
private List<Double> CandleOpenPriceList;	

@JsonProperty("t")
private List<LocalDateTime> CandleTimestapList;	

@JsonProperty("v")
private List<Double> CandleVolumeList;





public HistoricalData() {
	super();
}




@JsonProperty("c")
public List<Double> getCandleClosePriceList() {
	return CandleClosePriceList;
}




@JsonProperty("h")
public List<Double> getCandleHighPriceList() {
	return CandleHighPriceList;
}




@JsonProperty("l")
public List<Double> getCandleLowPriceList() {
	return CandleLowPriceList;
}




@JsonProperty("o")
public List<Double> getCandleOpenPriceList() {
	return CandleOpenPriceList;
}




@JsonProperty("t")
public List<LocalDateTime> getCandleTimestapList() {
	return CandleTimestapList;
}




@JsonProperty("v")
public List<Double> getCandleVolumeList() {
	return CandleVolumeList;
}




@JsonProperty("c")
public void setCandleClosePriceList(List<Double> candleClosePriceList) {
	CandleClosePriceList = candleClosePriceList;
}




@JsonProperty("h")
public void setCandleHighPriceList(List<Double> candleHighPriceList) {
	CandleHighPriceList = candleHighPriceList;
}




@JsonProperty("l")
public void setCandleLowPriceList(List<Double> candleLowPriceList) {
	CandleLowPriceList = candleLowPriceList;
}




@JsonProperty("o")
public void setCandleOpenPriceList(List<Double> candleOpenPriceList) {
	CandleOpenPriceList = candleOpenPriceList;
}




@JsonProperty("t")
public void setCandleTimestapList(List<Long> candleTimestapList) {
	
	CandleTimestapList = candleTimestapList.stream().map(timestamp -> Instant.ofEpochSecond(timestamp))
	                           .map(instant -> instant.atZone(ZoneId.systemDefault()).toLocalDateTime())
	                           .collect(Collectors.toList());
	
	

}




@JsonProperty("v")
public void setCandleVolumeList(List<Double> candleVolumeList) {
	CandleVolumeList = candleVolumeList;
}





@Override
public String toString() {
	return "HistoricalData [CandleClosePriceList=" + CandleClosePriceList + ", CandleHighPriceList="
			+ CandleHighPriceList + ", CandleLowPriceList=" + CandleLowPriceList + ", CandleOpenPriceList="
			+ CandleOpenPriceList + ", CandleTimestapList=" + CandleTimestapList + ", CandleVolumeList="
			+ CandleVolumeList + "]";
}

	




}
