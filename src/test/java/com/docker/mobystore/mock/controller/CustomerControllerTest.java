package com.docker.mobystore.mock.controller;

import io.restassured.RestAssured;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.docker.mobyartshop.MobyArtShopApp;
import com.docker.mobyartshop.controller.RestApiController;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,
		classes = {MobyArtShopApp.class,
		RestApiController.class})
public class CustomerControllerTest {

    @Autowired
    private WebApplicationContext wac;
    
    @Value("${local.server.port}")   
    int port;
    
    private MockMvc mockMvc;
    
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
        		.build();
        
        RestAssured.port = port;
    }
    
    @Test
    public void GetCustomers() throws Exception {
        mockMvc.perform(get("/api/customer/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                //.andExpect(jsonPath("$.username", is("thedoctor")))
        ;
    }
}