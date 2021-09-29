package com.products.ms.dto.request;

import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class ProductRequest {

	@NotNull(message = "Name cannot be null")
	@NotBlank(message = "Name cannot be empty, please inform us a name")
	private String name;
	@NotBlank(message = "Description cannot be empty, please inform us a description")
	@NotNull(message = "Description cannot be null")
	private String description;
	@NotNull(message = "Price cannot be null")
	@Min(value = 1, message = "Price Should Be Higher Or Equals 1")
	private BigDecimal price;
}
