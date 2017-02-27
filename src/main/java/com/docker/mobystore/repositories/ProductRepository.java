package com.docker.mobystore.repositories;

import com.docker.mobystore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Product findById(Long productId);
	
	//Product findByName(String name);
}
