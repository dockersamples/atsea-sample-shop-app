package com.docker.mobystore.service;

import com.docker.mobystore.model.Product;
import java.util.List;

public interface ProductService {
	Product findOne(Long productId);
	
	Product findByName(String name);
		
	List<Product> findAllProducts();

	Product findById(Long productId);
	

}
