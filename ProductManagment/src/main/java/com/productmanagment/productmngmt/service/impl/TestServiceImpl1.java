package com.productmanagment.productmngmt.service.impl;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.productmanagment.productmngmt.service.TestService;

@Service
@Qualifier("first")
public class TestServiceImpl1 implements TestService{

	@Override
	public String fun1() {
		return "fin1";
	}

}
