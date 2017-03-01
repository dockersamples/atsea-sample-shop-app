package com.docker.mobystore.service;


import com.docker.mobystore.model.Order;
import com.docker.mobystore.model.Product;

public interface OrderService {

	Order findById(Long orderId);
	
	void saveOrder(Order order);
	
	void updateOrder(Order order);
	
	void deleteOrderById(Long orderId);
	
	void deleteAllItems();

	boolean orderExists(Order order);
	
}