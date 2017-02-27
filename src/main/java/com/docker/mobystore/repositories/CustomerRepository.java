package com.docker.mobystore.repositories;

import com.docker.mobystore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findById(Long customerId);
}
