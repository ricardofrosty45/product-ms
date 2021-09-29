package com.products.ms.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.products.ms.dto.request.ProductRequest;
import com.products.ms.dto.response.ResponseReturn;
import com.products.ms.entity.ProductEntity;
import com.products.ms.service.ProductService;

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
@RequestMapping(value = "/v1/products", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Product", description = "Service responsible for make the CRUD of products")
@CrossOrigin
public class ProductRestController {

	@Autowired
	private ProductService service;

	@Operation(summary = "Create new product", description = "This endpoint will create a new product into database. Name,description and price cannot be null or blank!", tags = {
			"Product" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseReturn.class))),
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = ProductEntity.class))) })
	@PostMapping
	public ResponseEntity<?> createNewProducts(@Valid @RequestBody ProductRequest productRequest) {
		return new ResponseEntity<>(service.createNewProduct(productRequest), HttpStatus.CREATED);
	}

	@Operation(summary = "Update product by id", description = "This endpoint will update a product into database. Name,description and price cannot be null or blank!", tags = {
			"Product" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ResponseReturn.class))),
			@ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = ProductEntity.class))) })
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProductById(@Valid @RequestBody ProductRequest productRequest,
			@Valid @NotNull(message = "Id cannot be null") @NotBlank(message = "Id cannot be empty, please inform us a Id") @PathVariable("id") String id) {
		return new ResponseEntity<>(service.updateProductById(productRequest, id), HttpStatus.OK);
	}

	@Operation(summary = "Find product by id", description = "This endpoint will find a product by id into the database.", tags = {
			"Product" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ResponseReturn.class))),
			@ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = ProductEntity.class))) })
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(
			@Valid @NotNull(message = "Id cannot be null") @NotBlank(message = "Id cannot be empty, please inform us a Id") @PathVariable("id") String id) {
		return new ResponseEntity<>(service.getProductById(id), HttpStatus.OK);
	}

	@Operation(summary = "Get All Products", description = "This endpoint will get all products into database", tags = {
			"Product" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductEntity.class)))) })
	@GetMapping()
	public ResponseEntity<?> getAllProducts() {
		return new ResponseEntity<>(service.findAllProducts(), HttpStatus.OK);
	}

	@Operation(summary = "Delete product by id", description = "This endpoint will find a product and delete into database", tags = {
			"Product" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ResponseReturn.class))),
			@ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = ResponseReturn.class))) })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProductById(
			@Valid @NotNull(message = "Id cannot be null") @NotBlank(message = "Id cannot be empty, please inform us a Id") @PathVariable("id") String id) {
		return new ResponseEntity<>(service.deleteProductIntoDatabase(id), HttpStatus.OK);
	}

	@Operation(summary = "Find product with filters", description = "This endpoint will find a product and filter it by a max and min range and q must be equals to the name and description.", tags = {
			"Product" })
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "No Content"),
			@ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductEntity.class)))) })
	@GetMapping("/search")
	public ResponseEntity<?> getProductWithFilters(@RequestParam(value = "q", required = false) String q,
			@RequestParam(value = "min_price", required = false) BigDecimal minPrice,
			@RequestParam(value = "max_price", required = false) BigDecimal maxPrice) {

		List<ProductEntity> result = service.getProductWithFilter(q, minPrice, maxPrice);
		if (result.isEmpty()) {
			return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(service.getProductWithFilter(q, minPrice, maxPrice), HttpStatus.OK);
	}

	@Operation(summary = "Health Check", description = "This endpoint is a healthcheck of the ms", tags = { "Product" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Ok") })
	@GetMapping("/healthcheck")
	public ResponseEntity<?> heathCheckEndpoint() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
