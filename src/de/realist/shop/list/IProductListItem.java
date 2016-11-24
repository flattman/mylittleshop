/**
 * 
 */
package de.realist.shop.list;

import de.realist.shop.product.Product;

/**
 * @author flattman
 *
 */
public interface IProductListItem {
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
	 * Gibt die Menge zurück.
	 * @return
	 */
	public int getQuantity();
	
	/**
	 * Gibt den Gesamtpreis zurück.
	 * @return
	 */
	public Double getFinalPrice();
	
	/**
	 * Gibt den Einzelpreis zurück.
	 * @return
	 */
	public Double getPrice();
	
	/**
	 * Gibt das Produkt zurück.
	 * @return
	 */
	public Product getProduct();
	
	/**
	 * Addiert die Menge des item bei gleichem Produkt hinzu.
	 * @param item
	 */
	public void add(IProductListItem item);
	
	/**
	 * Gibt an ob das Produkt von item gleich dem eigenen ist.
	 * @param item
	 * @return
	 */
	public boolean isSameProduct(IProductListItem item);
}
