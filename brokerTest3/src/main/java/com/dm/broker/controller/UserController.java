package com.dm.broker.controller;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dm.broker.model.AuthenticationRequest;
import com.dm.broker.model.AuthenticationResponse;
import com.dm.broker.model.RegistrationRequest;
import com.dm.broker.model.User;
import com.dm.broker.model.UserDTO;
import com.dm.broker.persistance.UserRepo;
import com.dm.broker.security.MyUserDetails;
import com.dm.broker.security.MyUserDetailsService;
import com.dm.broker.security.jwt.JwtUtil;
import com.dm.broker.service.StockServiceImpl;
import com.dm.broker.service.UserServiceImpl;

@RestController
public class UserController {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private StockServiceImpl serviceImpl;
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private Mapper mapper;
	private User user;
	

	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody RegistrationRequest registrationRequest)
	{
		String email = registrationRequest.getEmail();
		String password = registrationRequest.getPassword();
		
		Optional<User> user = userServiceImpl.registerUser(email, password);
		if(user.isPresent())
		{
			UserDTO userDTO = mapper.map(user.get(), UserDTO.class);
			return  ResponseEntity.status(HttpStatus.OK).body(userDTO);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("FailureCode", "User already exists").build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception
	{
		try 
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
		}
		catch (BadCredentialsException e)
		{
			throw new Exception("Incorrect username or password",e);
		}
		final MyUserDetails MyUserDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		
		final String jwt = jwtUtil.generateToken(MyUserDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	
	
	}

}
