package com.docker.atsea.controller;

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

import com.docker.atsea.model.Order;
import com.docker.atsea.service.OrderService;
import com.docker.atsea.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class OrderController {
	
	public static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	OrderService orderService;
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
	
	@RequestMapping(value = "/order/", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> listAllOrderss() {
		List<Order> order = orderService.findAllOrders();
		if (order.isEmpty()) {
			return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);

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

}
