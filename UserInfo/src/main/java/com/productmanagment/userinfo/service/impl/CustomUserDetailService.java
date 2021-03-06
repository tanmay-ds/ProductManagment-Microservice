package com.productmanagment.userinfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.productmanagment.userinfo.entity.UserInfo;
import com.productmanagment.userinfo.repo.UserInfoRepository;
import com.productmanagment.userinfo.security.CustomUserDetail;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	UserInfoRepository userInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String username){
		UserInfo userInfo = userInfoRepository.findByEmail(username);
		if(userInfo==null) {
			throw new UsernameNotFoundException("Incorrect username or password");
		}
		return new CustomUserDetail(userInfo);
	}

}
