package com.docker.mobystore.repositories;

import com.docker.mobystore.model.Order;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface OrderRepository extends CrudRepository<Order, Long> {
	
	//Order findOne(Long orderId);
	public List<Order> findByOrderNum(int orderNum);
}