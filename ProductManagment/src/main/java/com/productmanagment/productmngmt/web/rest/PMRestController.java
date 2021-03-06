package com.productmanagment.productmngmt.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productmanagment.productmngmt.model.Product;
import com.productmanagment.productmngmt.service.PMService;
import com.productmanagment.productmngmt.service.TestService;

@RestController
@RequestMapping("/api")
public class PMRestController {

	@Autowired
	PMService pmService;
	
	@Autowired
	@Qualifier("first")
	TestService testService1;
	
	@Autowired
	@Qualifier("second")
	TestService testService2;

	@GetMapping("products/")
	public ResponseEntity<Page<Product>> getAll() {
		return ResponseEntity.ok(pmService.getAll());
	}

	@GetMapping("products/{pid}")
	public ResponseEntity<Product> getProductById(@PathVariable Long pid) {
		return ResponseEntity.ok(pmService.getProductById(pid));

	}
	
	@GetMapping("test1")
	public String test() {
		return testService1.fun1()+"  "+testService2.fun1();
	}

}
