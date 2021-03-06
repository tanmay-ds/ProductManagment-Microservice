package com.productmanagment.productmngmt.web.rest.client.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productmanagment.productmngmt.exceptionhandler.ClientErrorException;
import com.productmanagment.productmngmt.model.Product;
import com.productmanagment.productmngmt.model.ResponseModel;
import com.productmanagment.productmngmt.web.rest.client.ProductClient;
import com.productmanagment.productmngmt.web.rest.model.RestResponsePage;

@Service
public class ProductClientImpl implements ProductClient {

	private static final String PRODUCT_URL = "http://product-info/product/";

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Page<Product> getAllProduct() {
		ResponseEntity<RestResponsePage<Product>> responseEntity = restTemplate.exchange(PRODUCT_URL + "getall",
				HttpMethod.GET, null, new ParameterizedTypeReference<RestResponsePage<Product>>() {
				});

		return responseEntity.getBody();
	}

	@Override
	public Product getProductById(Long pid) {
		ResponseEntity<Product> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(PRODUCT_URL + "getproduct/" + pid,
					HttpMethod.GET, null, Product.class);
		} catch (HttpServerErrorException.InternalServerError e) {
			ObjectMapper mapper = new ObjectMapper();
			ResponseModel responseModel = null;
			try {
				responseModel = mapper.readValue(e.getResponseBodyAsString(), ResponseModel.class);
			} catch (JsonProcessingException e1) {
				e1.printStackTrace();
			}
			@SuppressWarnings("unchecked")
			Map<String,String> dataObj = mapper.convertValue(responseModel.getData(), Map.class);
			throw new ClientErrorException(dataObj.get("message"));
		}
		return responseEntity.getBody();
	}

}
