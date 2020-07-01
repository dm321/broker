package com.dm.broker.service;

import java.util.Optional;

import com.dm.broker.model.User;
import com.dm.broker.model.UserDTO;

public interface UserService {

	Optional<User> registerUser(String email, String password);
}
