package com.target.retail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.target.retail.error.ErrorResponse;

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
	public final ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex,
			WebRequest request) {

		ErrorResponse error = new ErrorResponse(4001, ex.msg);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	/**
	 * To handle ProductMisMatchException
	 * 
	 * @param ex
	 * @param request
	 * @return ErrorResponse
	 */
	@ExceptionHandler(ProductMisMatchException.class)
	public final ResponseEntity<ErrorResponse> handleProductMisMatchException(ProductMisMatchException ex,
			WebRequest request) {

		ErrorResponse error = new ErrorResponse(4002, ex.msg);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * To handle ProductInfoMissingException
	 * 
	 * @param ex
	 * @param request
	 * @return ErrorResponse
	 */
	@ExceptionHandler(ProductInfoMissingException.class)
	public final ResponseEntity<ErrorResponse> handleProductInfoMissingException(ProductInfoMissingException ex,
			WebRequest request) {

		ErrorResponse error = new ErrorResponse(4003, ex.msg);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	/**
	 * To handle any RuntimeException
	 * 
	 * @param ex
	 * @param request
	 * @return ErrorResponse
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {

		ErrorResponse error = new ErrorResponse(4004, ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
