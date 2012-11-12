/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import utils.Datum;

/**
 * @author rvanloon
 * @version 1
 * 
 */
public class Opsomming extends Opdracht implements Valideerbaar {

	private boolean inJuisteVolgorde;

	/**
	 * @param vraag
	 * @param juisteAntwoord
	 * @param categorie
	 * @param auteur
	 * @param datumRegistratie
	 */
	public Opsomming(String vraag, String juisteAntwoord,
			boolean inJuisteVolgorde, OpdrachtCategorie categorie,
			Leraar auteur, Datum datumRegistratie) {
		super(vraag, juisteAntwoord, categorie, auteur, datumRegistratie);

		setInJuisteVolgorde(inJuisteVolgorde);
	}

	/**
	 * @return the inJuisteVolgorde
	 */
	public boolean isInJuisteVolgorde() {
		return inJuisteVolgorde;
	}

	/**
	 * @param inJuisteVolgorde
	 *            the inJuisteVolgorde to set
	 */
	public void setInJuisteVolgorde(boolean inJuisteVolgorde) {
		this.inJuisteVolgorde = inJuisteVolgorde;
	}

	/**
	 * Valideert het juiste antwoord alvorens dit toe te voegen.
	 */
	@Override
	public void setJuisteAntwoord(String juisteAntwoord) {
		if (!(isValide(juisteAntwoord))) {
			throw new IllegalArgumentException(getValideertekst());
		}
		super.setJuisteAntwoord(juisteAntwoord);
	}

	/**
	 * Geeft aan of een antwoord juist of fout is. Geeft een exception als het
	 * antwoord niet valide is.
	 */
	@Override
	public Boolean isJuisteAntwoord(String antwoord) {
		if (!(isValide(antwoord))) {
			throw new IllegalArgumentException(getValideertekst());
		}

		String[] antwoorden = antwoord.split(";");
		String[] juisteAntwoorden = getJuisteAntwoord().split(";");

		if (antwoorden.length != juisteAntwoorden.length)
			return false;

		if (isInJuisteVolgorde()) {
			for (int i = 0; i < juisteAntwoorden.length; i++) {
				if (!(antwoorden[i].equals(juisteAntwoorden[i])))
					return false;
			}
		}

		else {
			ArrayList<String> temp = new ArrayList<String>(
					Arrays.asList(juisteAntwoorden));
			for (int i = 0; i < antwoorden.length; i++) {
				if (!(temp.contains(antwoorden[i]))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Kijkt na of het antwoord voldoet aan de juiste vorm.
	 */
	@Override
	public boolean isValide(String antwoord) {
		String[] antwoorden;
		try {
			antwoorden = antwoord.split(";");
		} catch (Exception e) {
			return false;
		}
		if (antwoorden.length > 1) {
			return true;
		}
		return false;
	}

	/**
	 * Geeft een tekst die tips hoe een geldig antwoord geformuleert moet
	 * worden.
	 */
	@Override
	public String getValideertekst() {
		return "Typ je antwoorden achter elkaar gescheiden door ;";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Opdracht#clone()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object clone() throws CloneNotSupportedException {
		Opsomming clone = new Opsomming(getVraag(), getJuisteAntwoord(),
				isInJuisteVolgorde(), getCategorie(), getAuteur(),
				getDatumRegistratie());
		clone.setMaxAantalPogingen(getMaxAantalPogingen());
		clone.setMaxAntwoordTijd(getMaxAntwoordTijd());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (inJuisteVolgorde ? 1231 : 1237);
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
		Opsomming other = (Opsomming) obj;
		if (inJuisteVolgorde != other.inJuisteVolgorde)
			return false;
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Opsomming opsomming = new Opsomming("xxx", "aaa;bbb;ccc", true,
				OpdrachtCategorie.rekenen, Leraar.Robrecht, new Datum());
		System.out.println(opsomming.isJuisteAntwoord("aaa-bbb-ccc"));
	}

}
