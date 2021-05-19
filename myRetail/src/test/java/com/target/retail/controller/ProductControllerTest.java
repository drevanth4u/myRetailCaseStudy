package com.target.retail.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.target.retail.exception.ProductNotFoundException;
import com.target.retail.model.Product;
import com.target.retail.service.ProductService;

/**
 * @author Revanth
 *
 */
@WebMvcTest(value = ProductController.class)
@RunWith(SpringRunner.class)
public class ProductControllerTest {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ProductService productServiceMock;
	
	@org.junit.Before
	public void setUp() {
	}
	
	/**
	 * To test getProductInfo end point
	 * @throws Exception 
	 */
	@Test
	public void getProductInfoTest() throws Exception{
		// service data from mock
		Map<String, String> currency = new HashMap<>();
		currency.put("value", "40");
		currency.put("currency_code", "USD");
		Product mockProduct = new Product("13264003", "Jif Natural Creamy Peanut Butter - 40oz", currency);

		Mockito.when(productServiceMock.getProductById(Mockito.anyString())).thenReturn(mockProduct);

		String url = "/products/13264003";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE);

		// Actual Result
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		// Expected Result
		String expectedProductJson = "{\"productId\": \"13264003\",\"current_price\": {\"value\": \"40\",\"currency_code\": \"USD\"},\"title\": \"Jif Natural Creamy Peanut Butter - 40oz\"}";

		JSONAssert.assertEquals(expectedProductJson, result.getResponse().getContentAsString(), true);
	}
	
	/**
	 * @throws Exception 
	 * To verify ProductNotFoundException
	 */
	@Test
	public void getProductInfoTestProductNotFound() throws Exception {
		Mockito.when(productServiceMock.getProductById(Mockito.anyString())).thenThrow(new NullPointerException());

		try {
			String url = "/products/123456";
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE);
			mockMvc.perform(requestBuilder).andReturn();
		} catch (ProductNotFoundException e) {
			logger.debug("Product not found Exception test sucess.");
		}
	}
	
	
}
