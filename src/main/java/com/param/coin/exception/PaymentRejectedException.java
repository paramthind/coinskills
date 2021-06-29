package com.param.coin.exception;

import org.springframework.http.HttpStatus;


public class PaymentRejectedException extends CoinException {
	public PaymentRejectedException(String message) {
		super(message, HttpStatus.FORBIDDEN);
	}
}
