/**
 * 
 */
package de.realist.shop;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import de.realist.shop.exceptions.ArticleNotFoundException;
import de.realist.shop.product.Product;

/**
 * @author flattman
 *
 */
public class MyProductInventory implements IInventory {
	private Map<String, Product> inventory = new HashMap<>();

	/**
	 * FÜgt dem Inventroy ein Produkt hinzu.
	 */
	@Override
	public void addProduct(Product product) {
		this.inventory.put(product.getSku(), product);
	}
	
	/**
	 * Entfern ein Produkt aus dem Inventory.
	 */
	@Override
	public void removeProduct(String sku) {
		this.inventory.remove(sku);
	}

	/**
	 * Gibt ein bestimmtes Produkt des Inventory zurück.
	 */
	@Override
	public Product getProduct(String sku) throws ArticleNotFoundException {
		if (!this.inventory.containsKey(sku)) {
			throw new ArticleNotFoundException(sku);
		}
		
		return this.inventory.get(sku);
	}

	/**
	 * Gibt das Inventory als Set zurück.
	 */
	@Override
	public Set<Entry<String, Product>> getItemSet() {
		return this.inventory.entrySet();
	}
}