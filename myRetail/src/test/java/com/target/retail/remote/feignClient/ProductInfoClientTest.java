package com.target.retail.remote.feignClient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/**
 * 
 * @author Revanth
 *
 */
public class ProductInfoClientTest implements ProductInfoClient {

	@Override
	public ResponseEntity<String> getProductInfoById(String productId) {
			String productInfo = "{\"product\": {\"item\": {\"product_description\": {\"title\": \"The Big Lebowski (Blu-ray)\"}}}}";

			return new ResponseEntity<String>(productInfo, HttpStatus.OK);
		}
	}
	
