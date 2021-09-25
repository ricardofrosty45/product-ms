package com.products.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class ProductsMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsMsApplication.class, args);
	}

}
