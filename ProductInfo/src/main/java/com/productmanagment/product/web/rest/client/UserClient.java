package com.productmanagment.product.web.rest.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.productmanagment.product.model.UserInfo;

@FeignClient(name = "USER-INFO")
public interface UserClient {
	@GetMapping("users/getalluserlist")
	public ResponseEntity<List<UserInfo>> getUsers(@RequestHeader(value = "Authorization") String token);
	
	@GetMapping("users/getbyemail/{email}")
	public UserInfo getByEmail(@PathVariable("email") String email);
	
}
