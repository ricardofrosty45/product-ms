package com.products.ms.dto.response;
import static lombok.AccessLevel.PRIVATE;

import com.products.ms.dto.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class ProductResponse {
	
	private ProductDTO product;

}
