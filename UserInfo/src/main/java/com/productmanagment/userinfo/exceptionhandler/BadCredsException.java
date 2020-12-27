package com.productmanagment.userinfo.exceptionhandler;

public class BadCredsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7073882161984405946L;

	public BadCredsException(String message) {
		super(message);
	}
	
	

}
