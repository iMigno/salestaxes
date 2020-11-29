package com.alessandromignogna.salestaxes.receipt;

import java.math.BigDecimal;
import java.util.List;

import com.alessandromignogna.salestaxes.util.TaxCalculator;

public class FiscalReceipt {

	private int id;
	private List<FiscalReceiptRow> rows;
	private TaxCalculator taxCalculator;

	public FiscalReceipt(int id, List<FiscalReceiptRow> rows, TaxCalculator taxCalculator) {
		super();
		this.id = id;
		this.rows = rows;
		this.taxCalculator = taxCalculator;

		if (rows != null) {
			for (FiscalReceiptRow row : rows) {
				row.setFiscalReceiptRowTaxes(taxCalculator.calculate(row));
			}
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<FiscalReceiptRow> getRows() {
		return rows;
	}

	public void setRows(List<FiscalReceiptRow> rows) {
		this.rows = rows;
	}

	public TaxCalculator getTaxCalculator() {
		return taxCalculator;
	}

	public void setTaxCalculator(TaxCalculator taxCalculator) {
		this.taxCalculator = taxCalculator;
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;

		if (rows != null) {
			for (FiscalReceiptRow row : rows) {
				BigDecimal price = row.getPrice();
				BigDecimal rowTaxes = BigDecimal.ZERO;
				for (FiscalReceiptRowTax rTax : row.getFiscalReceiptRowTaxes()) {
					rowTaxes = rowTaxes.add(rTax.getAmount());
				}
				BigDecimal rowPriceWithTaxes = price.add(rowTaxes);
				BigDecimal rowPriceWithTaxesAndQuantity = rowPriceWithTaxes.multiply(new BigDecimal(row.getQuantity()));
				total = total.add(rowPriceWithTaxesAndQuantity);
			}
		}

		return total;
	}

	public BigDecimal getTotalTaxAmount() {
		BigDecimal result = BigDecimal.ZERO;

		if (rows != null) {
			for (FiscalReceiptRow row : rows) {
				BigDecimal rowTaxes = BigDecimal.ZERO;
				for (FiscalReceiptRowTax rTax : row.getFiscalReceiptRowTaxes()) {
					rowTaxes = rowTaxes.add(rTax.getAmount());
				}
				BigDecimal rowTaxesWithQuantity = rowTaxes.multiply(new BigDecimal(row.getQuantity()));
				result = result.add(rowTaxesWithQuantity);
			}
		}

		return result;
	}

}
