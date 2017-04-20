package com.docker.atsea.service;

import java.util.List;

import com.docker.atsea.model.Order;

public interface OrderService {

	List<Order> findAllOrders();
	
	Order findById(Long orderId);
	
	Order createOrder(Order order);
	
	void saveOrder(Order order);
	
	void updateOrder(Order order);
	
	void deleteOrderById(Long orderId);
	
	void deleteAllItems();

	boolean orderExists(Order order);	
}