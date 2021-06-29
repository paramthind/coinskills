package com.param.coin.exception;

import org.springframework.http.HttpStatus;


public class InvoiceNotFoundException extends CoinException{

	public InvoiceNotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}
}
