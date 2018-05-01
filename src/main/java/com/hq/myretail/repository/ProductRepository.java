
package com.hq.myretail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hq.myretail.entity.Product;

/**
 */
public interface ProductRepository extends MongoRepository<Product, String> {

    public Product findProductByProductId(String productId);
}
