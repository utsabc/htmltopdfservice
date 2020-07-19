package com.service.resource.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ErrorHandler {
	
	private final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);
	
	public ResponseEntity<String> exceptionHandler(Exception ex){
		logger.error(ex.getMessage());
		logger.error("Following Exception Ocurred: ",ex );
		return ResponseEntity.ok(ex.getMessage());
		
	}

}
