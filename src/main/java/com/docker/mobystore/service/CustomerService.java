package com.docker.mobystore.service;

import java.util.List;

import com.docker.mobystore.model.Customer;

public interface CustomerService {

	Customer findById(Long customerId);

	Customer findByUserName(String name);
	
	Customer findByName(String name);
	
	Customer createCustomer(Customer customer);

	void saveCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void deleteCustomerById(Long customerId);

	void deleteAllCustomers();

	List<Customer> findAllCustomers();

	boolean customerExist(Customer customer);
}
