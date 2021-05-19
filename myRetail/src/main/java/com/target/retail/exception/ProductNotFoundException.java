package com.target.retail.exception;

/**
 * @author Revanth 
 * 
 */
public class ProductNotFoundException extends RuntimeException {

	/**
	 * default serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	String msg;

	public ProductNotFoundException(String msg) {
		this.msg=msg;
		
	}

}
