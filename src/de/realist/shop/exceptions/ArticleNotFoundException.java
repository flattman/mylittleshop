/**
 * 
 */
package de.realist.shop.exceptions;

/**
 * Exception für einen nciht gefunden Artikel.
 * @author flattman
 */
public class ArticleNotFoundException extends Exception {
	/**
	 * serial number
	 */
	private static final long serialVersionUID = 5402021603699734079L;
	protected final String sku;

	/**
	 * Erzeugt eine neue Exception.
	 * @param sku
	 */
	public ArticleNotFoundException(String sku) {
		super();
		this.sku = sku;
	}
}