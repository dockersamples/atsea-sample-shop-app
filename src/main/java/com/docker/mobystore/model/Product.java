package com.docker.mobystore.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="products")

public class Product implements Serializable {

	private static final long serialVersionUID = 3222530297013481114L;
	
    // For sort.
    private Date createDate;
 
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long productId;
    
    @NotEmpty
    @Column(name = "name", length = 255, nullable = false)
    private String name;
    
    @NotEmpty
    @Column(name = "price", nullable = false)
    private double price;
    
    
    @Column(name = "description", length=10485760, nullable = false)
    private String description;
        
    @Lob
    @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image; 
    
    public long getProductId() {
    	return productId;
    }
    
    public void setProductId(long productId) {
        this.productId = productId;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getDescription() {
    	return description;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }
  
    public byte[] getImage() {
        return image;
    }
 
    public void setImage(byte[] image) {
        this.image = image;
    }

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}	
	
}
