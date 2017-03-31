package com.docker.mobyartshop.service;

import java.util.List;

import com.docker.mobyartshop.model.Product;

public interface ProductService {
	
	Product findByName(String name);
		
	List<Product> findAllProducts();

	Product findById(Long productId);
	

}
