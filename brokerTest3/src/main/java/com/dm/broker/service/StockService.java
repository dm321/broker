package com.dm.broker.service;

import java.util.Optional;

import com.dm.broker.model.HistoricalData;
import com.dm.broker.model.Quote;
import com.dm.broker.model.Stock;

import reactor.core.publisher.Mono;

public interface StockService {
	
	Optional<Stock> getCurrentStockPrice(String ticker);
	
	Optional<Quote>  getQuote(String ticker);
	
	Optional<HistoricalData> getHistoricalData(String ticker, String resolution, long from, long to);
	

}
