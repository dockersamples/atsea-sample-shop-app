package com.docker.mobystore.repositories;

import com.docker.mobystore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
//	Customer findOne(Integer customerId);
	Customer findByName(String name);
	
//	@Query("SELECT c FROM Customer c WHERE c.username = :userName")
//	Customer findByUserName(@Param("userName") String userName);
}

