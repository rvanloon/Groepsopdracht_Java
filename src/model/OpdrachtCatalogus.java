package model;

import java.util.ArrayList;
import java.util.Iterator;

import com.sun.org.apache.xml.internal.resolver.Catalog;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class OpdrachtCatalogus implements Iterable<Opdracht> {

	private ArrayList<Opdracht> opdrachten;

	/**
	 * Default constructor.
	 */
	public OpdrachtCatalogus() {
		opdrachten = new ArrayList<Opdracht>();
	}

	/**
	 * Geeft een arraylist terug met alle opdrachten.
	 * 
	 * @return arraylist<Opdracht>
	 */
	public ArrayList<Opdracht> getOpdrachten() {
		return opdrachten;
	}

	private void setOpdrachten(ArrayList<Opdracht> opdrachten) {
		this.opdrachten = opdrachten;
	}

	/**
	 * Voeg een opdracht toe aan de catalogus. geeft een fout als de opdracht
	 * null is of reeds in de catalogus zit.
	 * 
	 * @param opdracht
	 *            Opdracht
	 */
	public void addOpdracht(Opdracht opdracht) {
		if (opdracht == null) {
			throw new IllegalArgumentException("Opdracht is null.");
		}
		if (opdrachten.contains(opdracht)) {
			throw new IllegalArgumentException("Opdracht reeds in catalogus.");
		}
		opdrachten.add(opdracht);
	}

	@Override
	public Iterator<Opdracht> iterator() {
		return opdrachten.iterator();
	}

	/* (non-Javadoc)
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

	/* (non-Javadoc)
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

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		OpdrachtCatalogus catalogus = new OpdrachtCatalogus();
		catalogus.setOpdrachten(opdrachten);
		return catalogus;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
