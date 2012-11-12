package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class OpdrachtCatalogus extends FileContainer implements
		Iterable<Opdracht>, PersisteerbaarAlsTekst {

	// private ArrayList<Opdracht> opdrachten;
	private Hashtable<Integer, Opdracht> opdrachten;

	/**
	 * Default constructor.
	 */
	public OpdrachtCatalogus() {
		opdrachten = new Hashtable<Integer, Opdracht>();
	}

	/**
	 * Geeft een arraylist terug met alle opdrachten.
	 * 
	 * @return arraylist<Opdracht>
	 */
	public Hashtable<Integer, Opdracht> getOpdrachten() {
		return opdrachten;
	}

	private void setOpdrachten(Hashtable<Integer, Opdracht> opdrachten) {
		this.opdrachten = opdrachten;
	}

	/**
	 * Voeg een opdracht toe aan de catalogus. geeft een fout als de opdracht
	 * null is of reeds in de catalogus zit.
	 * 
	 * @param opdracht
	 *            Opdracht
	 */
	public void addOpdracht(Opdracht opdracht) throws IllegalArgumentException {
		if (opdracht == null) {
			throw new IllegalArgumentException("Opdracht is null.");
		}
		if (opdrachten.contains(opdracht)) {
			throw new IllegalArgumentException("Opdracht reeds in catalogus.");
		}

		int key = 0;

		try {
			key = Collections.max(opdrachten.keySet());
		} catch (Exception e) {
			key = 0;
		}

		key++;

		opdracht.setKey(key);

		opdrachten.put(key, opdracht);
	}

	/**
	 * Verwijder een opdracht uit de catalogus. Dit kan enkel als deze nog niet
	 * aan een quiz is gekoppeld.
	 * 
	 * @param opdracht
	 *            Opdracht die verwijderd moet worden
	 * @throws IllegalArgumentException
	 */
	public void verwijderOpdracht(Opdracht opdracht)
			throws IllegalArgumentException {
		if (!(opdrachten.contains(opdracht))) {
			throw new IllegalArgumentException(
					"Deze opdracht zit niet in deze catalogus.");
		}
		if (!(opdracht.getQuizOpdracten().isEmpty())) {
			throw new IllegalArgumentException(
					"Deze opdracht is aan een quiz gekoppeld.");
		}
		opdrachten.remove(opdracht.getKey());
	}

	@Override
	public void maakObjectVanLijn(String[] velden) {
		// TODO Auto-generated method stub

	}

	/**
	 * geeft een string met alle waarden van de meegegeven opdracht om deze te
	 * 
	 * @param o
	 * @return
	 */
	@Override
	public String MaakLijnVanObject(Object o) {
		if (!(o.getClass().equals(Opdracht.class))) {
			throw new IllegalArgumentException("Meegeleverde object moet een opdracht zijn");
		}
		
		Opdracht opdracht;
		String lijn = "";
		
		opdracht = (Opdracht) o;
		
		lijn = lijn + opdracht.getVraag() + 
		
		return lijn;
	}

	@Override
	public String getFile() {
		return "bestanden\\opdrachten.txt";
	}

	@Override
	public void toevoegenLijn(String lijn) {
		// TODO Auto-generated method stub

	}

	/**
	 * Schrijft de volledige catalogus weg in een textfile.
	 * 
	 * @throws Exception
	 * 
	 */
	@Override
	public void SchrijfCatalogusNaarFile() throws Exception {
		ArrayList<String> lijnen = new ArrayList<String>();

		for (Opdracht opdracht : opdrachten.values()) {
			lijnen.add(MaakLijnVanObject(opdracht));
		}

		try {
			schrijven(lijnen);
		} catch (Exception e) {
			throw new Exception("Probleem bij wegschrijven file0.");
		}
	}

	@Override
	public Iterator<Opdracht> iterator() {
		return opdrachten.values().iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((opdrachten == null) ? 0 : opdrachten.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpdrachtCatalogus other = (OpdrachtCatalogus) obj;
		if (opdrachten == null) {
			if (other.opdrachten != null)
				return false;
		} else if (!opdrachten.equals(other.opdrachten))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		OpdrachtCatalogus catalogus = new OpdrachtCatalogus();
		catalogus.setOpdrachten(opdrachten);
		return catalogus;
	}

	public static void main(String[] args) {

	}

}
