package com.productmanagment.userinfo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.productmanagment.userinfo.dto.UserInfoDto;
import com.productmanagment.userinfo.entity.UserInfo;
import com.productmanagment.userinfo.security.jwt.AuthRequest;

public interface UserService {

	String createUser(List<@Valid UserInfoDto> usersDto);

	Page<UserInfo> getAll(Pageable pageable);

	String authenticate(AuthRequest authRequest);

	List<UserInfo> getAllList();

	UserInfo getByEmail(String email);

}
