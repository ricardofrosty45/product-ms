package com.products.ms.entity;

import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
@Document(collection = "product-collection")
public class ProductEntity {

	@Id
	private String id;
	private String name;
	private String description;
	private BigDecimal price;

}
