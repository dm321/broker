package com.dm.broker.mode.dto;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import com.dm.broker.model.Depot;

@Component
public class UserDTO {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Depot depot = new Depot();
	
	

	public UserDTO()
	{
		
	}

	public UserDTO(String firstName, String lastName, String email, String password, Depot depot) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.depot = depot;
	}




	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	@Override
	public String toString() {
		return "UserDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", depot=" + depot + "]";
	}
	
	
	

}
