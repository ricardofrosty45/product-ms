package com.products.ms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.products.ms.entity.ProductEntity;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String> {
}
