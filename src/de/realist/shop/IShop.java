/**
 * 
 */
package de.realist.shop;

import de.realist.shop.exceptions.ArticleNotFoundException;
import de.realist.shop.exceptions.EmptyBasketException;
import de.realist.shop.list.IProductList;
import de.realist.shop.order.IAddress;
import de.realist.shop.order.IOrder;

/**
 * @author flattman
 *
 */
public interface IShop {
	/**
	 * Gibt das Inventory des Shops zurück.
	 * @return
	 */
	public IInventory getInventory();
	
	/**
	 * erzeugt einen Auftrag aus dem Warenkorb.
	 * @param invoiceAddress
	 * @param deliveryAddress
	 * @return
	 * @throws EmptyBasketException
	 */
	public IOrder buyBasket(IAddress invoiceAddress, IAddress deliveryAddress) throws EmptyBasketException;
	
	/**
	 * fügt ein Produkt/Menge zum Warenkorb hinzu.
	 * @param sku
	 * @param qty
	 * @throws ArticleNotFoundException
	 */
	public void addToBasket(String sku, int qty) throws ArticleNotFoundException;
	
	/**
	 * Gibt den Warenkorb zurück.
	 * @return
	 */
	public IProductList getBasket();
}
