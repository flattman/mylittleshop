/**
 * 
 */
package de.realist.shop;

import de.realist.shop.product.Product;

/**
 * @author flattman
 *
 */
public class MyShopFactory extends AbstractShopFactory {

	@Override
	/**
	 * Erzeugt einen neuen Shop mit einem Test-Inventory.
	 */
	public MyShop createShop() {
		IInventory inventory = this.createDummyInventory();
		
		return new MyShop(inventory);
	}

	/**
	 * Erzeugt ein neues Test-Inventory und gibt diese zurück.
	 * @return
	 */
	private MyProductInventory createDummyInventory() {
		MyProductInventory inventory = new MyProductInventory();
		
		Product product1 = new Product("4507010001", "Weißkohl", 2.99);
		inventory.addProduct(product1);
		
		Product product2 = new Product("4507010002", "Kohlrabi", 0.79);
		inventory.addProduct(product2);
		
		Product product3 = new Product("4507010003", "Banane", 0.55);
		inventory.addProduct(product3);
		
		Product product4 = new Product("4507010004", "Melone", 5.99);
		inventory.addProduct(product4);
		
		return inventory;
	}
}
