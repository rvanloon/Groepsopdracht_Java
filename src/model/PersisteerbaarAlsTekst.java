/**
 * 
 */
package model;

/**
 * @author rvanloon
 *
 */
public interface PersisteerbaarAlsTekst {
	/**
	 * Maakt een object aan, aan de had van een array van strings
	 * @param String array velden
	 */
	void maakObjectVanLijn(String[]velden);
	
	/**
	 * Maakt een stringvoorstelling van het meegeleverde object
	 * @param Object o
	 * @return String 
	 */
	String MaakLijnVanObject(Object o);
}
