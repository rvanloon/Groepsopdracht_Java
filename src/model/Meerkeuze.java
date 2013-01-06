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
	 * overeenkomt met het juiste antwoord. geeft een exception als het antwoord
	 * niet valide is.
	 */
	@Override
	public Boolean isJuisteAntwoord(String antwoord) {
		if (!(isValide(antwoord))) {
			throw new IllegalArgumentException(getValideertekst());
		}

		int antwoordNummer = Integer.parseInt(antwoord);
		String keuze = keuzes.get(antwoordNummer - 1);

		if (keuze.equals(getJuisteAntwoord())) {
			return true;
		}
		return false;
	}

	/**
	 * Kijkt na of het antwoord voldoet aan de juiste vorm.
	 */
	@Override
	public boolean isValide(String antwoord) {
		try {
			int antwoordNummer = Integer.parseInt(antwoord);
			@SuppressWarnings("unused")
			String keuze = keuzes.get(antwoordNummer - 1);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Geeft een tekst die tips hoe een geldig antwoord geformuleert moet
	 * worden.
	 */
	@Override
	public String getValideertekst() {
		return "Geef het nummer in behorende tot het juiste antwoord.";
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

	private static void main(String[] args) {
		Meerkeuze meerkeuze = new Meerkeuze("xxx", "aaa",
				OpdrachtCategorie.NederlandseTaal, Leraar.Sven, new Datum());
		meerkeuze.voegKeuzeToe("bbb");
		meerkeuze.voegKeuzeToe("ccc");
		meerkeuze.voegKeuzeToe("aaa");

		try {
			meerkeuze.isJuisteAntwoord("ccc");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
