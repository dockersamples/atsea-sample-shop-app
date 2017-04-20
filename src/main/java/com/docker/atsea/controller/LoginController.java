package com.docker.atsea.controller;

import java.util.Date;
import java.util.Objects;

import javax.servlet.ServletException;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.docker.atsea.model.Customer;
import com.docker.atsea.service.CustomerService;
import com.docker.atsea.util.CustomErrorType;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(path = "/login/")
public class LoginController {
	
	public static final Logger logger = LoggerFactory.getLogger(LoginController.class);
		
	@Autowired
	CustomerService customerService;
		
	private static class UserLogin {
        public String username;
        public String password;
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody final UserLogin login) 
		throws ServletException {
		
		JSONObject reponseToken = new JSONObject();
		Customer customer = customerService.findByUserName(login.username);
		if (customer == null) {
			logger.error("Customer with username {} not found.", login.username);
			return new ResponseEntity(new CustomErrorType("Customer with username " + login.username 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		
		if (Objects.deepEquals(login.password, customer.getPassword()) && Objects.equals(login.username, customer.getUsername())) {
			String token = Jwts.builder().setSubject(login.username)
                .claim("roles", customer.getUsername())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"secretkey")
                .compact();
			
			reponseToken.put("token", token);
			
			return new ResponseEntity<JSONObject>(reponseToken, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(new CustomErrorType("Customer name or password not found."), HttpStatus.UNAUTHORIZED);
		
		
	}
}
