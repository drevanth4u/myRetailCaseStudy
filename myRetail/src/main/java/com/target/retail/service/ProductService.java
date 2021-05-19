package com.target.retail.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.retail.exception.ProductNotFoundException;
import com.target.retail.model.Product;
import com.target.retail.remote.feignClient.ProductInfoClient;
import com.target.retail.repository.ProductRepository;

/**
 * @author Revanth
 *
 */
@Service
public class ProductService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductInfoClient productInfoClient;

	@Autowired
	private ProductRepository productRepository;

	public ProductService() {

	}

	/**
	 * @param productId
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	//@HystrixCommand(fallbackMethod = "getProdctInfoFallback")
	public Product getProductById(String productId) throws JsonParseException, JsonMappingException, IOException {
		// Get product info from DB
		Optional<Product> productOptional = productRepository.getProductByproductId(productId);
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			// From external API
			product.setTitle(this.getNameForProduct(productId));
			logger.info("Title from Remote API   " + product.getTitle());
			return product;
		}
		throw new ProductNotFoundException("Product not found");
	}

	/**
	 * @param productId
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws Exception
	 * to get product title from external API
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getNameForProduct(String productId) throws JsonMappingException, JsonProcessingException {
		Map<String, Map> infoMap = getProductInfoFromProductInfoService(productId);

		Map<String, Map> productMap = infoMap.get("product");
		Map<String, Map> itemMap = productMap.get("item");
		Map<String, String> prodDescrMap = itemMap.get(("product_description"));

		return prodDescrMap.get("title");
	}

	/**
	 * Fallback method to provide sample product info if the external API is not
	 * available
	 * 
	 * @param productId
	 * @return sample product information
	 */
	public Product getProdctInfoFallback(String productId) {
		Map<String, String> price = new HashMap<>();
		price.put("value", "113.55");
		price.put("currency_code", "USD");
		Product product = new Product(productId, "Product Title Not Found", price);
		return product;
	}

	/**
	 * @param productId
	 * @return
	 * @throws JsonProcessingException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * to get remote data using Feign client
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map<String, Map> getProductInfoFromProductInfoService(String productId)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper infoMapper = new ObjectMapper();
		System.out.println(productInfoClient);
		ResponseEntity<String> response = productInfoClient.getProductInfoById(productId);
		System.out.println(response.getStatusCode().value());
		Map<String, Map> infoMap = infoMapper.readValue(response.getBody(), Map.class);

		return infoMap;
	}

	/**
	 * @param product
	 */
	public void updateProductById(Product product) {
		productRepository.save(product);
	}

}
