/**
 * 
 */
package de.realist.shop;

/**
 * @author flattman
 *
 */
public abstract class AbstractShopFactory {
	/**
	 * erzeugt einen neuen Shop.
	 * @return
	 */
	public abstract IShop createShop();
}
