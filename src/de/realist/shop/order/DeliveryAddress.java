/**
 * 
 */
package de.realist.shop.order;

/**
 * @author flattman
 *
 */
public class DeliveryAddress extends Address {
	/**
	 * Erzeugt eine neue Lieferadresse anhand von Einzelnangaben.
	 * @param name
	 * @param street
	 * @param zip
	 * @param city
	 */
	public DeliveryAddress(String name, String street, String zip, String city) {
		super(name, street, zip, city);
	}
	
	/**
	 * Erzeugt eine neue Lieferadresse anhand einer anderen Adresse.
	 * @param address
	 */
	public DeliveryAddress(IAddress address) {
		super(address);
	}
}