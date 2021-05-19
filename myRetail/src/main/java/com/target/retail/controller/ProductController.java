
package com.target.retail.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.target.retail.constants.MessageConstants;
import com.target.retail.exception.CustomResponse;
import com.target.retail.exception.ProductInfoMissingException;
import com.target.retail.exception.ProductMisMatchException;
import com.target.retail.exception.ProductNotFoundException;
import com.target.retail.model.Product;
import com.target.retail.service.ProductService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Revanth
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductService productService;

	/**
	 * To get the product information
	 * 
	 * @param productId
	 * @return product info
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find Product information by Id", notes = "Provide Product Id to get Product Information", response = Product.class)
	public ResponseEntity<Product> getProductInfo(@PathVariable("id") String productId) {
		Product product = null;
		try {
			product = productService.getProductById(productId);

		} catch (ProductNotFoundException e) {
			logger.debug("Product Not Found Exception  " + e);
			throw new ProductNotFoundException(String.format(MessageConstants.MSG_001, productId));
		} catch (Exception e) {
			logger.debug("Exception occured while getting product info  " + e);
			throw new RuntimeException(String.format(MessageConstants.MSG_005, productId));
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	/**
	 * To update the product price
	 * 
	 * @param product
	 * @param productId
	 * @return response
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update Product price by Id", notes = "Provide Product Id and Product information to update Product Information", response = CustomResponse.class)
	public ResponseEntity<CustomResponse> updateProductPrice(@RequestBody Product product,
			@PathVariable("id") String productId) {

		if (StringUtils.isBlank(product.getProductId())
				|| (product.getCurrent_price() == null && product.getCurrent_price().isEmpty())) {
			throw new ProductInfoMissingException(String.format(MessageConstants.MSG_004, productId));
		}

		if (!StringUtils.equalsIgnoreCase(productId, product.getProductId())) {
			throw new ProductMisMatchException(String.format(MessageConstants.MSG_002, productId));
		}

		try {
			productService.updateProductById(product);
		} catch (ProductNotFoundException e) {
			logger.debug("Product Not Found Exception while upating product price " + e);
			throw new ProductNotFoundException(String.format(MessageConstants.MSG_001, productId));
		} catch (Exception e) {
			logger.debug("Exception occured while updating product price  " + e);
			throw new RuntimeException(String.format(MessageConstants.MSG_005, productId));
		}

		return new ResponseEntity<CustomResponse>(
				new CustomResponse(HttpStatus.OK.value(), String.format(MessageConstants.MSG_003, productId)),
				HttpStatus.OK);

	}
}
