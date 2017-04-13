package com.docker.atsea.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.docker.atsea.model.Customer;
import com.docker.atsea.repositories.CustomerRepository;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	public Customer findById(Long customerId) {
		return customerRepository.findOne(customerId);
	}

	public Customer findByUserName(String name) {
		return customerRepository.findByUserName(name);
	}

	public Customer findByName(String name) {
		return customerRepository.findByName(name);
	}
	
	public Customer createCustomer(Customer customer) {		
		customer = customerRepository.save(customer);
		customerRepository.flush();
		return customer;
	}
	
	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	public void updateCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	public void deleteAllCustomers() {
		customerRepository.deleteAll();
	}

	public List<Customer> findAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}
	
	public boolean customerExist(Customer customer) {
		System.out.println(customer.getUsername());
		return customerRepository.findByUserName(customer.getUsername()) != null;
	}

	public void deleteCustomerById(Long customerId) {
		customerRepository.delete(customerId);		
	}
}
