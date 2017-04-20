package com.docker.atsea.controller;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/purchase/")
public class PurchaseController {
	
	public static final Logger logger = LoggerFactory.getLogger(PurchaseController.class);
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="", method = RequestMethod.GET)
    public ResponseEntity<?> purchase() {
    	logger.info("Performing purchase");
    	
    	JSONObject message = new JSONObject();    	
    	message.put("message", "Thank you for shopping @Sea! We're sending a confirmation email shortly and getting your order ready!");
		
    	return new ResponseEntity<JSONObject>(message, HttpStatus.OK);
    }

}
