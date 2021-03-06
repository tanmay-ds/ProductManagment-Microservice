package com.productmanagment.product.web.rest;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productmanagment.product.constant.Constants;
import com.productmanagment.product.dto.ProductDto;
import com.productmanagment.product.entity.Product;
import com.productmanagment.product.model.ResponseModel;
import com.productmanagment.product.model.UserInfo;
import com.productmanagment.product.service.ProductService;
import com.productmanagment.product.web.rest.client.UserClient;

@Validated
@RestController
@RequestMapping("/product")
public class ProductRestController {

	@Autowired
	ProductService proService;

	@Autowired
	UserClient userClient;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("create")
	public ResponseEntity<ResponseModel> createProduct(@RequestBody List<@Valid ProductDto> productsDto) {
		return ResponseEntity.ok(new ResponseModel(new Date().toString(), HttpStatus.OK,
				Collections.singletonMap(Constants.MESSAGE_KEY, proService.create(productsDto))));
	}

	@GetMapping("getproduct/{pid}")
	public ResponseEntity<Product> getProductById(@PathVariable Long pid) {
		return ResponseEntity.ok(proService.getProductById(pid));
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("update/{pid}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long pid, @RequestBody ProductDto productDto) {
		return ResponseEntity.ok(proService.updateProd(pid, productDto));
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("getall")
	public ResponseEntity<Page<Product>> getall(Pageable pageable) {
		return ResponseEntity.ok(proService.getAll(pageable));
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("getall/{search}")
	public ResponseEntity<Page<Product>> getall(@PathVariable String search, Pageable pageable) {
		return ResponseEntity.ok(proService.getAll(search, pageable));
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("delete/{pid}")
	public ResponseEntity<ResponseModel> deleteProduct(@PathVariable Long pid) {
		return ResponseEntity.ok(new ResponseModel(new Date().toString(), HttpStatus.OK, Collections.singletonMap(
				Constants.MESSAGE_KEY, "Product with Id : " + proService.deleteProd(pid) + " is deleted")));
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("addStock")
	public ResponseEntity<ResponseModel> addStock(@RequestBody Map<Long, Long> stockList) {
		return ResponseEntity.ok(new ResponseModel(new Date().toString(), HttpStatus.OK,
				Collections.singletonMap(Constants.MESSAGE_KEY, proService.addStock(stockList))));
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("removeStock")
	public ResponseEntity<ResponseModel> removeStock(@RequestBody Map<Long, Long> stockList) {
		return ResponseEntity.ok(new ResponseModel(new Date().toString(), HttpStatus.OK,
				Collections.singletonMap(Constants.MESSAGE_KEY, proService.removeStock(stockList))));
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/findAllUser")
	public ResponseEntity<List<UserInfo>> listuser(@RequestHeader(value = "Authorization") String token) {
		return userClient.getUsers(token);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/getuserbyemail")
	public ResponseEntity<UserInfo> getUserByEmail(@RequestParam(name = "email") String email) {
		return ResponseEntity.ok(userClient.getByEmail(email));
	}

}
