package com.dm.broker;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dm.broker.model.User;
import com.dm.broker.persistance.UserRepo;

@SpringBootApplication
public class BrokerTest3Application {
	
	@Autowired
	private UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(BrokerTest3Application.class, args);
		
		
	}

	@Bean
	public Mapper getMapper()
	{
		return new DozerBeanMapper();
	}
}
