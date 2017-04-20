package com.docker.atsea.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
	
	private static final long serialVersionUID = -8697455919895226841L;

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long customerId;
	
	@NotEmpty
    @Column(name = "name", length = 255, nullable = false)
    private String name;
	
	@NotEmpty
	@Column(name = "address", length = 512, nullable = false)
	
	private String address;
	@NotEmpty
	@Column(name = "email", length = 128, nullable = false)
    private String email;
	
	@NotEmpty
	@Column(name = "phone", length = 32, nullable = false)
    private String phone;
	
	@NotEmpty
	@Column(name = "username", length = 255, nullable = false)
    private String username;
	
	@NotEmpty
	@Column(name = "password", length = 255, nullable = false)
    private String password;
	
	@Column(name = "enabled", nullable = false)
	@Type( type = "org.hibernate.type.NumericBooleanType")
	private boolean enabled;
	
	@NotEmpty
	@Column(name = "role", columnDefinition = "varchar(5) DEFAULT 'USER'")
	private String role;
	
	public Customer() {
		
	}
	
	public Customer(Long customerId, String name, String address, String email, String phone,
			String username, String password, Boolean enabled, String role) {
		this.customerId = customerId;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
	}
	
	public Long getCustomerId() {
    	return customerId;
    }
    
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getAddress() {
        return address;
    }
 
    public void setAddress(String address) {
        this.address = address;
    }
 
    public String getPhone() {
        return phone;
    }
 
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
       
    public Boolean getEnabled() {
    	return enabled;
    }
    
    public void setEnabled(Boolean enabled) {
    	this.enabled = enabled;
    }
    
    public String getRole() {
    	return role;
    }
    
    public void setRole(String role) {
    	this.role = role;
    }
    
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId 
				          + ", name=" + name 
				          + ", username=" + username
				          + ", address=" + address 
				          + ", email=" + email 
				          + ", phone=" + phone 
				          + ", password=" + password + 
				          "]";
	}
    
}
