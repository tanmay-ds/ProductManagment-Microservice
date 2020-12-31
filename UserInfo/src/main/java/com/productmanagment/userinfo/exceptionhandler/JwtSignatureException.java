package com.productmanagment.userinfo.exceptionhandler;

public class JwtSignatureException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8893150885900839579L;

	public JwtSignatureException(String message) {
		super(message);
	}
}
