package com.trgt.mrl.myRetail.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.trgt.mrl.myRetail.entiry.Product;
import com.trgt.mrl.myRetail.remoteApiComm.feignClient.ProductInfoClient;
import com.trgt.mrl.myRetail.remoteApiComm.feignClient.ProductInfoClientMock;
import com.trgt.mrl.myRetail.repository.ProductRepository;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

	@InjectMocks
	ProductService productService;

	@Mock 
	ProductRepository productRepositoryMock;

	@Mock
	private ProductInfoClient productInfoClient;

	/**
	 * Setup for Mockito before any test run.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * 
	 *             Positive test.
	 * 
	 */
	@Test
	public void getProductByIdTest() throws Exception {
		// Repository data from mock
		Map<String, String> currency = new HashMap<>();
		currency.put("value", "50");
		currency.put("currency_code", "USD");
		Product mockProduct = new Product("13860428", "", currency);
		System.out.println(productRepositoryMock);
		Mockito.when(productRepositoryMock.getProductByproductId(Mockito.anyString())).thenReturn(mockProduct);

		Mockito.when(productInfoClient.getProductInfoById(Mockito.anyString()))
				.thenReturn(new ProductInfoClientMock().getProductInfoById("13860428"));

		// Actual Result
		Product actualProduct = productService.getProductById("13860428");

		// Expected Result
		Map<String, String> currency1 = new HashMap<>();
		currency.put("value", "50");
		currency.put("currency_code", "USD");
		Product expectedProduct = new Product("13860428", "The Big Lebowski (Blu-ray)", currency1);

		assertEquals(expectedProduct.getProductId(), actualProduct.getProductId());
	}

	/**
	 * @throws Exception
	 * 
	 *  Check for null pointer exception when wrong product id passed to Remote service.
	 */
	@Test(expected = NullPointerException.class)
	public void getProductInfoTest_wrongProductId() throws Exception {

		Map<String, String> currency = new HashMap<>();
		currency.put("value", "50");
		currency.put("currency_code", "USD");
		Product mockProduct = new Product("13860428", "", currency);
		Mockito.when(productRepositoryMock.getProductByproductId(Mockito.anyString())).thenReturn(mockProduct);

		// Mockito.when(productrepositoryMock.getProductByproductId(Mockito.anyString())).thenThrow(new
		// Exception());
		productService.getProductById("12345678");
	}
}
