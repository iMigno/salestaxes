package com.alessandromignogna.salestaxes.model.tax;

import java.util.List;

import com.alessandromignogna.salestaxes.product.ProductGenesis;
import com.alessandromignogna.salestaxes.receipt.FiscalReceiptRow;

public class ImportDutyTaxCondition implements TaxCondition {

	private List<ProductGenesis> taxableGenesisList;

	public ImportDutyTaxCondition(List<ProductGenesis> taxableGenesisList) {
		this.taxableGenesisList = taxableGenesisList;
	}
	
	@Override
	public boolean isTaxable(FiscalReceiptRow row) {
		return taxableGenesisList.contains(row.getProduct().getProductGenesis());
	}

}
