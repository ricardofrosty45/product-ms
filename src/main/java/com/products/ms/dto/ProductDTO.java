package com.products.ms.dto;

import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class ProductDTO {
	
	private String id;
	private String name;
	private String description;
	private BigDecimal price;

}
