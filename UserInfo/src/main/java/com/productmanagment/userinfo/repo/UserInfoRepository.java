package com.productmanagment.userinfo.repo;

import org.springframework.stereotype.Repository;

import com.productmanagment.userinfo.entity.UserInfo;
import com.productmanagment.userinfo.template.UserInfoRepositoryTemplate;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface UserInfoRepository extends MongoRepository<UserInfo, String>,UserInfoRepositoryTemplate{
	
	UserInfo findByEmail(String email);

	UserInfo findByFirstName(String name);

}
