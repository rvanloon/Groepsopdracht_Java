package model;

import java.util.ArrayList;

import utils.Datum;

/**
 * @author rvanloon
 * @version 1
 * 
 */
public class Meerkeuze extends Opdracht implements Valideerbaar {

	private ArrayList<String> keuzes;

	public Meerkeuze(String vraag, String juisteAntwoord,
			OpdrachtCategorie categorie, Leraar auteur, Datum datumRegistratie) {
		super(vraag, juisteAntwoord, categorie, auteur, datumRegistratie);
		keuzes = new ArrayList<String>();
	}

	/**
	 * @return the keuzes
	 */
	public ArrayList<String> getKeuzes() {
		return keuzes;
	}

	/**
	 * @param keuzes
	 *            the keuzes to set
	 */
	public void setKeuzes(ArrayList<String> keuzes) {
		if (keuzes == null) {
			throw new IllegalArgumentException("keuzes is null");
		}
		this.keuzes = keuzes;
	}

	/**
	 * Voeg een nieuwe keuze toe aan de keuzelijst.
	 * 
	 * @param keuze
	 */
	public void voegKeuzeToe(String keuze) {
		if (keuze == null || keuze.isEmpty()) {
			throw new IllegalArgumentException("geen geldige keuze");
		}
		if (keuzes.contains(keuze)) {
			throw new IllegalArgumentException("keuze reeds toegevoegd");
		}
		keuzes.add(keuze);
	}

	/**
	 * Verwijder een keuze uit de lijst.
	 * 
	 * @param keuze
	 */
	public void verwijderKeuze(String keuze) {
		if (keuze == null || keuze.isEmpty()) {
			throw new IllegalArgumentException("geen geldige keuze");
		}
		if (!(keuzes.contains(keuze))) {
			throw new IllegalArgumentException("keuze zit niet in de lijst");
		}
		keuzes.remove(keuze);
	}

	/**
	 * Geeft aan of een antwoord juist of fout is. Kijkt of het ingegeven nummer
	 * overeenkomt met het juiste antwoord.
	 */
	@Override
	public Boolean isJuisteAntwoord(String antwoord) {
		Vervangen door valideer.		
		return true;
	}

	@Override
	public boolean isValide(String antwoord) {
		try {
			int antwoordNummer = Integer.parseInt(antwoord);
			String keuze = keuzes.get(antwoordNummer - 1);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public String getValideertekst() {
		return "Geef het nummer in behorende tot hzet juiste antwoord.";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Opdracht#clone()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object clone() throws CloneNotSupportedException {
		Meerkeuze clone = new Meerkeuze(getVraag(), getJuisteAntwoord(),
				getCategorie(), getAuteur(), getDatumRegistratie());
		clone.setMaxAantalPogingen(getMaxAantalPogingen());
		clone.setMaxAntwoordTijd(getMaxAntwoordTijd());
		clone.setKeuzes((ArrayList<String>) getKeuzes().clone());

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
		result = prime * result + ((keuzes == null) ? 0 : keuzes.hashCode());
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
		Meerkeuze other = (Meerkeuze) obj;
		if (keuzes == null) {
			if (other.keuzes != null)
				return false;
		} else if (!keuzes.equals(other.keuzes))
			return false;
		return true;
	}

	public static void main(String[] args) {

	}

}
