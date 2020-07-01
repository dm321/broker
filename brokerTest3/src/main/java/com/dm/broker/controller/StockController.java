package com.dm.broker.controller;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dm.broker.Exceptions.EmptyStockException;
import com.dm.broker.model.HistoricalData;
import com.dm.broker.model.HistoricalDataRequest;
import com.dm.broker.model.Stock;
import com.dm.broker.model.StockDTO;
import com.dm.broker.service.DepotServiceImpl;
import com.dm.broker.service.StockServiceImpl;

@RestController
public class StockController {
	
	
	@Autowired
	private StockServiceImpl serviceImpl;

	private String resolution;
	private long from;
	private long to;
	
	
	@GetMapping("/stocks/{symbol}/candle")
	public ResponseEntity<HistoricalData> retriveHistoricalData(@PathVariable String symbol, @RequestBody HistoricalDataRequest historicalDataRequest)
	{
	
	
		resolution = historicalDataRequest.getResolution();
		from = historicalDataRequest.getFrom();
		to = historicalDataRequest.getTo();
		
		Optional<HistoricalData> historicalData = serviceImpl.getHistoricalData(symbol, resolution, from, to);
		if(historicalData.isPresent())
		{
			return ResponseEntity.status(HttpStatus.OK).body(historicalData.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	@GetMapping("/stocks/{symbol}/currentprice")
	public ResponseEntity<Stock>  retriveCurrentStockPrice(@PathVariable String symbol)
	{
		
		    Optional<Stock> stockCurrentPrice = serviceImpl.getCurrentStockPrice(symbol);
		    if(stockCurrentPrice.isPresent())
		    {
		    	return ResponseEntity.status(HttpStatus.OK).body(stockCurrentPrice.get());
		    }
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				 	
	}

}
