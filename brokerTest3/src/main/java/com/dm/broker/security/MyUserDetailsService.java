package com.dm.broker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dm.broker.model.User;
import com.dm.broker.persistance.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	private String password;
	private User user;
	private MyUserDetails myUserDetails;
	
	
	@Override
	public MyUserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
	
		user = userRepo.findByEmail(Email);
		if(user!=null)
		{
			password = user.getPassword();
			myUserDetails = new MyUserDetails(Email, password);
			return myUserDetails;
		}
		else
		{
			throw new UsernameNotFoundException("user "+Email+" do not exist");
		}
		
	}

}
