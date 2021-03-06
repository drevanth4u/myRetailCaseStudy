
package com.target.retail.remote.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Revanth
 *
 * Feign client for remote API call
 * 
 */
@FeignClient(
	    name = "productInfo-service",
	    url = "https://redsky.target.com/v3/pdp/tcin/")
public interface ProductInfoClient {
	
	
	/**
	 * @param productId
	 * @return product information from external API
	 */
	@RequestMapping(method = RequestMethod.GET, value = "{productId}?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate")
	public ResponseEntity<String> getProductInfoById(@PathVariable("productId") String productId);
	

}