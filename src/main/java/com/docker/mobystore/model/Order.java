package com.docker.mobystore.model;

import com.docker.mobystore.model.Customer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "orders", uniqueConstraints = { @UniqueConstraint(columnNames = "orderid") })
public class Order implements Serializable {
	private static final long serialVersionUID = 8367647197454666804L;

	@Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long orderId;
	
	//private Long orderCustomerId;
	
	@OneToOne(optional=false, cascade = CascadeType.ALL)
	@JoinColumn(name = "ordercustomerid", nullable = false )
	private Customer customer;
	
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "orderdate" )
    private Date orderDate;
        
    @Column(name = "ordernum", nullable=false)
    private Integer orderNum;
  
	public Order(){
		
	}

    public Order(Long orderId, Integer orderNum, Date orderDate) {
    	this.orderId = orderId;
    	this.orderNum = orderNum;
    	this.orderDate = orderDate;
    };

    public Long getCustomer() {
    	return customer.customerId();
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
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
    

	@Override
	public String toString() {
		return String.format("Order[customer=%d, orderDate=%s, orderId=%d, orderNum=%s]",
				customer, orderDate, orderId, orderNum);
	}
}
