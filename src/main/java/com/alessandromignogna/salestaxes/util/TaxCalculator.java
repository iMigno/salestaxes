package com.alessandromignogna.salestaxes.util;

import java.util.List;

import com.alessandromignogna.salestaxes.receipt.FiscalReceiptRow;
import com.alessandromignogna.salestaxes.receipt.FiscalReceiptRowTax;

public interface TaxCalculator {
	
	public List<FiscalReceiptRowTax> calculate(FiscalReceiptRow row);
}
