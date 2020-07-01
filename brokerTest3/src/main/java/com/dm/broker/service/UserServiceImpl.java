package com.dm.broker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dm.broker.model.User;
import com.dm.broker.model.UserDTO;
import com.dm.broker.persistance.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public Optional<User> registerUser(String email, String password) {
		
		User user;
		
		if(email != null && password != null)
		{
			user = userRepo.findByEmail(email);	
			if(user!=null) {
				return Optional.empty();
			}
			else
			{
				user = new User();
				user.setEmail(email);
				user.setPassword(password);
				return Optional.of(userRepo.save(user));
			}	
		}
			return Optional.empty();
	}
	}

