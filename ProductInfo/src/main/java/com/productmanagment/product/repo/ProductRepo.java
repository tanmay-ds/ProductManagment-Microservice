package com.productmanagment.product.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.productmanagment.product.entity.Product;
import com.productmanagment.product.template.ProductRepositoryTemplate;


public interface ProductRepo extends MongoRepository<Product,Long>, ProductRepositoryTemplate {

	Optional<Product> findByName(String name);
	
	List<Product> findProductByNameIgnoreCase(String productName);

	Page<Product> findByNameRegex(String name,Pageable pageable);

	Optional<Product> findByProdId(Long pid);
	
}
