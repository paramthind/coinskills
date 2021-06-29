package com.param.coin.model;

import com.param.coin.enums.InvoiceStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;


@Table(name = "invoice")
@Entity
public class Invoice {

	@Id
	@Column(name = "dca", unique = true, nullable = false)
	private String dca; //Digital currency address

	private BigDecimal amount;

	private BigDecimal paidAmount = BigDecimal.valueOf(0.0);

	@Enumerated(EnumType.STRING)
	private InvoiceStatus status;

	private long creationTime;

	public Invoice() {
	}


	public Invoice(BigDecimal amount) {
		this.amount = amount;
		this.creationTime = System.currentTimeMillis();
	}

	public String getDca() {
		return dca;
	}

	public void setDca(String dca) {
		this.dca = dca;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public InvoiceStatus getStatus() {
		return status;
	}

	public void setStatus(InvoiceStatus status) {
		this.status = status;
	}

	public long getCreationTime() {
		return creationTime;
	}


	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return "Invoice{" + "dca='" + dca + '\'' + ", amount=" + amount + ", paidAmount=" + paidAmount + ", status="
				+ status + ", creationDate=" + creationTime + '}';
	}
}
