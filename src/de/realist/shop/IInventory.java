/**
 * 
 */
package de.realist.shop;

import java.util.Map.Entry;
import java.util.Set;

import de.realist.shop.exceptions.ArticleNotFoundException;
import de.realist.shop.product.Product;

/**
 * @author flattman
 *
 */
public interface IInventory {
	/**
	 * Fügt eine neues Produkt hinzu.
	 * @param product
	 */
	public void addProduct(Product product);
	
	/**
	 * entfernt ein Produkt.
	 * @param sku
	 */
	public void removeProduct(String sku);
	
	/**
	 * Gibt ein Produkt zurück.
	 * @param sku
	 * @return
	 * @throws ArticleNotFoundException
	 */
	public Product getProduct(String sku) throws ArticleNotFoundException;
	
	/**
	 * Gibt das Inventory als Set zurück.
	 * @return
	 */
	public Set<Entry<String, Product>> getItemSet();
}
