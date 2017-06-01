package com.docker.atsea.util;

import org.json.simple.JSONObject;

import com.docker.atsea.model.Customer;

public class CustomerInfo {
	
	@SuppressWarnings("unchecked")
	public JSONObject getCustomerInfo(Customer customer) {
		JSONObject customerInfo = new JSONObject();
		customerInfo.put("customerIf", customer.getCustomerId());
		customerInfo.put("name", customer.getName());
		customerInfo.put("username", customer.getUsername());
		customerInfo.put("email", customer.getEmail());
		customerInfo.put("phone", customer.getPhone());
		customerInfo.put("address", customer.getAddress());
		
		return customerInfo;
	}

}
