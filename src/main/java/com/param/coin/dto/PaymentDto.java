package com.param.coin.dto;

import com.param.coin.enums.PaymentType;

import java.math.BigDecimal;


public class PaymentDto {

	private String id;
	private BigDecimal amount;
	private String dca;
	private PaymentType paymentType;

	public PaymentDto(String id, BigDecimal amount, String dca, PaymentType paymentType) {
		this.id = id;
		this.amount = amount;
		this.dca = dca;
		this.paymentType = paymentType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getDca() {
		return dca;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}
}
