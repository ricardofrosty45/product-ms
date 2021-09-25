package com.products.ms.advice;


import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> requestIsWrong(Exception ex) {
		Map<String, Object> bodyResponde = new LinkedHashMap<>();
		bodyResponde.put("timestamp", LocalDateTime.now());
		bodyResponde.put("message", ex.getMessage());
		return new ResponseEntity<>(bodyResponde, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> didNotFindResultsIntoDatabase(Exception ex) {
		Map<String, Object> bodyResponde = new LinkedHashMap<>();
		bodyResponde.put("timestamp", LocalDateTime.now());
		bodyResponde.put("message", ex.getMessage());
		return new ResponseEntity<>(bodyResponde, HttpStatus.NOT_FOUND);
	}

}