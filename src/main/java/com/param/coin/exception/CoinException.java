package com.param.coin.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Paramveer Singh on 2019-10-23.
 */
public abstract class CoinException extends RuntimeException{

	private HttpStatus httpStatus;

	CoinException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
