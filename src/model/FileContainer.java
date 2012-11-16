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

	public void lezen() {
		try {
			File file = new File(getFile());
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String lijn = null;
			while (!(lijn = reader.readLine()).equals("EINDE")) {
				toevoegenLijn(lijn);
			}
			reader.close();
		} catch (Exception ex) {
		}
	}

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

	public abstract String getFile();

	public abstract void toevoegenLijn(String lijn);
	
	public abstract void schrijfCatalogusNaarFile() throws Exception;

}
