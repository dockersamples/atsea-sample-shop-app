package com.docker.atsea.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.docker.atsea.model.Customer;
import com.docker.atsea.service.CustomerService;
import com.docker.atsea.util.CustomErrorType;
import com.docker.atsea.util.CustomerInfo;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	public static final Logger logger = LoggerFactory.getLogger(CustomerController.class);


	@Autowired
	CustomerService customerService;

	// -------------------------------------------------------------------
	//                   Customer methods
	//--------------------------------------------------------------------
	
	// -------------------Retrieve All Customers---------------------------------------------

	@RequestMapping(value = "/customer/", method = RequestMethod.GET)
	public ResponseEntity<List<JSONObject>> listAllUsers() {
		List<Customer> customer = customerService.findAllCustomers();
		if (customer.isEmpty()) {
			return new ResponseEntity<List<JSONObject>>(HttpStatus.NO_CONTENT);
		}
		
		List<JSONObject> customerData = new ArrayList<JSONObject>();
		CustomerInfo customerInfo = new CustomerInfo();
		for (Customer tempCustomer : customer) {
			customerData.add(customerInfo.getCustomerInfo(tempCustomer));
		}
		return new ResponseEntity<List<JSONObject>>(customerData, HttpStatus.OK);
	}

	// -------------------Retrieve Single Customer by Id------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<?> getCustomer(@PathVariable("customerId") long customerId) {
		logger.info("Fetching Customer with id {}", customerId);
		Customer customer = customerService.findById(customerId);
		if (customer == null) {
			logger.error("Customer with id {} not found.", customerId);
			return new ResponseEntity(new CustomErrorType("Customer with id " + customerId 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		
		CustomerInfo customerInfo = new CustomerInfo();
		JSONObject customerData = customerInfo.getCustomerInfo(customer);
		return new ResponseEntity<JSONObject>(customerData, HttpStatus.OK);
	}
	
	// -------------------Retrieve Single Customer by UserName------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/customer/username={userName}", method = RequestMethod.GET)
	public ResponseEntity<?> getCustomerByUserName(@PathVariable("userName") String userName) {
		logger.info("Fetching Customer with username {}", userName);
		Customer customer = customerService.findByUserName(userName);
		if (customer == null) {
			logger.error("Customer with username {} not found.", userName);
			return new ResponseEntity(new CustomErrorType("Customer with username " + userName 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		
		CustomerInfo customerInfo = new CustomerInfo();
		JSONObject customerData = customerInfo.getCustomerInfo(customer);
		return new ResponseEntity<JSONObject>(customerData, HttpStatus.OK);
	}

	// -------------------Retrieve Single Customer by Name------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/customer/name={name}", method = RequestMethod.GET)
	public ResponseEntity<?> getCustomerByName(@PathVariable("name") String name) {
		logger.info("Fetching Customer with name {}", name);
		Customer customer = customerService.findByName(name);
		if (customer == null) {
			logger.error("Customer with name {} not found.", name);
			return new ResponseEntity(new CustomErrorType("Customer with name " + name 
					+ " not found"), HttpStatus.NOT_FOUND);
		}

		CustomerInfo customerInfo = new CustomerInfo();
		JSONObject customerData = customerInfo.getCustomerInfo(customer);
		return new ResponseEntity<JSONObject>(customerData, HttpStatus.OK);
	}
	
	// -------------------Create a Customer-------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/customer/", method = RequestMethod.POST)
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Customer : {}", customer);
		
		System.out.println(customerService.customerExist(customer));
		
		if (customerService.customerExist(customer)) {
			logger.error("Unable to create a customer with username {}", customer.getUsername());
			return new ResponseEntity(new CustomErrorType("A customer with username " + 
			customer.getUsername() + " already exists."),HttpStatus.CONFLICT);
		}
		
		Customer currentCustomer = customerService.createCustomer(customer);
		Long currentCustomerId = currentCustomer.getCustomerId();
		JSONObject customerInfo = new JSONObject();
		customerInfo.put("customerId", currentCustomerId);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/customer/{customerId").buildAndExpand(customer.getCustomerId()).toUri());;
		return new ResponseEntity<JSONObject>(customerInfo, HttpStatus.CREATED);
	}

	// ------------------- Update a Customer ------------------------------------------------

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/customer/{customerId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCustomer(@PathVariable("customerId") long customerId, @RequestBody Customer customer) {
		logger.info("Updating customer with id {}", customerId);

		Customer currentCustomer = customerService.findById(customerId);

		if (currentCustomer == null) {
			logger.error("Unable to update. Customer with id {} not found.", customerId);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Customer with id " + customerId + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentCustomer.setName(customer.getName());
		currentCustomer.setUsername(customer.getUsername());
		currentCustomer.setAddress(customer.getAddress());
		currentCustomer.setPhone(customer.getPhone());
		currentCustomer.setEmail(customer.getEmail());
		currentCustomer.setPassword(customer.getPassword());
		currentCustomer.setRole(customer.getRole());
		currentCustomer.setEnabled(customer.getEnabled());

		customerService.updateCustomer(currentCustomer);
		return new ResponseEntity<Customer>(currentCustomer, HttpStatus.OK);
	}

	// ------------------- Delete a Customer-----------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/customer/{customerId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") long customerId) {
		logger.info("Fetching & Deleting customer with id {}", customerId);

		Customer customer = customerService.findById(customerId);
		if (customer == null) {
			logger.error("Unable to delete. User with customer {} not found.", customerId);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Customer with id " + customerId + " not found."),
					HttpStatus.NOT_FOUND);
		}
		customerService.deleteCustomerById(customerId);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Customers-----------------------------

	@RequestMapping(value = "/customer/", method = RequestMethod.DELETE)
	public ResponseEntity<Customer> deleteAllCustomers() {
		logger.info("Deleting All Customers");

		customerService.deleteAllCustomers();
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}
	
}
