package com.target.retail.exception;

/**
 * 
 * @author Revanth
 *
 */
public class ProductInfoMissingException extends RuntimeException {
	
	/**
	 * default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	String msg;
	
	public ProductInfoMissingException(String msg) {
	  this.msg = msg;
	}



}
