package com.productmanagment.product.exceptionhandler;

public class ProductAlreadyExists extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductAlreadyExists(String message) {
		super(message);
	}
}
