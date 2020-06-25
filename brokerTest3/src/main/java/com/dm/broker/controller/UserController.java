package com.dm.broker.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.dm.broker.mode.dto.UserDTO;
import com.dm.broker.model.AuthenticationRequest;
import com.dm.broker.model.AuthenticationResponse;
import com.dm.broker.model.User;
import com.dm.broker.persistance.UserRepo;
import com.dm.broker.security.MyUserDetails;
import com.dm.broker.security.MyUserDetailsService;
import com.dm.broker.security.jwt.JwtUtil;

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
	
	private User user;
	
	
	@RequestMapping("/users")
	public String registerUser()
	{
		return "Hello";
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
