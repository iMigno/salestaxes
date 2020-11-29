package com.alessandromignogna.salestaxes.receipt;

import java.math.BigDecimal;
import java.util.List;

import com.alessandromignogna.salestaxes.product.Product;

public class FiscalReceiptRow {

	private int quantity;
	private Product product;
	private BigDecimal price;
	private List<FiscalReceiptRowTax> fiscalReceiptRowTaxes;

	public FiscalReceiptRow(int quantity, Product product) {
		super();
		this.quantity = quantity;
		this.product = product;
		this.price = product.getPrice();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<FiscalReceiptRowTax> getFiscalReceiptRowTaxes() {
		return fiscalReceiptRowTaxes;
	}

	public void setFiscalReceiptRowTaxes(List<FiscalReceiptRowTax> fiscalReceiptRowTaxes) {
		this.fiscalReceiptRowTaxes = fiscalReceiptRowTaxes;
	}

}
