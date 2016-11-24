/**
 * 
 */
package de.realist.shop.order;

/**
 * @author flattman
 */
public class InvoiceAddress extends Address {

	/**
	 * Erzeugt eine neue Rechnungsadresse anhand von Einzelnangaben.
	 * @param name
	 * @param street
	 * @param zip
	 * @param city
	 */
	public InvoiceAddress(String name, String street, String zip, String city) {
		super(name, street, zip, city);
	}

	/**
	 * Erzeugt eine neue Rechnungsadresse anhand einer anderen Adresse.
	 * @param address
	 */
	public InvoiceAddress(IAddress address) {
		super(address);
	}
}
