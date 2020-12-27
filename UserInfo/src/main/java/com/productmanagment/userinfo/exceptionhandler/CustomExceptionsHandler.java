package com.productmanagment.userinfo.exceptionhandler;

import java.util.Collections;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.productmanagment.userinfo.model.ResponseModel;


@RestControllerAdvice
public class CustomExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BadCredsException.class)
	public final ResponseEntity<ResponseModel> badCred(BadCredsException e, WebRequest request) {
		return internalServerError(e.getLocalizedMessage());
	}

	private ResponseEntity<ResponseModel> internalServerError(String message) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseModel(new Date().toString(),
				HttpStatus.INTERNAL_SERVER_ERROR, Collections.singletonMap("message", message)));
	}
}
