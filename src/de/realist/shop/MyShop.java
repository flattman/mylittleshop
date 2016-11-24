/**
 * 
 */
package de.realist.shop;

import de.realist.shop.exceptions.ArticleNotFoundException;
import de.realist.shop.exceptions.EmptyBasketException;
import de.realist.shop.list.Basket;
import de.realist.shop.list.IProductList;
import de.realist.shop.order.AbstractOrderFactory;
import de.realist.shop.order.IAddress;
import de.realist.shop.order.IOrder;
import de.realist.shop.order.OrderFactory;
import de.realist.shop.product.Product;

/**
 * @author flattman
 * 
 */
public class MyShop implements IShop {
	private IInventory inventory;
	private IProductList basket;

	/**
	 * Erzeugt den Shop mit dem übergebenen Iventory.
	 * @param inventory
	 */
	public MyShop(IInventory inventory) {
		this.inventory = inventory;
		this.basket = new Basket();

	}

	/**
	 * Gibt das Inventory des Shops zurück.
	 */
	@Override
	public IInventory getInventory() {
		return this.inventory;
	}

	/**
	 * Wandelt den Basket in einen Order um.
	 */
	@Override
	public IOrder buyBasket(IAddress invoiceAddress, IAddress deliveryAddress) throws EmptyBasketException {
		AbstractOrderFactory orderFactory = new OrderFactory();
		
		return orderFactory.createOrder(this.basket, invoiceAddress, deliveryAddress);
	}

	/**
	 * Fügt dem Basket ein Produkt hinzu. 
	 */
	@Override
	public void addToBasket(String sku, int qty)
			throws ArticleNotFoundException {
		Product product = this.inventory.getProduct(sku);

		this.basket.addProduct(qty, product);
	}

	/**
	 * Gibt den Basket zurück.
	 */
	@Override
	public IProductList getBasket() {
		return this.basket;
	}
}