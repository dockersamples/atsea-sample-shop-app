package com.docker.mobystore.repositories;

import com.docker.mobystore.model.Order;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;


public interface OrderRepository extends CrudRepository<Order, Long> {

	//Order findOne(Long orderId);
	@Query("SELECT o FROM Order o WHERE o.orderNum = ?1")
	List<Order> findByOrderNum(@Param("ordernum") int orderNum);
	
}
