package com.docker.mobystore.model;

import java.io.Serializable;
import java.util.Date;
 
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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "orders", uniqueConstraints = { @UniqueConstraint(columnNames = "orderid") })
public class Order implements Serializable{

	private static final long serialVersionUID = 8367647197454666804L;
	
	@Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long orderId;
	
    @NotEmpty
    @Temporal(TemporalType.DATE)
    @Column(name = "orderdate" )
    private Date orderDate;
    
    @NotEmpty
    @Column(name = "ordernum", nullable=false)
    private int orderNum;
    
    @NotEmpty
    @ManyToOne(optional=false)
    @JoinColumn(name = "customerid", nullable = false )
    private Customer customerId;
    
	
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
    
    public long getOrderNum() {
    	return orderNum;
    }
    
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
    
    public Customer getCustomerId() {
    	return customerId;
    }
    
    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

}
