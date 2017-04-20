package com.docker.atsea.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="product", uniqueConstraints = { @UniqueConstraint(columnNames = "productid")})
@JsonInclude(Include.NON_NULL)
public class Product implements Serializable {

	private static final long serialVersionUID = 3222530297013481114L;
	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    
    @NotEmpty
    @Column(name = "name", length = 255, nullable = false)
    private String name;
    
    @NotEmpty
    @Column(name = "price", nullable = false)
    private double price;
    
    @Column(name = "description", length=10485760, nullable = false)
    private String description;
        
    @NotEmpty
    @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
    private String image; 
          
	public Product() {
		
	}
	
	public Product(Long productId, String name, String description, double price, String image) {
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
	}

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
  
    public String getImage() {
        return image;
    }
 
    public void setImage(String image) {
        this.image = image;
    }
    
	@Override
	public String toString() {
		return "Product [productId=" + productId +
				         ", name=" + name +
				         ", price=" + price +
				         ", description=" + description +
				         ", image=" + image + 
				         "]";
	}
	
}
