package com.docker.atsea.configuration;

import org.springframework.context.annotation.Bean;

import com.docker.atsea.service.CustomerService;
import com.docker.atsea.service.CustomerServiceImpl;
import com.docker.atsea.service.OrderService;
import com.docker.atsea.service.OrderServiceImpl;
import com.docker.atsea.service.ProductService;
import com.docker.atsea.service.ProductServiceImpl;

public class BeanConfiguration {

	@Bean
	public CustomerService customerService() {
		return new CustomerServiceImpl();
	}
	
	@Bean
	public OrderService orderService() {
		return new OrderServiceImpl();
	}
	
	@Bean
	public ProductService productService() {
		return new ProductServiceImpl();
	}

	
}
