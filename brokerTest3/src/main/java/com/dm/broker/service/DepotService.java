package com.dm.broker.service;

import java.util.Optional;

import com.dm.broker.model.Depot;


public interface DepotService {
	
	Optional<Depot> makeDeposit(String email, double amount);
	
	Optional<Depot> withdrawMoney(String email, double amount);
	
	Optional<Depot> placeOrder(String email, String ticker, double price, double amount, String type);

}
