package com.alessandromignogna.salestaxes.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alessandromignogna.salestaxes.product.Product;
import com.alessandromignogna.salestaxes.product.ProductGenesis;
import com.alessandromignogna.salestaxes.product.ProductType;
import com.alessandromignogna.salestaxes.receipt.FiscalReceiptRow;

public class CsvReader {

	private static final String CSV_SPLIT_CHAR = ",";
	public static final String IMPORTED_KEYWORD = "imported ";

	public static List<FiscalReceiptRow> readCsvFile(int id, String csvFile) {

		List<FiscalReceiptRow> rows = new ArrayList<FiscalReceiptRow>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			String line = "";

			while ((line = br.readLine()) != null) {
				String[] infos = line.split(CSV_SPLIT_CHAR);

				if (infos.length != 3) {
					// bad line
					throw new Exception("File format not recognized");
				}

				int quantity = Integer.parseInt(infos[0]);
				String productTitle = infos[1];
				BigDecimal price = new BigDecimal((infos[2]));
				
				String realProductTitle = productTitle.replace(IMPORTED_KEYWORD, "");
				
				ProductType type = ProductType.GENERIC_GOOD;
				if (productTitle.contains("pills")) {
					type = ProductType.MEDICAL;
				}
				if (productTitle.contains("chocolate")) {
					type = ProductType.FOOD;
				}
				if (productTitle.contains("book")) {
					type = ProductType.BOOK;
				}

				ProductGenesis origin = ProductGenesis.NATIONAL;
				if (productTitle.contains(IMPORTED_KEYWORD)) {
					origin = ProductGenesis.IMPORTED;
				}

				Product product = new Product(realProductTitle, price, type, origin);
				FiscalReceiptRow row = new FiscalReceiptRow(quantity, product);

				rows.add(row);
				
			}
			
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rows;
	}
}
