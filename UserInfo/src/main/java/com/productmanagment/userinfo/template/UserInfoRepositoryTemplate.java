package com.productmanagment.userinfo.template;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.productmanagment.userinfo.entity.UserInfo;


public interface UserInfoRepositoryTemplate {
	
	UserInfo findByEncryptEmail(String email,String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException;

}
