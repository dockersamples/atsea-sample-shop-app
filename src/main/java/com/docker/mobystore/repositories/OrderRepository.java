package com.docker.mobystore.repositories;

import com.docker.mobystore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	Order findOne(Long orderid);
}