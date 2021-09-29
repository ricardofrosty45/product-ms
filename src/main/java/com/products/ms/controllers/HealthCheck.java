package com.products.ms.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.ms.dto.response.HealthCheckResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/healthcheck")
@Tag(name = "HealthCheck", description = "Endpoint responsable for a Health Check of the service")
@CrossOrigin
public class HealthCheck {

	@Operation(summary = "Health Check", description = "This endpoint is a healthcheck of the ms", tags = { "HealthCheck" })
	@ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = HealthCheckResponse.class)))
	@GetMapping
	public ResponseEntity<?> heathCheckEndpoint() {
		return new ResponseEntity<>(new HealthCheckResponse(), HttpStatus.OK);
	}

}
