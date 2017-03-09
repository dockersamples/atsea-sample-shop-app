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
@NamedNativeQuery(name= "Order.findByOrderNum", query = "SELECT * FROM orders WHERE ordernum = 'orderNum'")
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
        
    @Column(name = "ordernum", nullable=false)
    private Integer orderNum;

    @JoinColumn(name = "customerId")
    private Long customerId;

    @Column(name = "productId")
    private Long productId;
    
    public Order(){
		
	}
	
	public Order(Long orderId, Integer orderNum, Date orderDate, Long productId) {
    	this.orderId = orderId;
    	this.orderNum = orderNum;
    	this.orderDate = orderDate;
    	this.productId = productId;
	}

    public Order(Long orderId, Integer orderNum, Date orderDate, Long customerId, Long productId) { 
    	this.orderId = orderId;
    	this.orderNum = orderNum;
    	this.orderDate = orderDate;
    	this.productId = productId;
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
    
    public Integer getOrderNum() {
    	return orderNum;
    }
    
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
    
    public void setProduct(Long productId) {
        this.productId = productId;
    }
    
    public long getProductId() {
    	return productId;
    }
	
	@Override
	public String toString() {
		return String.format("Order[customerId = " + customerId + 
				                    "orderDate= " +orderDate + 
				                    "orderId = "+ orderId + 
				                    "orderNum = " + orderNum +
				                    "productId = " + productId);
	}
}
