/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.realist.shop.list.BasketItem;
import de.realist.shop.product.Product;

/**
 * @author flattman
 *
 */
public class BasketItemTest {
	Product prod;
	BasketItem item;
	int testQuantity = 10;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		prod = new Product("4507010001", "TestProduct", 2.99);
		item = new BasketItem(this.testQuantity, this.prod);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.prod = null;
	}

	/**
	 * Test method for {@link de.realist.shop.list.BasketItem#BasketItem(int, de.realist.shop.product.Product)}.
	 */
	@Test
	public void testBasketItemIntProduct() {
		boolean ok = false;
		
		if (this.item.getSku().equals(this.prod.getSku())
				&& this.item.getName().equals(this.prod.getName())
				&& this.item.getQuantity() == this.testQuantity
				&& 0 == Double.compare(this.item.getPrice(), this.prod.getPrice())
				&& this.item.getProduct().hashCode() == this.prod.hashCode()) {
			ok = true;
		}
	 	
		assertTrue(ok);
	}

	/**
	 * Test method for {@link de.realist.shop.list.BasketItem#BasketItem(de.realist.shop.list.IProductListItem)}.
	 */
	@Test
	public void testBasketItemIProductListItem() {
		boolean ok = false;
		
		BasketItem testItem = new BasketItem(this.item);
		
		if (testItem.getSku().equals(this.prod.getSku())
				&& testItem.getName().equals(this.prod.getName())
				&& testItem.getQuantity() == this.testQuantity
				&& 0 == Double.compare(testItem.getPrice(), this.prod.getPrice())
				&& testItem.getProduct().hashCode() == this.prod.hashCode()) {
			ok = true;
		}
	 	
		assertTrue(ok);
	}

	/**
	 * Test method for {@link de.realist.shop.list.BasketItem#getFinalPrice()}.
	 */
	@Test
	public void testGetFinalPrice() {
		double testSum = this.testQuantity * this.prod.getPrice();
		assertEquals(testSum, this.item.getFinalPrice(), 0);
	}

	/**
	 * Test method for {@link de.realist.shop.list.BasketItem#getPrice()}.
	 */
	@Test
	public void testGetPrice() {
		assertEquals(this.item.getPrice(), this.prod.getPrice(), 0);
	}

	/**
	 * Test method for {@link de.realist.shop.list.BasketItem#getProduct()}.
	 */
	@Test
	public void testGetProduct() {
		assertEquals(this.prod.hashCode(), this.item.getProduct().hashCode());
	}

	/**
	 * Test method for {@link de.realist.shop.list.BasketItem#getQuantity()}.
	 */
	@Test
	public void testGetQuantity() {
		assertEquals(this.testQuantity, this.item.getQuantity());
	}

	/**
	 * Test method for {@link de.realist.shop.list.BasketItem#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals(this.item.getName(), this.prod.getName());
	}

	/**
	 * Test method for {@link de.realist.shop.list.BasketItem#getSku()}.
	 */
	@Test
	public void testGetSku() {
		assertEquals(this.item.getSku(), this.prod.getSku());
	}

	/**
	 * Test method for {@link de.realist.shop.list.BasketItem#add(de.realist.shop.list.IProductListItem)}.
	 */
	@Test
	public void testAdd() {
		BasketItem testItem = new BasketItem(1, this.prod);
		this.item.add(testItem);
		
		assertEquals(this.item.getQuantity(), this.testQuantity + testItem.getQuantity());
	}

	/**
	 * Test method for {@link de.realist.shop.list.BasketItem#isSameProduct(de.realist.shop.list.IProductListItem)}.
	 */
	@Test
	public void testIsSameProduct() {
		assertTrue(this.item.isSameProduct(this.item));
	}
	
	/**
	 * Test method for {@link de.realist.shop.list.BasketItem#isSameProduct(de.realist.shop.list.IProductListItem)}.
	 */
	@Test
	public void testIsNotSameProduct() {
		Product prod1 = new Product("22423", "test", 1.99);
		BasketItem testItem = new BasketItem(1, prod1);
		
		assertFalse(this.item.isSameProduct(testItem));
	}
}
