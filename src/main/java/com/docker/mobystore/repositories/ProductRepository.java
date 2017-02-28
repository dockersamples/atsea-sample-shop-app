package com.docker.mobystore.repositories;

import com.docker.mobystore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Product findByName(String name);
}
