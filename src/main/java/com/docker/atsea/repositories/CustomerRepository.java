package com.docker.atsea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.docker.atsea.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findOne(Long customerId);
	
	Customer findByName(String name);
	
	// adding find by username
	@Query("SELECT c FROM Customer c WHERE c.username = :userName")
	Customer findByUserName(@Param("userName") String userName);
}

