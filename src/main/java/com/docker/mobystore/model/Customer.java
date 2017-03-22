package com.docker.mobystore.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.CascadeType;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "customer")
//@JsonInclude(Include.NON_NULL)
public class Customer implements Serializable {
	
	private static final long serialVersionUID = -8697455919895226841L;

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
//	@SequenceGenerator(name="customer_customerid_seq", 
//	                   sequenceName="customer_customerid_seq",
//	                   allocationSize=1)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,
//	                generator="customer_customerid_seq")
	//@Column(name="customerid", updatable=false)
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
	
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@NotEmpty
	@Column(name = "role", columnDefinition = "varchar(5) DEFAULT 'USER'")
	private String role;
	
//	@OneToMany(mappedBy = "customerId", cascade = CascadeType.ALL)
//	private Set<Order> orders = new HashSet<Order>();
	
//	public Set<Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(Set<Order> orders) {
//		this.orders = orders;
//	}
	
//	public Customer() {
//		
//	}
//	
//	public Customer(Long customerId, String name, String address, String email, String phone,
//			String username, String password, Set<Order> orders) {
//		this.customerId = customerId;
//		this.name = name;
//		this.address = address;
//		this.email = email;
//		this.phone = phone;
//		this.username = username;
//		this.password = password;
//		this.orders = orders;
//	}
	
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Customer customer = (Customer) o;

		//if (Double.compare(user.salary, salary) != 0) return false;
		if (customerId != null ? !customerId.equals(customer.customerId) : customer.customerId != null) return false;
		if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
		return username != null ? username.equals(customer.username) : customer.username == null;
	}

//	@Override
//	public int hashCode() {
//		int result;
//		long temp;
//		result = customerId != null ? customerId.hashCode() : 0;
//		result = 31 * result + (name != null ? name.hashCode() : 0);
//		result = 31 * result + (username != null ? username.hashCode() : 0);
//		//temp = Double.doubleToLongBits(salary);
//		//result = 31 * result + (int) (temp ^ (temp >>> 32));
//		return result;
//	}

//	@Override
//	public String toString() {
//		return "Customer [customerId=" + customerId 
//				          + ", name=" + name 
//				          + ", username=" + username
//				          + ", address=" + address 
//				          + ", email=" + email 
//				          + ", phone=" + phone 
//				          + ", password=" + password +  "]";
//	}
//    
}
