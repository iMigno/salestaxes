package com.alessandromignogna.salestaxes.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.alessandromignogna.salestaxes.model.tax.Tax;
import com.alessandromignogna.salestaxes.receipt.FiscalReceiptRow;
import com.alessandromignogna.salestaxes.receipt.FiscalReceiptRowTax;

public class TaxCalculatorImpl implements TaxCalculator {

	private static final BigDecimal ROUND_EDGE = new BigDecimal("0.05");

	private List<Tax> taxes;

	public TaxCalculatorImpl(List<Tax> taxes) {
		super();
		this.taxes = taxes;
	}

	@Override
	public List<FiscalReceiptRowTax> calculate(FiscalReceiptRow row) {
		List<FiscalReceiptRowTax> result = new ArrayList<FiscalReceiptRowTax>();

		for (Tax tax : taxes) {
			if (tax.getTaxCondition().isTaxable(row)) {
				BigDecimal amount = calculateByTax(row, tax);
				FiscalReceiptRowTax rTax = new FiscalReceiptRowTax(tax, amount);
				result.add(rTax);
			}

		}
		return result;
	}

	private static BigDecimal calculateByTax(FiscalReceiptRow row, Tax tax) {
		BigDecimal taxable = row.getPrice().multiply(tax.getRate());
		BigDecimal result = taxable.divide(new BigDecimal(100));
		result = round(result, ROUND_EDGE);
		return result;
	}

	private static BigDecimal round(BigDecimal value, BigDecimal edge) {
		if (edge.compareTo(BigDecimal.ZERO) != 0) {
			BigDecimal divided = value.divide(edge, 0, RoundingMode.UP);
			BigDecimal result = divided.multiply(edge);
			return result;
		} else {
			return value;
		}
	}

}
