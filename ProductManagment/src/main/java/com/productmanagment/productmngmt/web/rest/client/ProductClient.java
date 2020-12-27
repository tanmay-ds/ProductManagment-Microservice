package com.productmanagment.productmngmt.web.rest.client;


import org.springframework.data.domain.Page;

import com.productmanagment.productmngmt.model.Product;

public interface ProductClient {

	Page<Product> getAllProduct();

	Product getProductById(Long pid);

}
