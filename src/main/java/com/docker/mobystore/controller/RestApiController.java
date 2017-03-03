package com.docker.mobystore.controller;

import java.util.List;

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

import com.docker.mobystore.model.Product;
import com.docker.mobystore.service.ProductService;
import com.docker.mobystore.model.Cart;
import com.docker.mobystore.service.CartService;
import com.docker.mobystore.model.Order;
import com.docker.mobystore.service.OrderService;

import com.docker.mobystore.model.Customer;
import com.docker.mobystore.service.CustomerService;
import com.docker.mobystore.util.CustomErrorType;


@RestController
@RequestMapping("/api")
public class RestApiController {
	
	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	ProductService productService; //Service which will do all data retrieval/manipulation work
	@Autowired
	CartService cartService;
	@Autowired
	OrderService orderService;
	@Autowired
	CustomerService customerService;

	// -------------------Retrieve All Products---------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/product/", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> listAllProducts() {
		List<Product> products = productService.findAllProducts();
		if (products.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
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
	
/*	// -------------------Retrieve Single Product By Name------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/product/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getProduct(@PathVariable("name") String name) {
		logger.info("Fetching User with name {}", name);
		Product product = productService.findByName(name);
		if (product == null) {
			logger.error("User with name {} not found.", name);
			return new ResponseEntity(new CustomErrorType("Product with name " + name 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}*/
	

	// -------------------Create an order-------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/order/", method = RequestMethod.POST)
	public ResponseEntity<?> createOrder(@RequestBody Order order, UriComponentsBuilder ucBuilder) {
		logger.info("Creating order : {}", order);

		if (orderService.orderExists(order)) {
			logger.error("Unable to create. An order with id {} already exist", order.getOrderId());
			return new ResponseEntity(new CustomErrorType("Unable to create. An order with id " + 
			order.getOrderId() + " already exists."),HttpStatus.CONFLICT);
		}
		
		orderService.saveOrder(order);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/order/{orderId}").buildAndExpand(order.getOrderId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Cart ------------------------------------------------

	@RequestMapping(value = "/cart/{cartId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCart(@PathVariable("cartId") long cartId, @RequestBody Cart cart) {
		logger.info("Updating Cart with id {}", cartId);

		Cart currentCart = cartService.findById(cartId);

		if (currentCart == null) {
			logger.error("Unable to update. Cart with id {} not found.", cartId);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Cart with id " + cartId + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentCart.setCustomer(cart.getCustomer());
		currentCart.setProductId(cart.getProductId());
		currentCart.setPrice(cart.getPrice());
		currentCart.setQuantity(cart.getQuantity());
		currentCart.setOrderDate(cart.getOrderDate());
		currentCart.setOrderNum(cart.getOrderNum());

		cartService.updateCart(currentCart);
		return new ResponseEntity<Cart>(currentCart, HttpStatus.OK);
	}

	// ------------------- Delete a cart item-----------------------------------------

	@RequestMapping(value = "/cart/{cartId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteItemById(@PathVariable("cartId") long cartId) {
		logger.info("Fetching & Deleting Item with id {}", cartId);

		Cart cart = cartService.findById(cartId);
		if (cart == null) {
			logger.error("Unable to delete item with cartid {} not found.", cartId);
			return new ResponseEntity(new CustomErrorType("Unable to item in cart with id " + cartId + " not found."),
					HttpStatus.NOT_FOUND);
		}
		cartService.deleteItemById(cartId);
		return new ResponseEntity<Cart>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Users-----------------------------

	@RequestMapping(value = "/cart/", method = RequestMethod.DELETE)
	public ResponseEntity<Cart> deleteAllItems() {
		logger.info("Deleting All Items");

		cartService.deleteAllItems();
		return new ResponseEntity<Cart>(HttpStatus.NO_CONTENT);
	}
	
	// -------------------------------------------------------------------
	//                   Customer methods
	//--------------------------------------------------------------------
	
	// -------------------Retrieve All Customer---------------------------------------------

	@RequestMapping(value = "/customer/", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> listAllUsers() {
		List<Customer> customer = customerService.findAllCustomers();
		if (customer.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);
	}

	// -------------------Retrieve Single Customer------------------------------------------

	@RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<?> getCustomer(@PathVariable("customerId") long customerId) {
		logger.info("Fetching Cistp,er with id {}", customerId);
		Customer customer = customerService.findById(customerId);
		if (customer == null) {
			logger.error("Customer with id {} not found.", customerId);
			return new ResponseEntity(new CustomErrorType("Customer with id " + customerId 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	// -------------------Create a Customer-------------------------------------------

	@RequestMapping(value = "/customer/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Customer : {}", customer);

/**		if (customerService.customerExist(customer)) {
			logger.error("Unable to create a customer with name {} already exist", customer.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A customer with name " + 
			customer.getName() + " already exists."),HttpStatus.CONFLICT);
		} */
		
		if(customer != null) {
			System.out.println(customer);
		}
		
		customerService.saveCustomer(customer);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/customer/{id}").buildAndExpand(customer.customerId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Customer ------------------------------------------------

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

		customerService.updateCustomer(currentCustomer);
		return new ResponseEntity<Customer>(currentCustomer, HttpStatus.OK);
	}

	// ------------------- Delete a Customer-----------------------------------------

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
