package com.products.ms.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.ms.dto.request.ProductRequest;
import com.products.ms.dto.response.ProductResponse;
import com.products.ms.dto.response.ResponseErrorReturn;
import com.products.ms.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/products", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Product", description = "Service responsible for make the CRUD of products")
public class ProductRestController {
	

	@Autowired
	private ProductService service;

	@Operation(summary = "This endpoint creates a new product", description = "This endpoint will create a new product into database. Name,description and price cannot be null or blank!", tags = {
			"Product" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseErrorReturn.class))),
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = ProductResponse.class))) })
	@PostMapping
	public ResponseEntity<?> createNewProducts(@Valid @RequestBody ProductRequest productRequest) {
		return new ResponseEntity<>(service.createNewProduct(productRequest), HttpStatus.CREATED);
	}

}
