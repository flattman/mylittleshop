/**
 * 
 */
package de.realist.shop.order;

import de.realist.shop.list.BasketItem;
import de.realist.shop.list.IProductListItem;
import de.realist.shop.product.Product;

/**
 * @author flattman
 *
 */
public class OrderItem extends BasketItem {

	/**
	 * Erzeugt ein neues OrderItem anhand von Menge und Produkt.
	 * @param quantity Bestellmenge
	 * @param product Bestellprodukt
	 */
	public OrderItem(int quantity, Product product) {
		super(quantity, product);
	}

	/**
	 * Erzeugt ein OrderItem anhand eines anderen Items.
	 * @param item
	 */
	public OrderItem(IProductListItem item) {
		super(item);
	}
	
	/**
	 * Erzeugt ein OrderItem anhand eine BasketItems.
	 * @param item
	 */
	public OrderItem(BasketItem item) {
		super(item);
	}
}
