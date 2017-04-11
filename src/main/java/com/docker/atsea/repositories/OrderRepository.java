package com.docker.atsea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docker.atsea.model.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {


}
