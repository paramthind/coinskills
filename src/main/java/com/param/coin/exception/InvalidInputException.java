package com.param.coin.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Paramveer Singh on 2019-10-23.
 */
public class InvalidInputException extends CoinException{

	public InvalidInputException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}
}
