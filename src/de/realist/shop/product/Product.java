/**
 * 
 */
package de.realist.shop.product;

/**
 * @author flattman
 *
 */
public class Product implements IProduct {
	private String sku;
	private String name;
	private Double price;

	/**
	 * Erzeugt eine neues Produkt.
	 * @param sku
	 * @param name
	 * @param price
	 */
	public Product(String sku, String name, Double price) {
		this.sku = sku;
		this.name = name;
		this.price = price;
	}

	@Override
	public String getSku() {
		return this.sku;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Double getPrice() {
		return this.price;
	}
}
