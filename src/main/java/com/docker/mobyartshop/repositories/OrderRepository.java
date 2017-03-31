package com.docker.mobyartshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.docker.mobyartshop.model.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {


}
