package com.docker.atsea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docker.atsea.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Product findByName(String name);
}
