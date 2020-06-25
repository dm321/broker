package com.dm.broker.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dm.broker.model.User;

public interface UserRepo  extends JpaRepository<User, Integer>{
	
	User findByEmail(String email);

}
