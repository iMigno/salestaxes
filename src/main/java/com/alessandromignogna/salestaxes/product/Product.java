package com.alessandromignogna.salestaxes.product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {

	private String title;

	private BigDecimal price;

	private ProductType productType;

	private ProductGenesis productGenesis;

	public Product(String title, BigDecimal price, ProductType productType, ProductGenesis productGenesis) {
		this.title = title;
		this.price = price.setScale(2, RoundingMode.HALF_UP);
		this.productType = productType;
		this.productGenesis = productGenesis;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public ProductGenesis getProductGenesis() {
		return productGenesis;
	}

	public void setProductGenesis(ProductGenesis productGenesis) {
		this.productGenesis = productGenesis;
	}

}
