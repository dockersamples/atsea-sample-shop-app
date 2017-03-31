package com.docker.mobyartshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docker.mobyartshop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Product findByName(String name);
}
