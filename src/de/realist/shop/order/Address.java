/**
 * 
 */
package de.realist.shop.order;

/**
 * @author flattman
 *
 */
public class Address implements IAddress {
	protected String name;
	protected String street;
	protected String zip;
	protected String city;
	

	/**
	 * Erzeugt eine einfache Adresse anhand der übergebenen Daten.
	 * @param name
	 * @param street
	 * @param zip
	 * @param city
	 */
	public Address(String name, String street, String zip, String city) {
		this.name = name;
		this.street = street;
		this.zip = zip;
		this.city = city;
	}
	
	/**
	 * Erzeugt anhand einer bestehenden Adresse eine neue Adresse.
	 * @param address
	 */
	public Address(IAddress address) {
		this.name = address.getName();
		this.street = address.getStreet();
		this.zip = address.getZip();
		this.city = address.getCity();
	}

	/**
	 * Gibt die Adresse als formatierten String zurück.
	 */
	@Override
	public String getAddressString() {
		return name + "\n" +
	           street + "\n" +
			   zip + " " + city;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getStreet() {
		return street;
	}

	@Override
	public String getZip() {
		return zip;
	}

	@Override
	public String getCity() {
		return city;
	}
}
