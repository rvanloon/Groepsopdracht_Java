package model;

import java.util.ArrayList;

import utils.Datum;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class Reproductie extends Opdracht {

	private ArrayList<String> trefwoorden;
	private int minAantalJuisteTrefwoorden;

	/**
	 * Constructor. Zet de parameter 'juiste antwword' van de constructor van
	 * opdracht op "ZIE TREFWOORDEN" omdat deze hier niet relevant is.
	 * 
	 * @param vraag
	 * @param categorie
	 * @param auteur
	 * @param datumRegistratie
	 */
	public Reproductie(String vraag, OpdrachtCategorie categorie,
			Leraar auteur, Datum datumRegistratie,
			int minAantalJuisteTrefwoorden) {
		super(vraag, "ZIE TREFWOORDEN", categorie, auteur, datumRegistratie);

		setMinAantalJuisteTrefwoorden(minAantalJuisteTrefwoorden);
		trefwoorden = new ArrayList<String>();
	}

	/**
	 * @return the trefwoorden
	 */
	public ArrayList<String> getTrefwoorden() {
		return trefwoorden;
	}

	/**
	 * @param trefwoorden
	 *            the trefwoorden to set
	 */
	private void setTrefwoorden(ArrayList<String> trefwoorden) {
		if (trefwoorden == null) {
			throw new IllegalArgumentException("trefwoorden is null");
		}
		this.trefwoorden = trefwoorden;
	}

	/**
	 * @return the minAantalJuisteTrefwoorden
	 */
	public int getMinAantalJuisteTrefwoorden() {
		return minAantalJuisteTrefwoorden;
	}

	/**
	 * Het min. aantal trefwoorden die in het antwoord moeten voorkomen.
	 * 
	 * @param minAantalJuisteTrefwoorden
	 *            the minAantalJuisteTrefwoorden to set
	 */
	public void setMinAantalJuisteTrefwoorden(int minAantalJuisteTrefwoorden) {
		if (minAantalJuisteTrefwoorden < 1) {
			throw new IllegalArgumentException(
					"minAantalJuisteTrefwoorden moet minstens één zijn.");
		}
		this.minAantalJuisteTrefwoorden = minAantalJuisteTrefwoorden;
	}

	/**
	 * Voeg een trefwoord toe
	 * 
	 * @param trefwoord
	 *            String
	 * @throws IllegalArgumentException
	 */
	public void VoegTrefwoordToe(String trefwoord)
			throws IllegalArgumentException {
		if (trefwoord == null || trefwoord.isEmpty()) {
			throw new IllegalArgumentException("trefwoord is leeg of null");
		}
		if (trefwoorden.contains(trefwoord)) {
			throw new IllegalArgumentException("trefwoord is reeds toegevoegd");
		}

		trefwoorden.add(trefwoord);
	}

	/**
	 * Verwijder een trefwoord.
	 * 
	 * @param trefwoord
	 * @throws IllegalArgumentException
	 */
	public void VerwijderTrefwoord(String trefwoord)
			throws IllegalArgumentException {
		if (trefwoord == null || trefwoord.isEmpty()) {
			throw new IllegalArgumentException("trefwoord is leeg of null");
		}
		if (!(trefwoorden.contains(trefwoord))) {
			throw new IllegalArgumentException("trefwoord bestaat niet");
		}

		trefwoorden.remove(trefwoord);
	}

	/*
	 * Geeft true als het antwoord het minimum aantal trefwoorden bevat. Anders
	 * false.
	 * 
	 * @see model.Opdracht#isJuisteAntwoord(java.lang.String)
	 */
	@Override
	public Boolean isJuisteAntwoord(String antwoord) {
		if (antwoord == null || antwoord.isEmpty()) {
			throw new IllegalArgumentException("antwoord is leeg of null");
		}

		int aantalTrefwoorden = 0;
		for (String trefwoord : trefwoorden) {
			if (antwoord.contains(trefwoord)) {
				aantalTrefwoorden++;
			}
		}

		return minAantalJuisteTrefwoorden > aantalTrefwoorden ? false : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + minAantalJuisteTrefwoorden;
		result = prime * result
				+ ((trefwoorden == null) ? 0 : trefwoorden.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reproductie other = (Reproductie) obj;
		if (minAantalJuisteTrefwoorden != other.minAantalJuisteTrefwoorden)
			return false;
		if (trefwoorden == null) {
			if (other.trefwoorden != null)
				return false;
		} else if (!trefwoorden.equals(other.trefwoorden))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Opdracht#clone()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object clone() throws CloneNotSupportedException {
		Reproductie clone = new Reproductie(getVraag(), getCategorie(),
				getAuteur(), getDatumRegistratie(),
				getMinAantalJuisteTrefwoorden());
		clone.setMaxAantalPogingen(getMaxAantalPogingen());
		clone.setMaxAntwoordTijd(getMaxAntwoordTijd());
		clone.setTrefwoorden((ArrayList<String>) getTrefwoorden().clone());
		clone.setAntwoordHints((ArrayList<String>) getAntwoordHints().clone());

		return clone;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Opdracht#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Opdracht#compareTo(model.Opdracht)
	 */
	@Override
	public int compareTo(Opdracht opdracht) {
		return super.compareTo(opdracht);
	}

	/**
	 * @param args
	 */
	private static void main(String[] args) {

	}

}
