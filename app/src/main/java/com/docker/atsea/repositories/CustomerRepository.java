package com.docker.atsea.repositories;

import com.docker.atsea.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByName(String name);

	Customer findByUsername(String username);
}

