package com.target.retail.error;

/**
 * 
 * @author Revanth
 *
 */
public class ErrorResponse {

	private int errorCode;
	private String errorDescription;

	public ErrorResponse(int errorCoode, String errorDescription) {
		super();
		this.errorCode = errorCoode;
		this.errorDescription = errorDescription;
	}

	public int getErrorCoode() {
		return errorCode;
	}

	public void setErrorCoode(int errorCoode) {
		this.errorCode = errorCoode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

}
