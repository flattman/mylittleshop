/**
 * 
 */
package de.realist.shop.order;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import de.realist.shop.list.IProductListItem;

/**
 *
 * @author flattman
 *
 */
public class Order implements IOrder {
	protected IAddress invoiceAddress;
	protected IAddress deliveryAddress;
	private Map<String, IProductListItem> items = new HashMap<>();
	private double finalPrice = 0;
	
	/**
	 * Erzeugt einen neuen Auftrag.
	 * @param invoiceAddress Rechnungsadresse
	 * @param deliveryAddress Lieferadresse
	 * @param items
	 */
	public Order(IAddress invoiceAddress, IAddress deliveryAddress) {
		this.invoiceAddress = invoiceAddress;
		this.deliveryAddress = deliveryAddress;
	}
	
	/**
	 * Fügt eine Auftragsposition hinzu.
	 * @param item
	 */
	public void addProduct(IProductListItem item) {
		this.finalPrice += item.getFinalPrice();
		
		if (this.items.containsKey(item.getSku())) {
			IProductListItem oldItem = this.items.get(item.getSku());
			item.add(oldItem);
		}
		
		this.items.put(item.getSku(), item);
	}

	/**
	 * gibt die Rechnungsadresse zurück.
	 */
	@Override
	public IAddress getInvoiceAddress() {
		return this.invoiceAddress;
	}

	/**
	 * gibt die Lieferadresse zurück.
	 */
	@Override
	public IAddress getDeliveryAddress() {
		return this.deliveryAddress;
	}

	/**
	 * Gibt die Anzahl der Auftragspositonen zurück.
	 */
	@Override
	public int countItems() {
		return this.items.size();
	}

	/**
	 * Gibt die Auftragspositionen als Set zurück.
	 */
	@Override
	public Set<Entry<String, IProductListItem>> getItemSet() {
		return this.items.entrySet();
	}

	/**
	 * Gibt den Gesamtpreis des Auftrags zurück.
	 */
	@Override
	public Double getFinalPrice() {
		return this.finalPrice;
	}
}
