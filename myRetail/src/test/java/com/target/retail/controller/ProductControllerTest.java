package com.target.retail.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

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

	@Value("${server.port}")
	private int serverPort;
	
	@Autowired
	MockMvc mockMvc;

	@MockBean
	ProductService productServiceMock;

	RestTemplate restTemplate;

	// Timeout value in milliseconds
	int timeout = 10_000;

	@Before
	public void setUp() {
		restTemplate = new RestTemplate(getClientHttpRequestFactory());
	}

	private HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();

		clientHttpRequestFactory.setHttpClient(httpClient());

		return clientHttpRequestFactory;
	}

	private HttpClient httpClient() {
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("user", "password"));

		HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
		return client;
	}


	/**
	 * To test getProductInfo end point
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetProductIdEndPoint() throws Exception {
		final String baseUrl = "http://localhost:" + serverPort + "/products/13264003";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
	}

	/**
	 * To test getProductInfo end point
	 * 
	 * @throws Exception
	 */
	@Test
	public void getProductInfoTest() throws Exception {
		// service data from mock
		Map<String, String> currency = new HashMap<>();
		currency.put("value", "40");
		currency.put("currency_code", "USD");
		Product mockProduct = new Product("13264003", "Jif Natural Creamy Peanut Butter - 40oz", currency);

		Mockito.when(productServiceMock.getProductById(Mockito.anyString())).thenReturn(mockProduct);

		final String baseUrl = "http://localhost:" + serverPort + "/products/13264003";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Expected Result
		String expectedProductJson = "{\"productId\":\"13264003\",\"title\":\"Jif Natural Creamy Peanut Butter - 40oz\",\"current_price\":{\"value\":\"40\",\"currency_code\":\"USD\"}}";
		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());

		Assert.assertEquals(expectedProductJson, result.getBody());
	}

	/**
	 * @throws Exception To verify ProductNotFoundException
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
