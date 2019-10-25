package com.param.coin.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Paramveer Singh on 2019-10-23.
 */
public class InvoiceNotFoundException extends CoinException{

	public InvoiceNotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}
}
