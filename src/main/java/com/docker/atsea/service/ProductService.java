package com.docker.atsea.service;

import java.util.List;

import com.docker.atsea.model.Product;

public interface ProductService {
	
	Product findByName(String name);
		
	List<Product> findAllProducts();

	Product findById(Long productId);
	

}
