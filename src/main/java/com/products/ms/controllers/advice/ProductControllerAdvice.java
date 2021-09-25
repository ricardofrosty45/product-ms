package com.products.ms.controllers.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.products.ms.dto.response.ResponseErrorReturn;

@ControllerAdvice
public class ProductControllerAdvice extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(
				ResponseErrorReturn.builder().status(HttpStatus.BAD_REQUEST.value()).message(exception.getFieldError().getDefaultMessage()).build(),
				HttpStatus.BAD_REQUEST);
	}
}
