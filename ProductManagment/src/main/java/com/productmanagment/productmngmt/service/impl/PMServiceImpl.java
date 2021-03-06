package com.productmanagment.productmngmt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.productmanagment.productmngmt.model.Product;
import com.productmanagment.productmngmt.service.PMService;
import com.productmanagment.productmngmt.web.rest.client.ProductClient;

@Service
public class PMServiceImpl implements PMService{

	@Autowired
	ProductClient productClient;
	
	@Override
	public Page<Product> getAll() {
		return productClient.getAllProduct();
	}

	@Override
	public Product getProductById(Long pid) {
		return productClient.getProductById(pid);
	}

}
