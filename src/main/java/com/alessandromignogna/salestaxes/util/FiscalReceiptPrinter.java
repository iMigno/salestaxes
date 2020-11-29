package com.alessandromignogna.salestaxes.util;

import java.math.BigDecimal;

import com.alessandromignogna.salestaxes.product.ProductGenesis;
import com.alessandromignogna.salestaxes.receipt.FiscalReceipt;
import com.alessandromignogna.salestaxes.receipt.FiscalReceiptRow;
import com.alessandromignogna.salestaxes.receipt.FiscalReceiptRowTax;

public class FiscalReceiptPrinter {
	
	
	public static void printFiscalReceipt(FiscalReceipt fp) {

		BigDecimal total = BigDecimal.ZERO;
		BigDecimal salesTaxes = BigDecimal.ZERO;

		System.out.println("Output " + fp.getId() + ":");
		for (FiscalReceiptRow row : fp.getRows()) {
			BigDecimal price = row.getPrice();
			BigDecimal rowTaxes = BigDecimal.ZERO;
			for(FiscalReceiptRowTax rTax : row.getFiscalReceiptRowTaxes()) {
				rowTaxes = rowTaxes.add(rTax.getAmount());
			}
			BigDecimal rowPriceWithTaxes = price.add(rowTaxes);
			
			total = total.add(rowPriceWithTaxes);
			salesTaxes = salesTaxes.add(rowTaxes);
			String toPrint = row.getQuantity() + " ";
			if (ProductGenesis.IMPORTED.equals(row.getProduct().getProductGenesis())) {
				toPrint += CsvReader.IMPORTED_KEYWORD;
			}
			toPrint += row.getProduct().getTitle() + ": " + rowPriceWithTaxes;

			System.out.println(toPrint);
		}

		System.out.println("Sales Taxes: " + salesTaxes);
		System.out.println("Total: " + total);
		System.out.println("");

	}
}
