package com.param.coin.config;

import com.param.coin.exception.CoinException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handles exceptions that occur in this project and returns a json response.
 * Created by Paramveer Singh on 2019-10-23.
 */
@ControllerAdvice
public class CoinExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(CoinExceptionHandler.class);

	@ExceptionHandler
	ResponseEntity handleException(CoinException exception) {
		log.warn(exception.getMessage());
		return new ResponseEntity(buildErrorResponse(exception.getMessage()), exception.getHttpStatus());
	}

	@ExceptionHandler
	ResponseEntity handleException(Exception exception) {
		log.error("Exception", exception);
		return new ResponseEntity(buildErrorResponse(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private String buildErrorResponse(String msg){
		return "{ \"msg\" : \"" + msg + "\"}";
	}
}
