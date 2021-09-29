package com.products.ms.controllers.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;
import com.products.ms.controllers.ProductRestController;
import com.products.ms.dto.request.ProductRequest;
import com.products.ms.entity.ProductEntity;
import com.products.ms.mock.builder.MockBuilder;
import com.products.ms.repository.ProductRepository;
import com.products.ms.service.ProductService;

@WebMvcTest(ProductRestController.class)
@ContextConfiguration(classes = { ProductRepository.class, ProductService.class })
@EnableWebMvc
@ComponentScan(basePackages = "com.products.ms.controllers")
public class ProductRestControllerTest extends MockBuilder {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService service;

	@Test
	void shouldPostAndCreateAProduct() throws Exception {
		ProductRequest request = createProductRequest();
		ProductEntity entity = createEntity();
		Mockito.when(service.createNewProduct(request)).thenReturn(entity);

		mockMvc.perform(
				post("/v1/products").content(new Gson().toJson(request)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	void shouldPostAndNotCreateAProduct() throws Exception {
		ProductRequest request = createProductRequest();
		request.setPrice(null);
		ProductEntity entity = createEntity();
		Mockito.when(service.createNewProduct(request)).thenReturn(entity);

		mockMvc.perform(
				post("/v1/products").content(new Gson().toJson(request)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldPutAndUpdateProductById() throws Exception {
		ProductRequest request = createProductRequest();
		ProductEntity entity = createEntity();
		Mockito.when(service.updateProductById(request, entity.getId())).thenReturn(entity);

		mockMvc.perform(put("/v1/products/{id}", entity.getId()).content(new Gson().toJson(request))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void shouldPutNotUpdateProductById() throws Exception {

		Mockito.when(service.updateProductById(null, "123")).thenReturn(null);

		mockMvc.perform(put("/v1/products/{id}", "123").content("{}").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldGetProductById() throws Exception {
		ProductEntity entity = createEntity();
		Mockito.when(service.getProductById("123")).thenReturn(entity);

		mockMvc.perform(get("/v1/products/{id}", entity.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void shouldGetAllProducts() throws Exception {

		Mockito.when(service.findAllProducts()).thenReturn(listEntity());

		mockMvc.perform(get("/v1/products/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void shouldDeleteProductById() throws Exception {
		String id = "123";
		Mockito.when(service.deleteProductIntoDatabase(id)).thenReturn(createResponseReturn());

		mockMvc.perform(delete("/v1/products/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void shouldNotSearchProductWithFilter() throws Exception {
		String q = "test";
		BigDecimal minPrice = new BigDecimal(10);
		BigDecimal maxPrice = new BigDecimal(1);
		Mockito.when(service.getProductWithFilter(q, minPrice, maxPrice)).thenReturn(listEntity());

		mockMvc.perform(get("/v1/products/search").param("q", q).param("minPrice", minPrice.toString())
				.param("maxPrice", maxPrice.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	void shouldCheckHealthCheck() throws Exception {

		mockMvc.perform(get("/v1/healthcheck").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
