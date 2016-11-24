/**
 * 
 */
package de.realist.shop.list;

import de.realist.shop.product.Product;

/**
 * @author flattman
 *
 */
public class BasketItem implements IProductListItem {
	/**
	 * Artikelnummer
	 */
	protected String sku;
	
	/**
	 * Produktname
	 */
	protected String name;
	
	/**
	 * Menge 
	 */
	protected int quantity;
	
	/**
	 * Einzelpreis
	 */
	protected double price;
	
	/**
	 * Das Produkt selbst
	 */
	protected Product product;
	
	
	/**
	 * Erzeugt ein neues BasketItem aus einem Produkt und der Menge.
	 * @param quantity gewünschte Menge
	 * @param product gewünschtes Produkt
	 */
	public BasketItem(int quantity, Product product) {
		this.sku = product.getSku();
		this.name = product.getName();
		this.quantity = quantity;
		this.price = product.getPrice();
		this.product = product;
	}
	
	/**
	 * Erzeugt aus einem übergebenen ProductListItem ein neues BasketItem.
	 * @param item
	 */
	public BasketItem(IProductListItem item) {
		this.sku = item.getSku();
		this.name = item.getName();
		this.quantity = item.getQuantity();
		this.price = item.getPrice();
		this.product = item.getProduct();
	}

	/**
	 * Gibt den Gesamtpreis des Items zurück.
	 */
	@Override
	public Double getFinalPrice() {
		return this.price * this.quantity;
	}

	@Override
	public Double getPrice() {
		return this.price;
	}

	@Override
	public Product getProduct() {
		return this.product;
	}

	@Override
	public int getQuantity() {
		return this.quantity;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getSku() {
		return this.sku;
	}

	/**
	 * Fügt dem Item eine weitere Menge hinzu.
	 */
	@Override
	public void add(IProductListItem item) {
		if (item.isSameProduct(this)) {
			this.quantity += item.getQuantity();
		}
	}

	/**
	 * Gibt an ob das übergebenen Item das gleiche Produkt enthält wie dieses.
	 */
	@Override
	public boolean isSameProduct(IProductListItem item) {
		return this.getSku().equals(item.getSku());	
	}
}
