package com.productmanagment.product.exceptionhandler;

import java.util.Collections;
import java.util.Date;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.productmanagment.product.constant.Constants;
import com.productmanagment.product.model.ResponseModel;

@RestControllerAdvice
public class CustomExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoSuchProductFound.class)
	public final ResponseEntity<ResponseModel> noProductFound(NoSuchProductFound e, WebRequest request) {
		return internalServerError(e.getLocalizedMessage());
	}

	@ExceptionHandler(ProductAlreadyExists.class)
	public final ResponseEntity<ResponseModel> productHandler(ProductAlreadyExists e, WebRequest request) {
		return internalServerError(e.getLocalizedMessage());
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public final ResponseEntity<ResponseModel> duplicateProductHandler(DuplicateKeyException e, WebRequest request) {
		return internalServerError(Constants.PRODUCT_ALREADY_EXISTS);
	}

	@ExceptionHandler(NegativeArgumentException.class)
	public final ResponseEntity<ResponseModel> negativeArgument(NegativeArgumentException e, WebRequest request) {
		return internalServerError(e.getLocalizedMessage());
	}
	
	@ExceptionHandler(JwtSignatureException.class)
	public final ResponseEntity<ResponseModel> jwtSignatureException(JwtSignatureException e, WebRequest request) {
		return internalServerError(e.getLocalizedMessage());
	}

	private ResponseEntity<ResponseModel> internalServerError(String message) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseModel(new Date().toString(),
				HttpStatus.INTERNAL_SERVER_ERROR, Collections.singletonMap("message", message)));
	}
}
