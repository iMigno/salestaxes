import static org.junit.Assert.assertTrue;

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
import com.alessandromignogna.salestaxes.receipt.FiscalReceiptRow;

public class TaxableTest {
	
	private Tax basicTax;
	private Tax dutyTax;

	@Before
	public void initTest() {
		initBasicTax();
		initDutyTax();
	}
	
	private void initBasicTax() {
		List<ProductType> taxableTypes = new ArrayList<ProductType>();
		taxableTypes.add(ProductType.GENERIC_GOOD);
		basicTax = new Tax("basic", new BigDecimal("10"), new BasicTaxCondition(taxableTypes));
	}
	
	private void initDutyTax() {
		List<ProductGenesis> taxableGenesis = new ArrayList<ProductGenesis>();
		taxableGenesis.add(ProductGenesis.IMPORTED);
		dutyTax = new Tax("duty", new BigDecimal("5"), new ImportDutyTaxCondition(taxableGenesis));
	}
	
	
	@Test
	public void isTaxableByBasicTax() {
		Product p1 = new Product("book", new BigDecimal("12.49"), ProductType.BOOK, ProductGenesis.NATIONAL);
		FiscalReceiptRow row1 = new FiscalReceiptRow(1, p1);
		assertTrue(!basicTax.getTaxCondition().isTaxable(row1));
		
		Product p2 = new Product("music CD", new BigDecimal("14.99"), ProductType.GENERIC_GOOD, ProductGenesis.NATIONAL);
		FiscalReceiptRow row2 = new FiscalReceiptRow(1, p2);
		assertTrue(basicTax.getTaxCondition().isTaxable(row2));
	}
	
	@Test
	public void isTaxableByDutyTax() {
		Product p1 = new Product("imported bottle of perfume", new BigDecimal("27.99"), ProductType.GENERIC_GOOD, ProductGenesis.IMPORTED);
		FiscalReceiptRow row1 = new FiscalReceiptRow(1, p1);
		assertTrue(dutyTax.getTaxCondition().isTaxable(row1));
		
		Product p2 = new Product("bottle of perfume ", new BigDecimal("18.99"), ProductType.GENERIC_GOOD, ProductGenesis.NATIONAL);
		FiscalReceiptRow row2 = new FiscalReceiptRow(1, p2);
		assertTrue(!dutyTax.getTaxCondition().isTaxable(row2));
	}
	

}
