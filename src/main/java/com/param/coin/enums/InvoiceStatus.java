package com.param.coin.enums;


public enum InvoiceStatus {

	EXPIRED("Expired"), // not sure when to set this. Using a scheduler or with some other criteria. No details are provided.
	PARTIALLY_PAID("Partially paid"),
	PAID("Paid");
	// Should have UPAID enum too

	private final String status;

	InvoiceStatus(String s) {
		this.status = s;
	}

	public String getStatus() {
		return status;
	}
}
