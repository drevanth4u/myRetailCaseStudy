package com.target.retail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author Revanth
 *
 */
@ControllerAdvice
public class ProductExceptionHandler {

	/**
	 * To handle ProductNotFoundException
	 * 
	 * @param ex
	 * @param request
	 * @return ErrorResponse
	 */
	@ExceptionHandler(ProductNotFoundException.class)
	public final ResponseEntity<CustomResponse> handleProductNotFoundException(ProductNotFoundException ex,
			WebRequest request) {

		CustomResponse response = new CustomResponse(4001, ex.msg);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * To handle ProductMisMatchException
	 * 
	 * @param ex
	 * @param request
	 * @return ErrorResponse
	 */
	@ExceptionHandler(ProductMisMatchException.class)
	public final ResponseEntity<CustomResponse> handleProductMisMatchException(ProductMisMatchException ex,
			WebRequest request) {

		CustomResponse response = new CustomResponse(4002, ex.msg);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * To handle ProductInfoMissingException
	 * 
	 * @param ex
	 * @param request
	 * @return CustomResponse
	 */
	@ExceptionHandler(ProductInfoMissingException.class)
	public final ResponseEntity<CustomResponse> handleProductInfoMissingException(ProductInfoMissingException ex,
			WebRequest request) {

		CustomResponse response = new CustomResponse(4003, ex.msg);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * To handle any RuntimeException
	 * 
	 * @param ex
	 * @param request
	 * @return ErrorResponse
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<CustomResponse> handleException(Exception ex, WebRequest request) {

		CustomResponse response = new CustomResponse(4004, ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

}
