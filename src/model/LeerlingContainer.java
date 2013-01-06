package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class LeerlingContainer implements Iterable<Leerling> {

	private ArrayList<Leerling> leerlingen;

	/**
	 * Constructor
	 */
	public LeerlingContainer() {
		leerlingen = new ArrayList<Leerling>();
	}

	/**
	 * geeft een arraylist terug met leerlingen
	 * 
	 * @return the leerlingen
	 */
	public ArrayList<Leerling> getLeerlingen() {
		return leerlingen;
	}

	/**
	 * @param leerlingen
	 *            the leerlingen to set
	 */
	public void setLeerlingen(ArrayList<Leerling> leerlingen)
			throws IllegalArgumentException {
		if (leerlingen == null) {
			throw new IllegalArgumentException("Leerlingen is null.");
		}
		this.leerlingen = leerlingen;
	}

	/**
	 * Voegt een leerling toe aan de catalogus. Geeft een fout als deze reeds
	 * toegevoegd is.
	 * 
	 * @param leerling
	 * @throws IllegalArgumentException
	 */
	public void addLeerling(Leerling leerling) throws IllegalArgumentException {
		if (leerling == null) {
			throw new IllegalArgumentException("Leerling is null.");
		}
		if (leerlingen.contains(leerling)) {
			throw new IllegalArgumentException("Leerling bestaat reeds.");
		}
		leerlingen.add(leerling);
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
				+ ((leerlingen == null) ? 0 : leerlingen.hashCode());
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
		LeerlingContainer other = (LeerlingContainer) obj;
		if (leerlingen == null) {
			if (other.leerlingen != null)
				return false;
		} else if (!leerlingen.equals(other.leerlingen))
			return false;
		return true;
	}

	@Override
	public Iterator<Leerling> iterator() {
		return leerlingen.iterator();
	}

	private static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
