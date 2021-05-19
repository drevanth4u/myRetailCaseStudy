package com.target.retail.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.target.retail.exception.ProductNotFoundException;
import com.target.retail.model.Product;
import com.target.retail.remote.feignClient.ProductInfoClient;
import com.target.retail.remote.feignClient.ProductInfoClientTest;
import com.target.retail.repository.ProductRepository;

/**
 * 
 * @author Revanth
 *
 */
@RunWith(SpringRunner.class)
public class ProductServiecTest {

	@InjectMocks
	ProductService productService;

	@Mock
	ProductRepository productrepositoryMock;

	@Mock
	private ProductInfoClient productInfoClient;

	/**
	 * Setup for Mockito before any test run.
	 */
	@Before
	public void setUp() {
	}

	/**
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void getProductByIdTest() throws Exception {
		// Repository data from mock
		Map<String, String> currency = new HashMap<>();
		currency.put("value", "50");
		currency.put("currency_code", "USD");
		Product mockProduct = new Product("13860428", "", currency);
		Optional<Product> mockOptional = Optional.of(mockProduct);
		System.out.println(productrepositoryMock);
		Mockito.when(productrepositoryMock.findById(Mockito.anyString())).thenReturn(mockOptional);

		Mockito.when(productInfoClient.getProductInfoById(Mockito.anyString()))
				.thenReturn(new ProductInfoClientTest().getProductInfoById("13860428"));

		// Actual Result
		Product actualProduct = productService.getProductById("13860428");

		// Expected Result
		Map<String, String> currency1 = new HashMap<>();
		currency1.put("value", "50");
		currency1.put("currency_code", "USD");
		Product expectedProduct = new Product("13860428", "The Big Lebowski (Blu-ray)", currency1);

		assertEquals(expectedProduct.getProductId(), actualProduct.getProductId());
	}

	/**
	 * @throws Exception
	 * 
	 *  Check for ProductNotFoundException when wrong product id passed to Remote service.
	 */
	@Test(expected = ProductNotFoundException.class)
	public void getProductInfoTest_wrongProductId() throws Exception {

		Map<String, String> currency = new HashMap<>();
		currency.put("value", "50");
		currency.put("currency_code", "USD");
		Product mockProduct = new Product("13860428", "", currency);
		Optional<Product> mockOptional = Optional.of(mockProduct);
		Mockito.when(productrepositoryMock.findById(Mockito.anyString())).thenReturn(mockOptional);
		productService.getProductById("12345678");
	}
	
}
