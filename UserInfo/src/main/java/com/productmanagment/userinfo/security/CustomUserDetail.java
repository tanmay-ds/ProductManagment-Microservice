package com.productmanagment.userinfo.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.productmanagment.userinfo.entity.UserInfo;

public class CustomUserDetail implements UserDetails {

	private static final long serialVersionUID = -4859982843868046672L;

	private UserInfo userInfo;
	
	private String username;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;
	
	public CustomUserDetail(UserInfo userInfo) {
		this.userInfo = userInfo;
		this.username = userInfo.getEmail();
		this.email = userInfo.getEmail();
		this.authorities = userInfo.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
	}
	
	public CustomUserDetail(String username, String email, Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.email = email;
		this.authorities = authorities;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return userInfo.getPassword();
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
