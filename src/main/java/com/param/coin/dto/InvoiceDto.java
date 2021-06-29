package com.param.coin.dto;

import com.param.coin.enums.InvoiceStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;


public class InvoiceDto {
	private String dca; //Digital currency address
	private BigDecimal amount;
	private BigDecimal paidAmount;
	private BigDecimal balance;
	private InvoiceStatus status;
	private Date creationTime;


	public InvoiceDto(String dca, BigDecimal amount, BigDecimal paidAmount, InvoiceStatus status, long creationTime) {
		this.dca = dca;
		this.amount = amount;
		this.paidAmount = paidAmount;
		this.status = status;
		this.creationTime = new Date(creationTime);
		this.balance = this.amount.subtract(this.paidAmount).setScale(2, RoundingMode.HALF_EVEN);
	}

	public String getDca() {
		return dca;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public InvoiceStatus getStatus() {
		return status;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public BigDecimal getBalance() {
		return balance;
	}
}
