package com.param.coin.dto;

import com.param.coin.enums.PaymentType;

import java.math.BigDecimal;

/**
 * Created by Paramveer Singh on 2019-10-24.
 */
public class PaymentPostDto {
	private BigDecimal amount;
	private String dca;
	private PaymentType type;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDca() {
		return dca;
	}

	public void setDca(String dca) {
		this.dca = dca;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}
}
