package com.productmanagment.userinfo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.productmanagment.userinfo.constant.Constants;
import com.productmanagment.userinfo.dto.Dtos;
import com.productmanagment.userinfo.dto.UserInfoDto;
import com.productmanagment.userinfo.entity.UserInfo;
import com.productmanagment.userinfo.exceptionhandler.BadCredsException;
import com.productmanagment.userinfo.repo.UserInfoRepository;
import com.productmanagment.userinfo.security.jwt.AuthRequest;
import com.productmanagment.userinfo.security.jwt.JwtTokenProvider;
import com.productmanagment.userinfo.service.SequenceGenrationService;
import com.productmanagment.userinfo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	SequenceGenrationService sequenceGenrationService;

	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailService userDetailsService;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	Dtos dtos;

	@Override
	public String createUser(List<@Valid UserInfoDto> usersDto) {
		List<UserInfo> userInfoList = new ArrayList<>();
		for (UserInfoDto user : usersDto) {
			UserInfo userInfo = dtos.userDtoTouser(user);
			userInfo.setUuid(sequenceGenrationService.generateUsersSequence(UserInfo.SEQUENCE_NAME));
			userInfoList.add(userInfo);
		}
		userInfoRepository.saveAll(userInfoList);
		return Constants.USER_ADDED;
	}

	@Override
	public Page<UserInfo> getAll(Pageable pageable) {
		return userInfoRepository.findAll(pageable);
	}

	@Override
	public String authenticate(AuthRequest authRequest) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredsException("Incorrect username or password");
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		return "Bearer " + jwtTokenProvider.genrateToken(userDetails);
	}

}
