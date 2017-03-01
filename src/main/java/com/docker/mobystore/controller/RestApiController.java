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
	CartService cartService;
	OrderService orderService;
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
		headers.setLocation(ucBuilder.path("/api/cart/{cartId}").buildAndExpand(order.getOrderId()).toUri());
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
}
