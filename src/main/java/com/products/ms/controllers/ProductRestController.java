package com.products.ms.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/product", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Product", description = "Service responsible for make the CRUD of products")
@Validated
public class ProductRestController {

	@Operation(summary = "Test", description = "Test", tags = {
			"Product" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = Exception.class))),
			@ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class)))) })
	@GetMapping
	public ResponseEntity<?> findBySigriIds() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
