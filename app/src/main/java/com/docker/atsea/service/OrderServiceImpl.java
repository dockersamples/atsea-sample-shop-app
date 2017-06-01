package com.docker.atsea.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.docker.atsea.model.Order;
import com.docker.atsea.repositories.CustomerRepository;
import com.docker.atsea.repositories.OrderRepository;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
		
	public Order findById(Long orderId) {
		return orderRepository.findOne(orderId) ;
	}

	public Order createOrder(Order order) {		
		order = orderRepository.save(order);
		orderRepository.flush();
		return order;
	}

	public void saveOrder(Order order) {
		orderRepository.save(order);
	}
	
	public void updateOrder(Order order) {
		orderRepository.save(order);
	}

	public void deleteOrderById(Long orderId) {
		orderRepository.delete(orderId);
	}

	public void deleteAllItems() {
		orderRepository.deleteAll();
	}

	public boolean orderExists(Order order) {
		return findById(order.getOrderId()) != null;
	}

	public List<Order> findAllOrders() {
		return (List<Order>) orderRepository.findAll();
	}	
}
