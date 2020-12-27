package com.productmanagment.userinfo.template.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.productmanagment.userinfo.entity.UserInfo;
import com.productmanagment.userinfo.template.UserInfoRepositoryTemplate;
import com.productmanagment.userinfo.util.CryptoUtil;

public class UserInfoRepositoryTemplateImpl implements UserInfoRepositoryTemplate {

	@Autowired
	CryptoUtil cryptoUtil;
	
	@Autowired
	private MongoTemplate mongoTeamplate;
	
	@Override
	public UserInfo findByEncryptEmail(String email,String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		Query query = new Query().addCriteria(Criteria.where("email").is(cryptoUtil.encrypt(email, key)));
		
		return mongoTeamplate.findOne(query, UserInfo.class);
	}

}
