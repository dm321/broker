package com.dm.broker.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.dm.broker.model.HistoricalData;
import com.dm.broker.model.Quote;
import com.dm.broker.model.Stock;

import reactor.core.publisher.Mono;

@Service
public class StockServiceImpl implements StockService {

	private static final String TOKEN = "brmugifrh5r90ebn6nig";
    private static final String STOCK_API_BASE_URL = "https://finnhub.io/api/v1"; 
	private WebClient webClient;	
	private Optional<Quote> quote;
	private Optional<HistoricalData> historicalData;
	
	
	
	public StockServiceImpl() {
		this.webClient = WebClient.builder()
                .baseUrl(STOCK_API_BASE_URL)
                .build();
	}



	@Override
	public Optional<Stock> getCurrentStockPrice(String ticker) {
		
		return getQuote(ticker).map(quote-> quote.getCurrentPrice()).map(price -> {
                                                                     Stock stock = new Stock();
                                                                     stock.setTicker(ticker);
                                                                     stock.setPrice(price);
                                                                     return Optional.of(stock);})
				.orElseGet(Optional::empty);
		                                                                       				
	}

	@Override
	public Optional<Quote> getQuote(String ticker) {
		
				quote = Optional.ofNullable(webClient.get()
				.uri( uriBuilder ->
				      uriBuilder
				      .path("/quote")
				      .queryParam("symbol", ticker)
				      .queryParam("token", TOKEN)
				      .build())
				        .headers(header -> header.setContentType(MediaType.APPLICATION_JSON))
						.accept(MediaType.APPLICATION_JSON)
						.retrieve().bodyToMono(Quote.class).block());
				return quote;
	}



	@Override
	public Optional<HistoricalData> getHistoricalData(String ticker, String resolution, long from, long to) {
	         
		     
	         historicalData =  Optional.ofNullable(webClient.get()
				.uri( uriBuilder ->
			       uriBuilder
			      .path("/stock/candle")
			      .queryParam("symbol", ticker)
			      .queryParam("resolution", resolution)
			      .queryParam("from", from)
			      .queryParam("to", to)
			      .queryParam("token", TOKEN)
			      .build())
			        .headers(header -> header.setContentType(MediaType.APPLICATION_JSON))
					.accept(MediaType.APPLICATION_JSON)
					.retrieve().bodyToMono(HistoricalData.class).block());
	         
	         return historicalData;
	}

}
