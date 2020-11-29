package com.alessandromignogna.salestaxes.model.tax;

import com.alessandromignogna.salestaxes.receipt.FiscalReceiptRow;

public interface TaxCondition {

	boolean isTaxable(FiscalReceiptRow row);
}
