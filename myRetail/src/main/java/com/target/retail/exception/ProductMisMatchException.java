package com.target.retail.exception;

/**
 * @author Revanth 
 */
public class ProductMisMatchException extends RuntimeException {

	/**
	 * default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	String msg;
	
	
	public ProductMisMatchException(String msg ) {
		this.msg = msg;
	}


}
