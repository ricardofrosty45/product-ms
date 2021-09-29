package com.products.ms.mock.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.products.ms.dto.request.ProductRequest;
import com.products.ms.dto.response.ResponseReturn;
import com.products.ms.entity.ProductEntity;

public abstract class MockBuilder {

	public ProductEntity createEntity() {
		return ProductEntity.builder().id(UUID.randomUUID().toString()).name("String").description("String").build();
	}
	
	
	public ProductRequest createProductRequest() {
		return ProductRequest.builder().name("String").description("String").price(new BigDecimal(5)).build();
	}
	
	
	public ResponseReturn createResponseReturn() {
		return ResponseReturn.builder().message("Message").status(0).build();
	}
	
	public List<ProductEntity> listEntity(){
		List<ProductEntity> list = new ArrayList<ProductEntity>();
		list.add(createEntity());
		return list;
	}


}
