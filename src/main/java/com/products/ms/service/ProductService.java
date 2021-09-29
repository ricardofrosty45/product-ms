package com.products.ms.service;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.mongodb.MongoSocketOpenException;
import com.products.ms.dto.request.ProductRequest;
import com.products.ms.dto.response.ResponseReturn;
import com.products.ms.entity.ProductEntity;
import com.products.ms.exception.DatabaseException;
import com.products.ms.repository.ProductRepository;
import com.products.ms.util.DatabaseFilterUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Retryable(include = { IllegalArgumentException.class, ConnectException.class,
			MongoSocketOpenException.class }, maxAttempts = 5, backoff = @Backoff(delay = 2000))
	public ProductEntity createNewProduct(ProductRequest productRequest) {
		log.info("Saving Into Database: {}", new Gson().toJson(productRequest));
		return repository.save(
				ProductEntity.builder().description(productRequest.getDescription()).name(productRequest.getName())
						.id(UUID.randomUUID().toString()).price(productRequest.getPrice()).build());
	}

	@Retryable(include = { IllegalArgumentException.class, ConnectException.class,
			MongoSocketOpenException.class }, maxAttempts = 5, backoff = @Backoff(delay = 2000))
	public ProductEntity updateProductById(ProductRequest productRequest, String id) {
		log.info("Updating Into Database: {}", new Gson().toJson(productRequest));
		Optional<ProductEntity> resultDataBase = repository.findById(id);
		checkIfProductExistsIntoDatabase(resultDataBase);
		return repository.save(
				ProductEntity.builder().id(resultDataBase.get().getId()).description(productRequest.getDescription())
						.price(productRequest.getPrice()).name(productRequest.getName()).build());

	}

	@Retryable(include = { IllegalArgumentException.class, ConnectException.class,
			MongoSocketOpenException.class }, maxAttempts = 5, backoff = @Backoff(delay = 2000))
	public ProductEntity getProductById(String id) {
		log.info("Consulting Product Into Database . . .");
		Optional<ProductEntity> resultDataBase = repository.findById(id);
		checkIfProductExistsIntoDatabase(resultDataBase);
		return resultDataBase.get();

	}

	public List<ProductEntity> findAllProducts() {
		return repository.findAll();
	}

	@Retryable(include = { IllegalArgumentException.class, ConnectException.class,
			MongoSocketOpenException.class }, maxAttempts = 5, backoff = @Backoff(delay = 2000))
	public ResponseReturn deleteProductIntoDatabase(String id) {
		log.info("Consulting Product Into Database . . .");
		Optional<ProductEntity> resultDataBase = repository.findById(id);
		checkIfProductExistsIntoDatabase(resultDataBase);
		log.info("Deleting Product Into Database . . .");
		repository.delete(resultDataBase.get());

		return ResponseReturn.builder().status(200).message("Product was deleted!").build();
	}

	@Retryable(include = { IllegalArgumentException.class, ConnectException.class,
			MongoSocketOpenException.class }, maxAttempts = 5, backoff = @Backoff(delay = 2000))
	public List<ProductEntity> getProductWithFilter(String q, BigDecimal minPrice, BigDecimal maxPrice) {
		return DatabaseFilterUtil.applyParamsFilterIntoDatabaseResult(repository.findAll(), q, minPrice, maxPrice);

	}

	private void checkIfProductExistsIntoDatabase(Optional<ProductEntity> resultDataBase) {
		if (resultDataBase.isEmpty()) {
			log.error("Didn't Have Results!");
			throw new DatabaseException(
					"Didn't find any results into database! can't update item, please inform an valid Id!");
		}
	}

}
