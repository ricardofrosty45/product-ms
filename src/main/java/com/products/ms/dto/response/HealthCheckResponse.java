package com.products.ms.dto.response;

import lombok.Data;

@Data
public class HealthCheckResponse {
	private String message = "Service is up";
	private long timestamp = System.currentTimeMillis();

}
