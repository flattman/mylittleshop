/**
 * 
 */
package de.realist.shop.list;

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
public class Basket implements IProductList {
	/**
	 * Enthält die BasketItems.
	 */
	private Map<String, IProductListItem> items = new HashMap<>();
	
	/**
	 * Fügt ein neues Produkt mit der entsprechenden Menge hinzu.
	 */
	@Override
	public void addProduct(int quantity, Product product) {
		IProductListItem item = new BasketItem(quantity, product);
		
		if (this.items.containsKey(product.getSku())) {
			IProductListItem oldItem = this.items.get(product.getSku());
			item.add(oldItem);
		}
		
		this.items.put(product.getSku(), item);
	}

	/**
	 * Entfernt ein Produkt aus dem Basket.
	 */
	@Override
	public void removeProduct(String sku) {
		this.items.remove(sku);
	}

	/**
	 * leer den Basket.
	 */
	@Override
	public void clear() {
		this.items.clear();
	}

	/**
	 * Gibt die Items als Set zurück.
	 */
	@Override
	public Set<Entry<String, IProductListItem>> getItemSet() {
		return items.entrySet();
	}
	
	@Override
	public int countItems() {
		return this.items.size();
	}
	
	/**
	 * Gibt ein bestimmtes Item zurück.
	 */
	@Override
	public IProductListItem getProduct(String sku) throws ArticleNotFoundException {
		if (!this.items.containsKey(sku)) {
			throw new ArticleNotFoundException(sku);
		}
		
		return this.items.get(sku);
	}
}
