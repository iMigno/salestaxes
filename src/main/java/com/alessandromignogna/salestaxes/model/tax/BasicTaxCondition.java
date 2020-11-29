package com.alessandromignogna.salestaxes.model.tax;

import java.util.List;

import com.alessandromignogna.salestaxes.product.ProductType;
import com.alessandromignogna.salestaxes.receipt.FiscalReceiptRow;

public class BasicTaxCondition implements TaxCondition {

	private List<ProductType> taxableTypes;

	public BasicTaxCondition(List<ProductType> taxableTypes) {
		this.taxableTypes = taxableTypes;
	}
	
	@Override
	public boolean isTaxable(FiscalReceiptRow row) {
		return taxableTypes.contains(row.getProduct().getProductType());
	}

}
