package com.productmanagment.product.exceptionhandler;

public class NegativeArgumentException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NegativeArgumentException(String message) {
		super(message);
	}

}
