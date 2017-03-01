package com.docker.mobystore.service;

import java.util.List;

import com.docker.mobystore.model.Product;
import com.docker.mobystore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	public Product findByName(String name) {
		return productRepository.findByName(name);
	}

	public Product findById(Long productId) {
		return productRepository.findOne(productId);
	}
}
