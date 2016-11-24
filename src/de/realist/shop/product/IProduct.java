/**
 * 
 */
package de.realist.shop.product;

/**
 * @author flattman
 *
 */
public interface IProduct {
	/**
	 * Gibt die SKU zurück.
	 * @return
	 */
	public String getSku();
	
	/**
	 * Gibt den Namen zurück.
	 * @return
	 */
	public String getName();
	
	/**
	 * Gibt den Produktpreis zurück.
	 * @return
	 */
	public Double getPrice();
}
