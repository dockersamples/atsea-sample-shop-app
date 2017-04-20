package com.docker.atsea.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long orderId;
	
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "orderdate" )
    private Date orderDate;
        
    @Column(name = "customerid")
    private Long customerId;
    
    @ElementCollection
    @MapKeyColumn(name="productid")
    @Column(name = "productsordered")
    @CollectionTable(name="orderquantities", joinColumns=@JoinColumn(name="orderid"))
    Map<Integer, Integer> productsOrdered = new HashMap<Integer, Integer>();

    
    public Order(){
		
	}
	
	public Order(Long orderId, Date orderDate, Long productId, Map<Integer, Integer> productsOrdered) {
    	this.orderId = orderId;
    	this.orderDate = orderDate;
    	this.productsOrdered = productsOrdered;
	}

    public Order(Long orderId, Date orderDate, Long productId, Map<Integer, Integer> productsOrdered, Long customerId) { 
    	this.orderId = orderId;
    	this.orderDate = orderDate;
    	this.productsOrdered = productsOrdered;
    	this.customerId = customerId;
    };

	public Long getCustomerId() {
		return customerId;
	}
	
    public void setCustomerId(Long customerId) {
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
        
    public Map<Integer, Integer> getProductsOrdered() {
    	return productsOrdered;
    }
    
    public void setProductsOrdered(Map<Integer, Integer> productsOrdered) {
    	this.productsOrdered = productsOrdered;
    }
    	
	@Override
	public String toString() {
		return "Order [customerId = " + customerId + 
				      "orderDate= " + orderDate + 
				      "orderId = "+ orderId + 
				      "productsOrdered = " + productsOrdered +
				      "]";
	}
}
