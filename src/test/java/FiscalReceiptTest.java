import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.alessandromignogna.salestaxes.model.tax.BasicTaxCondition;
import com.alessandromignogna.salestaxes.model.tax.ImportDutyTaxCondition;
import com.alessandromignogna.salestaxes.model.tax.Tax;
import com.alessandromignogna.salestaxes.product.Product;
import com.alessandromignogna.salestaxes.product.ProductGenesis;
import com.alessandromignogna.salestaxes.product.ProductType;
import com.alessandromignogna.salestaxes.receipt.FiscalReceipt;
import com.alessandromignogna.salestaxes.receipt.FiscalReceiptRow;
import com.alessandromignogna.salestaxes.util.TaxCalculator;
import com.alessandromignogna.salestaxes.util.TaxCalculatorImpl;

public class FiscalReceiptTest {
	
	private TaxCalculator taxCalculator;
	private List<FiscalReceiptRow> rows;

	@Before
	public void initTest() {
		initTaxes();
	}

	private void initTaxes() {
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
		taxCalculator = new TaxCalculatorImpl(taxes);
	}
	
	private void initZeroRow() {
		rows = new ArrayList<FiscalReceiptRow>();
		Product p1 = new Product("book", new BigDecimal("0"), ProductType.BOOK, ProductGenesis.IMPORTED);
		rows.add(new FiscalReceiptRow(100, p1));
	}
	
	private void initSimpleRowsWithQuantity() {
		rows = new ArrayList<FiscalReceiptRow>();
		Product p1 = new Product("book", new BigDecimal("1"), ProductType.BOOK, ProductGenesis.NATIONAL);
		rows.add(new FiscalReceiptRow(100, p1));
		
		Product p2 = new Product("pills", new BigDecimal("2"), ProductType.BOOK, ProductGenesis.NATIONAL);
		rows.add(new FiscalReceiptRow(50, p2));
	}
	
	private void initRowsWithTaxes() {
		rows = new ArrayList<FiscalReceiptRow>();
		
		Product p1 = new Product("computer", new BigDecimal("55"), ProductType.GENERIC_GOOD, ProductGenesis.NATIONAL);
		rows.add(new FiscalReceiptRow(1, p1));
		
		Product p2 = new Product("pills", new BigDecimal("13.55"), ProductType.MEDICAL, ProductGenesis.IMPORTED);
		rows.add(new FiscalReceiptRow(1, p2));
	}
	
	private void initRowsWithExample1() {
		rows = new ArrayList<FiscalReceiptRow>();
		
		Product p1 = new Product("book", new BigDecimal("12.49"), ProductType.BOOK, ProductGenesis.NATIONAL);
		rows.add(new FiscalReceiptRow(1, p1));
		
		Product p2 = new Product("music CD", new BigDecimal("14.99"), ProductType.GENERIC_GOOD, ProductGenesis.NATIONAL);
		rows.add(new FiscalReceiptRow(1, p2));
		
		Product p3 = new Product("chocolate bar", new BigDecimal("0.85"), ProductType.FOOD, ProductGenesis.NATIONAL);
		rows.add(new FiscalReceiptRow(1, p3));
	}
	
	private void initRowsWithExample2() {
		rows = new ArrayList<FiscalReceiptRow>();
		
		Product p1 = new Product("imported box of chocolates", new BigDecimal("10.00"), ProductType.FOOD, ProductGenesis.IMPORTED);
		rows.add(new FiscalReceiptRow(1, p1));
		
		Product p2 = new Product("imported bottle of perfume", new BigDecimal("47.50"), ProductType.GENERIC_GOOD, ProductGenesis.IMPORTED);
		rows.add(new FiscalReceiptRow(1, p2));
		
	}
	
	private void initRowsWithExample3() {
		rows = new ArrayList<FiscalReceiptRow>();
		
		Product p1 = new Product("bottle of perfume", new BigDecimal("27.99"), ProductType.GENERIC_GOOD, ProductGenesis.IMPORTED);
		rows.add(new FiscalReceiptRow(1, p1));
		
		Product p2 = new Product("bottle of perfume", new BigDecimal("18.99"), ProductType.GENERIC_GOOD, ProductGenesis.NATIONAL);
		rows.add(new FiscalReceiptRow(1, p2));
		
		Product p3 = new Product("packet of headache pills", new BigDecimal("9.75"), ProductType.MEDICAL, ProductGenesis.NATIONAL);
		rows.add(new FiscalReceiptRow(1, p3));
		
		Product p4 = new Product("box of imported chocolates", new BigDecimal("11.25"), ProductType.FOOD, ProductGenesis.IMPORTED);
		rows.add(new FiscalReceiptRow(1, p4));
		
	}
	
	
	@Test
	public void validZeroTotal() {
		initZeroRow();
		FiscalReceipt f = new FiscalReceipt(1, rows, taxCalculator);
		assertEquals(new BigDecimal("0.00"), f.getTotal());
		assertEquals(new BigDecimal("0.00"), f.getTotalTaxAmount());
	}
	
	@Test
	public void validSimpleTotalWithQuantity() {
		initSimpleRowsWithQuantity();
		FiscalReceipt f = new FiscalReceipt(1, rows, taxCalculator);
		assertEquals(new BigDecimal("200.00"), f.getTotal());
		assertEquals(new BigDecimal("0"), f.getTotalTaxAmount());
	}
	
	@Test
	public void validTotalWithTaxes() {
		initRowsWithTaxes();
		FiscalReceipt f = new FiscalReceipt(1, rows, taxCalculator);
		assertEquals(new BigDecimal("74.75"), f.getTotal());
		assertEquals(new BigDecimal("6.20"), f.getTotalTaxAmount());
	}
	
	@Test
	public void validTotalWithExample1() {
		initRowsWithExample1();
		FiscalReceipt f = new FiscalReceipt(1, rows, taxCalculator);
		assertEquals(new BigDecimal("29.83"), f.getTotal());
		assertEquals(new BigDecimal("1.50"), f.getTotalTaxAmount());
	}
	
	@Test
	public void validTotalWithExample2() {
		initRowsWithExample2();
		FiscalReceipt f = new FiscalReceipt(1, rows, taxCalculator);
		assertEquals(new BigDecimal("65.15"), f.getTotal());
		assertEquals(new BigDecimal("7.65"), f.getTotalTaxAmount());
	}
	
	@Test
	public void validTotalWithExample3() {
		initRowsWithExample3();
		FiscalReceipt f = new FiscalReceipt(1, rows, taxCalculator);
		assertEquals(new BigDecimal("74.68"), f.getTotal());
		assertEquals(new BigDecimal("6.70"), f.getTotalTaxAmount());
	}
	

}
