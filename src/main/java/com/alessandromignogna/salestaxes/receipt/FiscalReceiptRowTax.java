package com.alessandromignogna.salestaxes.receipt;

import java.math.BigDecimal;

import com.alessandromignogna.salestaxes.model.tax.Tax;

public class FiscalReceiptRowTax {

	private Tax tax;
	private BigDecimal amount;

	public FiscalReceiptRowTax(Tax tax, BigDecimal amount) {
		super();
		this.tax = tax;
		this.amount = amount;
	}

	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
