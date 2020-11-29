package com.alessandromignogna.salestaxes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alessandromignogna.salestaxes.model.tax.BasicTaxCondition;
import com.alessandromignogna.salestaxes.model.tax.ImportDutyTaxCondition;
import com.alessandromignogna.salestaxes.model.tax.Tax;
import com.alessandromignogna.salestaxes.product.ProductGenesis;
import com.alessandromignogna.salestaxes.product.ProductType;
import com.alessandromignogna.salestaxes.receipt.FiscalReceipt;
import com.alessandromignogna.salestaxes.receipt.FiscalReceiptRow;
import com.alessandromignogna.salestaxes.util.CsvReader;
import com.alessandromignogna.salestaxes.util.FiscalReceiptPrinter;
import com.alessandromignogna.salestaxes.util.TaxCalculator;
import com.alessandromignogna.salestaxes.util.TaxCalculatorImpl;

public class SalesTaxes {

	public static void main(String[] args) {

		// create taxes
		List<Tax> taxes = new ArrayList<Tax>();

		List<ProductType> taxableTypes = new ArrayList<ProductType>();
		taxableTypes.add(ProductType.GENERIC_GOOD);
		Tax basicSalesTax = new Tax("basic", new BigDecimal("10"), new BasicTaxCondition(taxableTypes));
		taxes.add(basicSalesTax);

		List<ProductGenesis> taxableGenesis = new ArrayList<ProductGenesis>();
		taxableGenesis.add(ProductGenesis.IMPORTED);
		Tax importDutyTax = new Tax("duty", new BigDecimal("5"), new ImportDutyTaxCondition(taxableGenesis));
		taxes.add(importDutyTax);

		// create Tax Calculator
		TaxCalculator taxCalculator = new TaxCalculatorImpl(taxes);

		// read and print 1st receipt
		List<FiscalReceiptRow> rows1 = CsvReader.readCsvFile(1, "receiptA.csv");
		FiscalReceipt r1 = new FiscalReceipt(1, rows1, taxCalculator);
		FiscalReceiptPrinter.printFiscalReceipt(r1);

		// read and print 2nd receipt
		List<FiscalReceiptRow> rows2 = CsvReader.readCsvFile(2, "receiptB.csv");
		FiscalReceipt r2 = new FiscalReceipt(2, rows2, taxCalculator);
		r1.setTaxCalculator(taxCalculator);
		FiscalReceiptPrinter.printFiscalReceipt(r2);

		// read and print 3rd receipt
		List<FiscalReceiptRow> rows3 = CsvReader.readCsvFile(3, "receiptC.csv");
		FiscalReceipt r3 = new FiscalReceipt(3, rows3, taxCalculator);
		r1.setTaxCalculator(taxCalculator);
		FiscalReceiptPrinter.printFiscalReceipt(r3);

	}

}
