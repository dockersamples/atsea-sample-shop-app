package com.docker.mobystore.model;


import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.*;

public class CustomerLogin {
	
	@NotEmpty
	@Size(min=8, max=32)
	private String userName;
	
	@NotEmpty
	@Size(min=8, max=32)
	private String password;
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setCustomerName() {
		this.userName = userName;
	}
}
