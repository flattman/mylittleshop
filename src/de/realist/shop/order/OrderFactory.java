/**
 * 
 */
package de.realist.shop.order;

import java.util.Map;

import de.realist.shop.exceptions.EmptyBasketException;
import de.realist.shop.list.IProductList;
import de.realist.shop.list.IProductListItem;

/**
 * @author flattman
 * Factory zur Erzeugung von Aufträgen.
 */
public class OrderFactory extends AbstractOrderFactory {

	@Override
	/**
	 * Erzeugt einen neuen Auftrag.
	 */
	public IOrder createOrder(IProductList basket, IAddress invoiceAddress,
			IAddress deliveryAddress) throws EmptyBasketException {
		if (basket.countItems() == 0) {
			throw new EmptyBasketException();
		}

		Order order = new Order(deliveryAddress, deliveryAddress);
		
		for (Map.Entry<String, IProductListItem> entry : basket.getItemSet()) {
			IProductListItem item = entry.getValue();
			order.addProduct(item);
		}
		
		return order;
	}
}
