package com.productmanagment.userinfo.dto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.productmanagment.userinfo.entity.RoleInfo;
import com.productmanagment.userinfo.entity.UserInfo;
import com.productmanagment.userinfo.util.CryptoUtil;

@Component
public class Dtos {
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	CryptoUtil cryptoUtil;

	@Value("${key}")
	private String key;

	public UserInfo userDtoTouser(UserInfoDto userInfoDto) {
		UserInfo userInfo = new UserInfo();
		userInfo.setAddress(userInfoDto.getAddress());
		userInfo.setEmail(userInfoDto.getEmail());
		userInfo.setFirstName(userInfoDto.getFirstName());
		userInfo.setLastName(userInfoDto.getLastName());
		userInfo.setPassword(userInfoDto.getPassword());
		userInfo.setPhoneNumber(userInfoDto.getPhoneNumber());
		userInfo.setRoles(userInfoDto.getRoles());
		return userInfo;
	}

	public UserInfo encrypt(UserInfo user) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {
		UserInfo encryptedUsers = new UserInfo();
		encryptedUsers.setFirstName(cryptoUtil.encrypt(user.getFirstName(), key));
		encryptedUsers.setLastName(cryptoUtil.encrypt(user.getLastName(), key));
		encryptedUsers.setPhoneNumber(cryptoUtil.encrypt(user.getPhoneNumber(), key));
		encryptedUsers.setAddress(cryptoUtil.encrypt(user.getAddress(), key));
		encryptedUsers.setEmail(cryptoUtil.encrypt(user.getEmail(), key));
		encryptedUsers.setPassword(passwordEncoder.encode(user.getPassword()));
		List<RoleInfo> roles = new ArrayList<>();
		for (RoleInfo role : user.getRoles()) {
			RoleInfo newRoles = new RoleInfo();
			newRoles.setRole(cryptoUtil.encrypt(role.getRole(), key));
			roles.add(newRoles);
		}
		encryptedUsers.setRoles(roles);
		return encryptedUsers;
	}

	public UserInfo decrypt(UserInfo user) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {
		UserInfo decryptedUsers = new UserInfo();
		decryptedUsers.setUuid(user.getUuid());
		decryptedUsers.setFirstName(cryptoUtil.decrypt(user.getFirstName(), key));
		decryptedUsers.setLastName(cryptoUtil.decrypt(user.getLastName(), key));
		decryptedUsers.setPhoneNumber(cryptoUtil.decrypt(user.getPhoneNumber(), key));
		decryptedUsers.setAddress(cryptoUtil.decrypt(user.getAddress(), key));
		decryptedUsers.setEmail(cryptoUtil.decrypt(user.getEmail(), key));
		decryptedUsers.setPassword(user.getPassword());
		List<RoleInfo> roles = new ArrayList<>();
		for (RoleInfo role : user.getRoles()) {
			RoleInfo newRoles = new RoleInfo();
			newRoles.setRole(cryptoUtil.decrypt(role.getRole(), key));
			roles.add(newRoles);
		}
		decryptedUsers.setRoles(roles);
		return decryptedUsers;
	}

}
