package com.productmanagment.userinfo.web.rest;

import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productmanagment.userinfo.constant.Constants;
import com.productmanagment.userinfo.model.ResponseModel;
import com.productmanagment.userinfo.security.jwt.AuthRequest;
import com.productmanagment.userinfo.service.UserService;

@RestController
@RequestMapping("auth")
public class AuthRestController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<ResponseModel> authenticate(@RequestBody AuthRequest authRequest) {
		return ResponseEntity.ok(new ResponseModel(new Date().toString(), HttpStatus.OK,
				Collections.singletonMap(Constants.TOKEN_KEY, userService.authenticate(authRequest))));
	}

}
