package com.dm.broker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dm.broker.model.Depot;
import com.dm.broker.model.Stock;
import com.dm.broker.model.StockOrder;
import com.dm.broker.model.User;
import com.dm.broker.persistance.UserRepo;

@Service
public class DepotServiceImpl implements DepotService {

	@Autowired
	private UserRepo userRepo;
	private User user;
	
	
	@Override
	@Transactional
	public Optional<Depot> makeDeposit(String email, double amount) {

		if(email != null && amount > 0)
		{
			user = userRepo.findByEmail(email);
			if(user != null)
			{
				user.getDepot().makeDeposit(amount);
				userRepo.save(user);
				return Optional.of(user.getDepot());
			}
		}
		return Optional.empty();
	}


	@Override
	public Optional<Depot> withdrawMoney(String email, double amount) {
		if (email !=null)
			
			user = userRepo.findByEmail(email);
		    if(user != null && amount >= user.getDepot().getBalance())
		    {
		    	user.getDepot().withdraw(amount);
		    	userRepo.save(user);
		    	return Optional.of(user.getDepot());
		    }
		    return Optional.empty();
	}


	@Override
	public Optional<Depot> placeOrder(String email, String ticker, double price, double amount, String type) {
		
		double totalPrice = price * amount;
		System.out.println("before if");
		System.out.println(email+" "+ticker+" "+price+" "+amount+" "+type);
		type = type.toUpperCase();
		if(email !=null && ticker!= null && amount >0 && ("BUY".equals(type) || "SELL".equals(type)))
		{
	
			user = userRepo.findByEmail(email);
			if(totalPrice <= user.getDepot().getBalance())
			{
				StockOrder stockOrder = new StockOrder();
				stockOrder.setType(type);
				stockOrder.setAmount(amount);
				stockOrder.setPrice(price);
				
				Stock stock = new Stock();
				stock.setPrice(price);
				stock.setTicker(ticker);
				stockOrder.setStock(stock);
						
			
				try {
					System.out.println("inside try");
					user.getDepot().addStocksOrder(new StockOrder(type, price, amount, ticker, stock));
				} catch (Exception e) {
					e.printStackTrace();
				}
				userRepo.save(user);
				return Optional.of(user.getDepot());	
			}
			
			}
		return Optional.empty();
		}
		
	}



