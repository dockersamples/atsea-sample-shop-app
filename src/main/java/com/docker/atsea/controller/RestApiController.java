package com.docker.atsea.controller;

import java.util.List;

import org.json.simple.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.docker.atsea.model.Customer;
import com.docker.atsea.model.Order;
import com.docker.atsea.model.Product;
import com.docker.atsea.service.CustomerService;
import com.docker.atsea.service.OrderService;
import com.docker.atsea.service.ProductService;
import com.docker.atsea.util.CustomErrorType;
import com.docker.atsea.util.CustomerInfo;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/api")
public class RestApiController {
	
	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	ProductService productService; //Service which will do all data retrieval/manipulation work
	@Autowired
	OrderService orderService;
	@Autowired
	CustomerService customerService;
	@Autowired
	JdbcTemplate jdbcTemplate;

	
	// -------------------------------------------------------------------
	//                   Product methods
	//--------------------------------------------------------------------


	// -------------------Retrieve All Products---------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/product/", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> listAllProducts() {
		List<Product> products = productService.findAllProducts();
		if (products.isEmpty()) {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	// -------------------Retrieve Single Product By Id------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
	public ResponseEntity<?> getProduct(@PathVariable("productId") long productId) {
		logger.info("Fetching Product with id {}", productId);
		Product product = productService.findById(productId);
		if (product == null) {
			logger.error("Product with id {} not found.", productId);
			return new ResponseEntity(new CustomErrorType("Product with id " + productId 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	// -------------------------------------------------------------------
	//                   Order methods
	//--------------------------------------------------------------------


	// -------------------Create an Order-------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/order/", method = RequestMethod.POST)
	public ResponseEntity<?> createOrder(@RequestBody Order order, UriComponentsBuilder ucBuilder) {
		logger.info("Creating order : {}", order);

		if (orderService.orderExists(order)) {
			logger.error("Unable to create. An order with id {} already exist", order.getOrderId());
			return new ResponseEntity(new CustomErrorType("Unable to create. An order with id " + 
			order.getOrderId() + " already exists."),HttpStatus.CONFLICT);
		}
				
		Order currentOrder = orderService.createOrder(order);
		Long currentOrderId = currentOrder.getOrderId();
		JSONObject orderInfo = new JSONObject();
		orderInfo.put("orderId", currentOrderId);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/order/").buildAndExpand(order.getOrderId()).toUri());
		return new ResponseEntity<JSONObject>(orderInfo, HttpStatus.CREATED);
	}


	// ------------------- Delete an Order-----------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/order/{orderId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteItemById(@PathVariable("orderId") long orderId) {
		logger.info("Fetching & Deleting Item with id {}", orderId);

		Order order = orderService.findById(orderId);
		if (order == null) {
			logger.error("Unable to delete item with orderid {} not found.", orderId);
			return new ResponseEntity(new CustomErrorType("Unable to delete order. Order with id " + orderId + " not found."),
					HttpStatus.NOT_FOUND);
		}
		orderService.deleteOrderById(orderId);
		return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
	}

	
	// ------------------- Get All Orders-----------------------------
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/order/", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> listAllOrderss() {
		List<Order> order = orderService.findAllOrders();
		if (order.isEmpty()) {
			return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Order>>(order, HttpStatus.OK);
	}
	
	// -------------------Retrieve Single Order By Id------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
	public ResponseEntity<?> getOrder(@PathVariable("orderId") long orderId) {
		logger.info("Fetching Order with id {}", orderId);
		Order order = orderService.findById(orderId);
		if (order == null) {
			logger.error("Order with id {} not found.", orderId);
			return new ResponseEntity(new CustomErrorType("Order with id " + orderId 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	// -------------------Retrieve Single Order By Username------------------------------------------

//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@RequestMapping(value = "/order/username={userName}", method = RequestMethod.GET)
//	public ResponseEntity<?> getOrder(@PathVariable("userName") String userName) {
//		logger.info("Fetching Order for username {}", userName);
//		Order order = orderService.
//		if (order == null) {
//			logger.error("Order with id {} not found.", orderId);
//			return new ResponseEntity(new CustomErrorType("Order with id " + orderId 
//					+ " not found"), HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<Order>(order, HttpStatus.OK);
//	}

	// ---------------------Update an order-------------------------------
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/order/{orderId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateOrder(@PathVariable("orderId") long orderId, @RequestBody Order order) {
		logger.info("Updating order with id {}", orderId);

		Order currentOrder = orderService.findById(orderId);

		if (currentOrder == null) {
			logger.error("Unable to update. Order with id {} not found.", orderId);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Order with id " + orderId + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentOrder.setCustomerId(order.getCustomerId());
		currentOrder.setOrderDate(order.getOrderDate());
		currentOrder.setProductsOrdered(order.getProductsOrdered());
		orderService.updateOrder(currentOrder);
		
		JSONObject orderInfo = new JSONObject();
		orderInfo.put("orderId", currentOrder.getOrderId());
		orderInfo.put("customerId", currentOrder.getCustomerId());
		orderInfo.put("orderDate", currentOrder.getOrderDate());
		orderInfo.put("productsOrdered", currentOrder.getProductsOrdered());
		return new ResponseEntity<JSONObject>(orderInfo, HttpStatus.OK);
	}
	
	// -------------------------------------------------------------------
	//                   Customer methods
	//--------------------------------------------------------------------
	
	// -------------------Retrieve All Customers---------------------------------------------

	@RequestMapping(value = "/customer/", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> listAllUsers() {
		List<Customer> customer = customerService.findAllCustomers();
		if (customer.isEmpty()) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);
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
