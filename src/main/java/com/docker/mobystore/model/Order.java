package com.docker.mobystore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "orders")
@JsonInclude(Include.NON_NULL)
public class Order implements Serializable {
	private static final long serialVersionUID = 8367647197454666804L;

	@Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private Long orderId;
	
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "orderdate" )
    private Date orderDate;
        
    @Column(name = "customerid")
    private Long customerId;

    @ElementCollection
    @Column(name = "productsordered")
    private Set<String> productsOrdered = new HashSet<String>();
        
    public Order(){
		
	}
	
	public Order(Long orderId, Date orderDate, Long productId, Set<String> productsOrdered) {
    	this.orderId = orderId;
    	this.orderDate = orderDate;
    	this.productsOrdered = productsOrdered;
	}

    public Order(Long orderId, Date orderDate, Long productId, Set<String> productsOrdered, Long customerId) { 
    	this.orderId = orderId;
    	this.orderDate = orderDate;
    	this.productsOrdered = productsOrdered;
    	this.customerId = customerId;
    };

	public Long getCustomerId() {
		return customerId;
	}
	
    public void setCustomer(Long customerId) {
        this.customerId = customerId;
    }
    
    public long getOrderId() {
    	return orderId;
    }
    
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    
    public Date getOrderDate() {
    	return orderDate;
    }
    
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
        
    public Set<String> getProductsOrdered() {
    	return productsOrdered;
    }
    
    public void setProductsOrdered(Set<String> productsOrdered) {
    	this.productsOrdered = productsOrdered;
    }
    	
	@Override
	public String toString() {
		return String.format("Order[customerId = " + customerId + 
				                    "orderDate= " +orderDate + 
				                    "orderId = "+ orderId + 
				                    "productsOrdred = " + productsOrdered);
	}
}
