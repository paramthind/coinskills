package com.param.coin.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Paramveer Singh on 2019-10-24.
 */
public class PaymentRejectedException extends CoinException {
	public PaymentRejectedException(String message) {
		super(message, HttpStatus.FORBIDDEN);
	}
}
