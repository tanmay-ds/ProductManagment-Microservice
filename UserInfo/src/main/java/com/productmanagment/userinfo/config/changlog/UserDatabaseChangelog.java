package com.productmanagment.userinfo.config.changlog;

import java.util.Collections;

import org.springframework.transaction.annotation.Transactional;

import com.productmanagment.userinfo.entity.RoleInfo;
import com.productmanagment.userinfo.entity.UserInfo;
import com.productmanagment.userinfo.repo.UserInfoRepository;

import io.changock.migration.api.annotations.ChangeLog;
import io.changock.migration.api.annotations.ChangeSet;

@ChangeLog
public class UserDatabaseChangelog {
	@Transactional
	@ChangeSet(order = "001", id = "defaultUser", author = "system")
	public void addDefaultUser(UserInfoRepository userInfoRepository) {
		UserInfo user = new UserInfo();
		user.setUuid(1L);
		user.setFirstName("default");
		user.setLastName("user");
		user.setEmail("du@du.com");
		user.setAddress("system");
		user.setPassword("$2a$10$a9hMAms0VN.kJ5lVvKQ6NOv8ylLaKvm66l/TyyXsV.hZjGDgpFjqO");
		user.setPhoneNumber("8877665544");
		user.setRoles(Collections.singletonList(new RoleInfo("ROLE_ADMIN")));

		userInfoRepository.save(user);

//		$2a$10$a9hMAms0VN.kJ5lVvKQ6NOv8ylLaKvm66l/TyyXsV.hZjGDgpFjqO
	}
}
