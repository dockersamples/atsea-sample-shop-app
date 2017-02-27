package com.docker.mobystore.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cart", uniqueConstraints = { @UniqueConstraint(columnNames = "cartid")})
public class Cart implements Serializable {
	
	private static final long serialVersionUID = -4035480148215508477L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customerId")
	private  Customer customer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderNum")
	private Order orderNum;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productId")
	private Product productId;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "orderdate")
	private Date orderDate;
	
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	
	public long getCartId() {
		return cartId;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Product getProductId() {
		return getProductId();
	}
	
	public void setProduct(Product productId) {
		this.productId = productId;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public double setPrice() {
		return price;
	}
	
	public void getPrice(double price) {
		this.price = price;
	}

	public Date getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
    public Order getOrderNum() {
    	return orderNum;
    }
    
    public void setOrderNum(Order order) {
    	this.orderNum = orderNum;
    }
}
