/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Formatter;

/**
 * @author rvanloon
 * 
 */
public abstract class FileContainer {

	public static final String splitteken = "#";

	/**
	 * Leest de lijnen uit een textfile en geeft deze door voor omzetting naar
	 * objecten.
	 * 
	 * @throws Exception
	 */
	public void lezen() throws Exception {
		try {
			File file = new File(getFile());
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String lijn = null;
			while (!(lijn = reader.readLine()).equals("EINDE")) {
				toevoegenLijn(lijn);
			}
			reader.close();
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * Schrijft een arraylist van lijnen weg naar een textfile
	 * 
	 * @param lijnen
	 * @throws Exception
	 */
	public void schrijven(ArrayList<String> lijnen) throws Exception {
		Formatter schrijver = null;

		try {
			File file = new File(getFile());
			// open file
			schrijver = new Formatter(file);

			// records schrijven
			for (String s : lijnen) {
				schrijver.format("%s%n", s);
			}
			// "EINDE" toevoegen
			schrijver.format("%s", "EINDE");
		} catch (SecurityException ex) {
			throw new Exception("Geen schrijftoegeang tot bestand");
		} catch (FileNotFoundException ex) {
			throw new Exception("Kan bestand niet openen");
		} finally {
			// sluit schrijver
			if (schrijver != null) {
				schrijver.close();
			}
		}
	}

	/**
	 * Geeft de locatie van de textfile waar de objecten worden weggeschreven
	 * 
	 * @return String lijn
	 */
	public abstract String getFile();

	/**
	 * Maakt een object aan aan de hand van de meegeleverde string en voert deze
	 * toe aan de catalogus
	 * 
	 * @param String
	 *            lijn
	 */
	public abstract void toevoegenLijn(String lijn);

	/**
	 * Schrijft de inhoud van een catalogus weg naar een file
	 * 
	 * @throws Exception
	 */
	public abstract void schrijfCatalogusNaarFile() throws Exception;

}
