/**
 * 
 */
package de.realist.shop.order;

/**
 * @author flattman
 *
 */
public interface IAddress {
	/**
	 * Gibt die Adresse formatiert zurück.
	 * @return
	 */
	public String getAddressString();
	
	/**
	 * Gibt den Namen zurück.
	 * @return
	 */
	public String getName();
	
	/**
	 * Gibt die Straße zurück.
	 * @return
	 */
	public String getStreet();
	
	/**
	 * Gibt die PLZ zurück.
	 * @return
	 */
	public String getZip();
	
	/**
	 * Gibt die Stadt zurück.
	 * @return
	 */
	public String getCity();
	
}
