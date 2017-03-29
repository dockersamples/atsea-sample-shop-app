package com.docker.mobystore.mock.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.docker.mobystore.controller.RestApiController;
import com.docker.mobystore.mock.TestUtils;
import com.docker.mobystore.model.Customer;
import com.docker.mobystore.service.CustomerService;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringRunner.class)
@WebMvcTest(RestApiController.class)
public class CustomerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	CustomerService customerService;
	
	private final String URL = "/customer/";
	
	@Test
	public void testCreateACustomer() throws Exception {
		Customer mockCustomer = new Customer(1l,
				                             "Veruca Salt",
				                             "Veruca",
				                             "27 Colmore Row, Birmingham, England B3 2EW",
				                             "+44 20 7123 4567",
				                             "verucadarling@gmail.com",
				                             "nowdaddy",
				                             true,
				                             "USER");
		Mockito.doReturn(mockCustomer).when(customerService).saveCustomer(Mockito.any(Customer.class));
		
		MvcResult result = (MvcResult) mockMvc.perform((RequestBuilder) ((ResultActions) MockMvcRequestBuilders.post(URL)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(TestUtils.objectToJson(mockCustomer)))
				.andReturn());
		
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.CREATED.value(), status);
		
		verify(customerService).saveCustomer(any(Customer.class));
		
		Customer resultCustomer = TestUtils.jsonToObject(result.getResponse()
				.getContentAsString(), Customer.class);
		assertNotNull(resultCustomer);
		assertEquals(1l, resultCustomer.getCustomerId().longValue());
	}
	
	

}
