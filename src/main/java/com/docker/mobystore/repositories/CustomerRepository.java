package com.docker.mobystore.repositories;

import com.docker.mobystore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	//Customer findOne(Long customerId);
	Customer findByName(String name);
}
