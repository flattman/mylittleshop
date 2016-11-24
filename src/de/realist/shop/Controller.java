/**
 * 
 */
package de.realist.shop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import de.realist.shop.exceptions.ArticleNotFoundException;
import de.realist.shop.exceptions.EmptyBasketException;
import de.realist.shop.list.IProductList;
import de.realist.shop.list.IProductListItem;
import de.realist.shop.order.Address;
import de.realist.shop.order.DeliveryAddress;
import de.realist.shop.order.IAddress;
import de.realist.shop.order.IOrder;
import de.realist.shop.order.InvoiceAddress;
import de.realist.shop.product.Product;

/**
 * @author flattman
 * 
 */
public class Controller {
	/**
	 * Spaltenbreiten für die Darstellung von Warenkob/Auftrags-Positionen
	 */
	static final int TABLE_WIDTH_COL_SKU   = 12;
	static final int TABLE_WIDTH_COL_NAME  = 20;
	static final int TABLE_WIDTH_COL_PRICE =  6;
	static final int TABLE_WIDTH_COL_QTY   =  6;
	static final int TABLE_WIDTH_COL_SUM   =  6;
	
	static final String TABLE_HEAD_SKU     = "Artikelnr.";
	static final String TABLE_HEAD_NAME    = "Artikelname";
	static final String TABLE_HEAD_PRICE   = "Preis";
	static final String TABLE_HEAD_QTY     = "Menge";
	static final String TABLE_HEAD_SUM     = "Summe";
	
	static final String BOUNDARY           = "===========================";
	
	/**
	 * verwendeter Shop.
	 */
	static IShop shop;
	
	/**
	 * Input Reader zum lesen von Benutzereingaben.
	 */
	static BufferedReader br;
	
	static PrintStream out = System.out;
	
	/**
	 * hide constructor.
	 */
	private Controller() {
		throw new IllegalAccessError("Main Class");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		br = new BufferedReader(new InputStreamReader(System.in));

		AbstractShopFactory shopFactory = new MyShopFactory();
		shop = shopFactory.createShop();

		out.println("Willkommen in meinem kleinen Shop, kaufen Sie reichlich!");
		fillBasket();
		orderBasket();
	}

	/**
	 * Gibt das Inventory des Shops auf der Konsole aus.
	 */
	private static void printShopInventory() {
		out.println("Ich biete folgende Produkte:");
		out.println(padRight(TABLE_HEAD_SKU, TABLE_WIDTH_COL_SKU) + "\t"
				         + padRight(TABLE_HEAD_NAME, TABLE_WIDTH_COL_NAME) + "\t" 
				         + padLeft(TABLE_HEAD_PRICE, TABLE_WIDTH_COL_PRICE));

		IInventory inventory = shop.getInventory();

		for (Map.Entry<String, Product> entry : inventory.getItemSet()) {
			String sku = entry.getKey();
			Product product = entry.getValue();
			out.println(padRight(sku, TABLE_WIDTH_COL_SKU) + "\t"
					         + padRight(product.getName(), TABLE_WIDTH_COL_NAME) + "\t"
					         + padLeft(product.getPrice().toString(), TABLE_WIDTH_COL_PRICE) + " EUR");
		}
	}

	/**
	 * Gibt den Warenkorb des Shops auf der Konsole aus.
	 */
	private static void printBasket() {
		IProductList basket = shop.getBasket();

		out.println("Aktueller Warenkorb:");
		out.println(padRight(TABLE_HEAD_SKU, TABLE_WIDTH_COL_SKU) + "\t"
						 + padRight(TABLE_HEAD_NAME, TABLE_WIDTH_COL_NAME) + "\t" 
				         + padLeft(TABLE_HEAD_QTY, TABLE_WIDTH_COL_QTY) + "\t"
				         + padLeft(TABLE_HEAD_PRICE, TABLE_WIDTH_COL_PRICE) + "\t" 
				         + padLeft(TABLE_HEAD_SUM, TABLE_WIDTH_COL_SUM));

		for (Map.Entry<String, IProductListItem> entry : basket.getItemSet()) {
			String sku = entry.getKey();
			IProductListItem item = entry.getValue();

			out.println(padRight(sku, TABLE_WIDTH_COL_SKU) + "\t"
							 + padRight(item.getName(), TABLE_WIDTH_COL_NAME) + "\t"
							 + padLeft(Integer.toString(item.getQuantity()), TABLE_WIDTH_COL_QTY) + "\t"
							 + padLeft(item.getPrice().toString(), TABLE_WIDTH_COL_PRICE) + "\t"
							 + padLeft(item.getFinalPrice().toString(), TABLE_WIDTH_COL_SUM));
		}
	}

	/**
	 * Füllt den String s mit  n Leerzeichen nach rechts auf.
	 * @param s
	 * @param n
	 * @return
	 */
	private static String padRight(String s, int n) {
		return StringUtils.rightPad(s, n, " ");
	}

	/**
	 * Füllt den String s mit n Leerzeichen nach links auf.
	 * @param s
	 * @param n
	 * @return
	 */
	private static String padLeft(String s, int n) {
		return StringUtils.leftPad(s, n, " ");
	}

	/**
	 * Routine zur Befüllung des Warenkorbs über Benutzereingaben.
	 */
	private static void fillBasket() {
		String exitOrdering = "";

		do {
			printShopInventory();

			try {
				out.println("");
				out.print("Bitte SKU eingeben: ");
				String sku = br.readLine();

				out.println("");
				out.print("Bitte Menge angegen: ");
				int qty = Integer.parseInt(br.readLine());

				shop.addToBasket(sku, qty);
				out.println("Artikel " + sku + " " + qty + "x hinzugefügt.");

				out.println("");
				out.print("Bestellung beenden? tippe ja: ");
				exitOrdering = br.readLine();

			} catch (IOException e) {
				out.println("Error in input");
				out.println(e.getMessage());
			} catch (NumberFormatException e) {
				out.println("Invalid Format!");
			} catch (ArticleNotFoundException e) {
				out.println("Artikel existiert nicht");
			}

		} while (!"ja".equals(exitOrdering));
	}

	/**
	 * Routine zur Umwandlung des Baskets in einen Auftrag.
	 */
	private static void orderBasket() {
		printBasket();
		
		try {
			out.println("");
			out.println("Auftrag erstellen? tippe ja: ");
			String ok = br.readLine();
			
			if ("ja".equals(ok)) {
				IAddress invoiceAddress = getInvoiceAddress();
				IAddress deliveryAddress = getDeliveryAddress(invoiceAddress);
				
				IOrder order = shop.buyBasket(invoiceAddress, deliveryAddress);
				out.println("\n\n");
				out.println("Auftag angelegt");
				showOrder(order);
			} else {
				out.println("Auftrag nicht angelegt.");
			}
		} catch (IOException e) {
			out.println("Error in input");
			out.println(e.getMessage());
		} catch (EmptyBasketException e) {
			out.println("Der Warenkorb war leer, es wurde kein Auftrag erstellt.");
		}	
	}
	
	/**
	 * Zeigt einen Auftrag an.
	 * @param order
	 */
	private static void showOrder(IOrder order) {
		out.println(BOUNDARY);
		out.println("Rechnungsanschrift:\n" + order.getDeliveryAddress().getAddressString());
		
		out.println(BOUNDARY);
		out.println("Lieferanschrift:\n" + order.getDeliveryAddress().getAddressString());
		
		out.println(BOUNDARY);
		out.println("Bestellte Produkte:");
		out.println(padRight(TABLE_HEAD_SKU, 12) + "\t"
						 + padRight(TABLE_HEAD_NAME, 20) + "\t" + padLeft("Menge", 6) + "\t"
						 + padLeft(TABLE_HEAD_PRICE, 6) + "\t" + padLeft("Summe", 6));

		for (Map.Entry<String, IProductListItem> entry : order.getItemSet()) {
			String sku = entry.getKey();
			IProductListItem item = entry.getValue();

			out.println(padRight(sku, 12) + "\t"
							 + padRight(item.getName(), 20) + "\t"
							 + padLeft(Integer.toString(item.getQuantity()), 6) + "\t"
							 + padLeft(item.getPrice().toString(), 6) + "\t"
							 + padLeft(item.getFinalPrice().toString(), 6));
		}
		
		out.println(BOUNDARY);
		out.println("Gesamtpreis: " + order.getFinalPrice() + " EUR");
		out.println("Vielen Dank für Ihren Auftrag, Bye!");
	}
	
	/**
	 * Fragt beim Benutzer die Lieferadresse ab und gibt diese zurück.
	 * @param invoiceAddress
	 * @return
	 */
	private static DeliveryAddress getDeliveryAddress(IAddress invoiceAddress) {
		out.println("Lieferanschrift eingeben");
		
		out.println("Rechnungsanschrift verwenden? tippe ja");
		
		String ans = "";
		boolean retry;
		do {
			retry = false;
			try {
				ans = br.readLine();
			} catch (IOException e1) {
				out.println("retry!");
				retry = true;
			}
		} while (retry);
		
		if ("ja".equals(ans)) {
			return new DeliveryAddress(invoiceAddress);
		}
		
		IAddress addr = askForAddress();
		
		return new DeliveryAddress(addr);
	}
	
	/**
	 * Fragt beim Benutzer die Rechnungsanschrift ab und gibt diese zurück.
	 * @return
	 */
	private static InvoiceAddress getInvoiceAddress() {
		out.println("Rechnungsanschrift eingeben");

		IAddress addr = askForAddress();
		
		return new InvoiceAddress(addr);
	}
	
	/**
	 * Addressabfrage / eingabe.
	 * @return
	 */
	private static IAddress askForAddress() {
		String name = "";
		String street = "";
		String zip = "";
		String city = "";
		
		boolean retry;
		do {
			try {
				out.print("Name: ");
				name = br.readLine();
				
				out.print("Straße: ");
				street = br.readLine();
				
				out.print("PLZ: ");
				zip = br.readLine();
				
				out.print("Ort: ");
				city = br.readLine();
				
				retry = false;
			} catch (IOException e) {
				out.println("Error in input, try again");
				out.println(e.getMessage());
				retry = true;
			}
		} while (retry);
		
		return new Address(name, street, zip, city);
	}
}
