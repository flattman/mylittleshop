/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.realist.shop.exceptions.ArticleNotFoundException;
import de.realist.shop.list.Basket;
import de.realist.shop.list.IProductList;
import de.realist.shop.list.IProductListItem;
import de.realist.shop.product.Product;

/**
 * @author flattman
 *
 */
public class BasketTest {
	IProductList basket;
	Product prod1;
	Product prod2;
	Product prod3;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.basket = new Basket();
		
		this.prod1 = new Product("4507010001", "Product 1", 2.99);
		this.prod2 = new Product("4507010002", "Product 2", 1.55);
		this.prod3 = new Product("4507010003", "Product 3", 0.89);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.basket = null;
		this.prod1 = null;
		this.prod2 = null;
		this.prod3 = null;
	}

	/**
	 * Test method for {@link de.realist.shop.list.Basket#addProduct(de.realist.shop.product.Product, int)}.
	 */
	@Test
	public void testAddNewProduct() {
		this.basket.addProduct(5, this.prod1);
		
		try {
			this.basket.getProduct(this.prod1.getSku());
		} catch (ArticleNotFoundException e) {
			fail("Produkt wurde nicht im basket gefunden.");
		}
	}
	
	/**
	 * Test method for {@link de.realist.shop.list.Basket#addProduct(de.realist.shop.product.Product, int)}.
	 */
	@Test
	public void testAddSameProduct() {
		this.basket.addProduct(5, this.prod1);
		this.basket.addProduct(3, this.prod1);
		
		try {
			IProductListItem addedItem = this.basket.getProduct(this.prod1.getSku());
			assertEquals(8, addedItem.getQuantity());
		} catch (ArticleNotFoundException e) {
			fail("Produkt wurde nicht im basket gefunden.");
		}
	}

	/**
	 * Test method for {@link de.realist.shop.list.Basket#removeProduct(java.lang.String)}.
	 */
	@Test
	public void testRemoveProduct() {
		this.basket.addProduct(1, this.prod1);
		this.basket.removeProduct(this.prod1.getSku());
		
		boolean thrown = false;
		try {
			this.basket.getProduct(this.prod1.getSku());
		} catch (ArticleNotFoundException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}

	/**
	 * Test method for {@link de.realist.shop.list.Basket#clear()}.
	 */
	@Test
	public void testClear() {
		this.basket.addProduct(1, this.prod1);
		this.basket.clear();
		assertEquals(0, this.basket.countItems());
	}

	/**
	 * Test method for {@link de.realist.shop.list.Basket#countItems()}.
	 */
	@Test
	public void testCountItems() {
		this.basket.addProduct(1, this.prod1);
		this.basket.addProduct(1, this.prod2);
		this.basket.addProduct(1, this.prod3);
		
		assertEquals(3, this.basket.countItems());
	}

}
