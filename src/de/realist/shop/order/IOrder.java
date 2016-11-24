/**
 * 
 */
package de.realist.shop.order;

import java.util.Map.Entry;
import java.util.Set;

import de.realist.shop.list.IProductListItem;

/**
 * @author flattman
 *
 */
public interface IOrder {
	/**
	 * Gibt die Anzahl der Auftragspositionen zurück.
	 * @return
	 */
	public int countItems();
	
	/**
	 * Gibt die Auftragspositionen als Set zurück.
	 * @return
	 */
	public Set<Entry<String, IProductListItem>> getItemSet();
	
	/**
	 * Gibt die Rechnungsanschrift zurück.
	 * @return
	 */
	public IAddress getInvoiceAddress();
	
	/**
	 * Gibt die Lieferanschrift zurück.
	 * @return
	 */
	public IAddress getDeliveryAddress();
	
	/**
	 * 
	 * @return
	 */
	public Double getFinalPrice();
	
}