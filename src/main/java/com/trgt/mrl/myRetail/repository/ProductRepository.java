package com.trgt.mrl.myRetail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trgt.mrl.myRetail.entiry.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	
	/**
	 * @param productId
	 * @return
	 */
	public Product getProductByproductId(String productId);
}
