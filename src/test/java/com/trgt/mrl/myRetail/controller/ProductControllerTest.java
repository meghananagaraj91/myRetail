package com.trgt.mrl.myRetail.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.trgt.mrl.myRetail.entiry.Product;
import com.trgt.mrl.myRetail.service.ProductService;

/**
 * @author Rohit 
 * Created On : 10/02/2017
 */
@WebMvcTest(value=ProductController.class)
@RunWith(SpringRunner.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ProductService productServiceMock;
	
	/**
	 * Setup for Mockito before any test run.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * @throws Exception
	 * 
	 * Positive test.
	 * 
	 */
	@Test
	public void getProductInfoTest() throws Exception  {
		// service data from mock
		Map<String, String> currency = new HashMap<>();
		currency.put("value", "50");
		currency.put("currency_code", "USD");
		Product mockProduct = new Product("13860428", "The Big Lebowski (Blu-ray)", currency);
		
		Mockito.when(
				productServiceMock.getProductById(Mockito.anyString())).thenReturn(mockProduct);
		
		String url = "/products/13860428";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE);
		
		//Actual Result
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		//Expected Result
		String expectedProductJson = "{\"productId\": \"13860428\",\"title\": \"The Big Lebowski (Blu-ray)\",\"current_price\": {\"value\": \"50\",\"currency_code\": \"USD\"}}";
		
		JSONAssert.assertEquals(expectedProductJson, result.getResponse().getContentAsString(), false);
	}
}
