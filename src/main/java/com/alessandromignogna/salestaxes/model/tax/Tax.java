package com.alessandromignogna.salestaxes.model.tax;

import java.math.BigDecimal;

public class Tax {

	private String title;
	private BigDecimal rate;
	private TaxCondition taxCondition;

	public Tax(String title, BigDecimal rate, TaxCondition taxCondition) {
		this.title = title;
		this.rate = rate;
		this.taxCondition = taxCondition;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public TaxCondition getTaxCondition() {
		return taxCondition;
	}

	public void setTaxCondition(TaxCondition taxCondition) {
		this.taxCondition = taxCondition;
	}

}
