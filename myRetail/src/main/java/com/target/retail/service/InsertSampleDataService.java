package com.target.retail.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.retail.model.Product;
import com.target.retail.repository.ProductRepository;

/**
 * 
 * @author Revanth
 *
 */
@Service
public class InsertSampleDataService {

	@Autowired
	private ProductRepository productRepository;
	

	/**
	 * Default constructor
	 */
	public InsertSampleDataService() {
	}

	/**
	 * Populate data into DB after application initialized
	 */
	@PostConstruct
	public void init() {
		addProduct();
	}

	/**
	 * Add product info
	 */
	private void addProduct() {
		if (productRepository != null) {

			Map<String, String> product1_price = new HashMap<>();
			product1_price.put("value", "13.49");
			product1_price.put("currency_code", "USD");
			Product product1 = new Product("13860428", "", product1_price);

			Map<String, String> product2_price = new HashMap<>();
			product2_price.put("value", "100.50");
			product2_price.put("currency_code", "USD");
			Product product2 = new Product("54456119", "", product2_price);

			Map<String, String> product3_price = new HashMap<>();
			product3_price.put("value", "40");
			product3_price.put("currency_code", "USD");
			Product product3 = new Product("13264003", "", product3_price);

			Map<String, String> product4_price = new HashMap<>();
			product4_price.put("value", "105.50");
			product4_price.put("currency_code", "USD");
			Product product4 = new Product("12954218", "",
					product4_price);

			// delete previous data
			this.productRepository.deleteAll();

			// Add product List in database.
			List<Product> products = Arrays.asList(product1, product2, product3, product4);
			this.productRepository.saveAll(products);
		}
	}
	
}
