package de.realist.shop.list;

import java.util.Set;
import java.util.Map.Entry;

import de.realist.shop.exceptions.ArticleNotFoundException;
import de.realist.shop.product.Product;

/**
 * ProductList Interface.
 * @author flattman
 *
 */
public interface IProductList {
	/**
	 * Fügt ein neues Produkt/Menge hinzu.
	 * @param quantity
	 * @param product
	 */
	public void addProduct(int quantity, Product product);
	
	/**
	 * Gibt ein bestimmtes Produkt mit der SKU zurück.
	 * @param sku
	 * @return
	 * @throws ArticleNotFoundException
	 */
	public IProductListItem getProduct(String sku) throws ArticleNotFoundException;
	
	/**
	 * Entfernt das Produkt mit der übergebenen SKU.
	 * @param sku
	 */
	public void removeProduct(String sku);
	
	/**
	 * Leert die Liste.
	 */
	public void clear();
	
	/**
	 * Gibt die Anzahl der Einträge zurück.
	 * @return
	 */
	public int countItems();
	
	/**
	 * Gibt die Items als Set zurück.
	 * @return
	 */
	public Set<Entry<String, IProductListItem>> getItemSet();
}
