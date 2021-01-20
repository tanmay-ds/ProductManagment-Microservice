package com.productmanagment.userinfo.web.rest;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productmanagment.userinfo.constant.Constants;
import com.productmanagment.userinfo.dto.UserInfoDto;
import com.productmanagment.userinfo.entity.UserInfo;
import com.productmanagment.userinfo.model.ResponseModel;
import com.productmanagment.userinfo.service.UserService;

@Validated
@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	UserService userService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("createuser")
	public ResponseEntity<ResponseModel> createUser(@RequestBody List<@Valid UserInfoDto> usersDto) {
		return ResponseEntity.ok(new ResponseModel(new Date().toString(), HttpStatus.OK,
				Collections.singletonMap(Constants.MESSAGE_KEY, userService.createUser(usersDto))));
	}

//	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("getalluser")
	public ResponseEntity<Page<UserInfo>> getAll(Pageable pageable) {
		return ResponseEntity.ok(userService.getAll(pageable));
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("getalluserlist")
	public ResponseEntity<List<UserInfo>> getAllList() {
		return ResponseEntity.ok(userService.getAllList());
	}
//	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("getbyemail/{email}")
	public UserInfo getByEmail(@PathVariable("email") String email) {
		return userService.getByEmail(email);
	}

}
