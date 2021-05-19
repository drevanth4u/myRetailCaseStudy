package com.target.retail.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.target.retail.model.Product;

/**
 * 
 * @author Revanth
 *
 */
public interface ProductRepository extends MongoRepository<Product, String> {

	Optional<Product> getProductByproductId(String productId);
	
}
