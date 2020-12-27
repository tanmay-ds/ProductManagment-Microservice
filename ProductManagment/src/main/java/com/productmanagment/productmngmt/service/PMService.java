package com.productmanagment.productmngmt.service;


import org.springframework.data.domain.Page;

import com.productmanagment.productmngmt.model.Product;

public interface PMService {

	Page<Product> getAll();
	
	Product getProductById(Long pid);
	
}
