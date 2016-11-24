/**
 * 
 */
package de.realist.shop.order;

import de.realist.shop.exceptions.EmptyBasketException;
import de.realist.shop.list.IProductList;

/**
 * @author flattman
 *
 */
public abstract class AbstractOrderFactory {
	/**
	 * Erzeugt ein neuen Auftrag anhand eines Warenkorbs.
	 * @param basket
	 * @param invoiceAddress
	 * @param deliveryAddress
	 * @return
	 * @throws EmptyBasketException
	 */
	public abstract IOrder createOrder(IProductList basket, IAddress invoiceAddress, IAddress deliveryAddress) throws EmptyBasketException;
}
