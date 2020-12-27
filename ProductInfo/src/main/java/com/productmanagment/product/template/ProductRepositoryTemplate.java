package com.productmanagment.product.template;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.productmanagment.product.entity.Product;


public interface ProductRepositoryTemplate {
		
	Page<Product> findByNamePartialSearch(String regex,Pageable pageable);
	
}
