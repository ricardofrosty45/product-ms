package com.products.ms.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.products.ms.entity.ProductEntity;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class DatabaseFilterUtil {

	private static final String RAGE_BIG_DECIMAL_FAILED = "Max and min filter didn't work, didn't return a item that's equals to your informed range";

	public List<ProductEntity> applyParamsFilterIntoDatabaseResult(List<ProductEntity> allProducts, String q,
			BigDecimal minPrice, BigDecimal maxPrice) {
		List<ProductEntity> filteredResult = new ArrayList<>();
		allProducts.forEach(result -> {
			if (q == null) {
				log.info("Params 'q' is null, filtering with just min and max values");
				comparePrices(result, minPrice, maxPrice,filteredResult);
			} else {
				if (q.isBlank() || q.isEmpty()) {
					log.info("Params 'q' is empty, filtering with just min and max values");
					comparePrices(result, minPrice, maxPrice,filteredResult);
				}
				if (result.getName().equals(q) && result.getDescription().equals(q)) {
					log.info("Params 'q' is not empty or null, filtering with 'q' and max and min range");
					comparePrices(result, minPrice, maxPrice,filteredResult);
				}
			}

		});

		return filteredResult;
	}

	private void comparePrices(ProductEntity result, BigDecimal minPrice, BigDecimal maxPrice,List<ProductEntity> filteredResult) {
		
		if (minPrice == null || maxPrice == null) {
			log.error(RAGE_BIG_DECIMAL_FAILED);
		} else {
			if (result.getPrice().doubleValue() >= minPrice.doubleValue()
					&& result.getPrice().doubleValue() <= maxPrice.doubleValue()) {
				filteredResult.add(result);

			} else {
				log.error(RAGE_BIG_DECIMAL_FAILED);
			}
		}

	}

}
