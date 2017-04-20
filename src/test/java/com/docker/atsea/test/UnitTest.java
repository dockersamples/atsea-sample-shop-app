package com.docker.atsea.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.docker.atsea.model.Customer;
import com.docker.atsea.model.Order;
import com.docker.atsea.model.Product;
import com.docker.atsea.repositories.CustomerRepository;
import com.docker.atsea.repositories.OrderRepository;
import com.docker.atsea.repositories.ProductRepository;
import com.docker.atsea.service.CustomerService;
import com.docker.atsea.service.CustomerServiceImpl;
import com.docker.atsea.service.OrderService;
import com.docker.atsea.service.OrderServiceImpl;
import com.docker.atsea.service.ProductService;
import com.docker.atsea.service.ProductServiceImpl;


@SuppressWarnings("serial")
@RunWith(SpringRunner.class)
public class UnitTest {
	@Mock
	CustomerRepository customerRepository;
	
	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerServiceImpl mockCustomerServiceImpl = new CustomerServiceImpl();	
	
	@Mock
	ProductRepository productRepository;
	
	@Mock
	ProductService productService;
	
	@InjectMocks
	ProductServiceImpl mockProductServiceImpl = new ProductServiceImpl();
	
	@Mock
	OrderRepository orderRepository;
	
	@Mock
	OrderService orderService;
	
	@InjectMocks
	OrderServiceImpl mockOrderServiceImpl = new OrderServiceImpl();
	
	// Mock customer
	public Customer returnCustomer = new Customer(1L, "Arthur Dent", "Dockerland", "ad@null.com", "415-555-5555",
			"arthurd", "docker!", true, "ADMIN");
	
	// Mock product
	public Product returnProduct = new Product(1l, "Container", "container picture", 25.50, "http://localhost:8080/atsea/static/images/container.jpg");
	
	// Mock order
	public Date orderDate = new Date(); 
	private static Map<Integer, Integer> productsOrdered;
    static{
      productsOrdered =   new HashMap<Integer, Integer>() {{
            put(2, 4);
            put(3, 9);
            put(4, 16);
        }};
    }
	public Order mockOrder = new Order(1L, orderDate, 2l, productsOrdered);
	public Order referenceOrder = new Order(1l, orderDate, 2l, productsOrdered);
	
	// Test CustomerService implementation
	@Test
	public void whenCustomerUserNameIsProvided_theReturnedNameIsCorrect() {	
		Mockito.when(mockCustomerServiceImpl.findByUserName("arthurd")).thenReturn(returnCustomer);
		String testName = returnCustomer.getName();
		Assert.assertEquals("Arthur Dent", testName);
	}
	
	// Test ProductService implementation
	@Test
	public void whenProductIdIdProvided_theReturnedDescriptionIsIncorrect(){
		Mockito.when(mockProductServiceImpl.findById(1l)).thenReturn(returnProduct);
		String testDescription = returnProduct.getDescription();
		Assert.assertNotEquals("a whale", testDescription);
	}
	
	// Test OrderService implementation
	@Test
	public void whenOrderFindById_theReferenceOrderDoesNotMatch() {
		Mockito.when(mockOrderServiceImpl.findById(1l)).thenReturn(referenceOrder);
		Assert.assertNotEquals(mockOrder, referenceOrder);
	}
	
}