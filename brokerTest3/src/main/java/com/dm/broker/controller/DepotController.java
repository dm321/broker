package com.dm.broker.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dm.broker.model.DepositRequest;
import com.dm.broker.model.Depot;
import com.dm.broker.model.DepotDTO;
import com.dm.broker.model.OrderRequest;
import com.dm.broker.model.UserDTO;
import com.dm.broker.model.WithdrawRequest;
import com.dm.broker.service.DepotServiceImpl;

@RestController
public class DepotController {

	@Autowired
	private DepotServiceImpl depotServiceImpl;
	@Autowired
	private Mapper mapper;
	
	@PutMapping("/users/depot/deposit")
	public ResponseEntity<Depot> makeDeposit(@RequestBody DepositRequest depositRequest)
	{
		String email = depositRequest.getEmail();
		double amount = depositRequest.getAmount();
		
		Optional<Depot> depot = depotServiceImpl.makeDeposit(email, amount);
		if(depot.isPresent())
		{
			
			return ResponseEntity.status(HttpStatus.OK).body(depot.get());
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/users/depot/withdraw")
	public ResponseEntity<Depot> withdraw(@RequestBody WithdrawRequest withdrawRequest)
	{
		String email = withdrawRequest.getEmail();
		double amount = withdrawRequest.getAmount();
		
		Optional<Depot> depot = depotServiceImpl.withdrawMoney(email, amount);
		if(depot.isPresent())
		{
			return ResponseEntity.status(HttpStatus.OK).body(depot.get());
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("users/depot/orders")
	public ResponseEntity<Depot> placeOrder(@RequestBody OrderRequest orderRequest)
	{
		System.out.println(LocalDateTime.now());
		System.out.println(orderRequest);
		String email = orderRequest.getEmail();
		String ticker = orderRequest.getTicker();
		double price = orderRequest.getPrice();
		double amount = orderRequest.getAmount();
		String orderType = orderRequest.getType();
		
		Optional<Depot> depot = depotServiceImpl.placeOrder(email, ticker, price, amount, orderType);
		if(depot.isPresent())
		{
			return ResponseEntity.status(HttpStatus.OK).body(depot.get());
		}
		return ResponseEntity.badRequest().build();
		
	}
}
