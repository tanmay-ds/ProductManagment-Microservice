package com.productmanagment.productmngmt.exceptionhandler;



public class ClientErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3054265676486160891L;
	
	public ClientErrorException(String message) {
		super(message);
	}
}
