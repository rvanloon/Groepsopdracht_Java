package model;

import java.util.List;

import utils.Datum;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class Opdracht implements Comparable<Opdracht> {

	private String vraag;
	private String juisteAntwoord;
	private int maxAantalPogingen;
	private List<String> antwoordHints;
	private int maxAntwoordTijd = -1;

	private OpdrachtCategorie categorie;
	private List<QuizOpdracht> quizzen;
	private Leraar auteur;
	private Datum datumRegistratie;

	public String getVraag() {
		return vraag;
	}

	public void setVraag(String vraag) {
		this.vraag = vraag;
	}

	public String getJuisteAntwoord() {
		return juisteAntwoord;
	}

	public void setJuisteAntwoord(String juisteAntwoord) {
		this.juisteAntwoord = juisteAntwoord;
	}

	public int getMaxAantalPogingen() {
		return maxAantalPogingen;
	}

	public void setMaxAantalPogingen(int maxAantalPogingen) {
		this.maxAantalPogingen = maxAantalPogingen;
	}

	public List<String> getAntwoordHints() {
		return antwoordHints;
	}

	public void setAntwoordHints(List<String> antwoordHints) {
		this.antwoordHints = antwoordHints;
	}

	public int getMaxAntwoordTijd() {
		return maxAntwoordTijd;
	}

	public void setMaxAntwoordTijd(int maxAntwoordTijd) {
		this.maxAntwoordTijd = maxAntwoordTijd;
	}

	public OpdrachtCategorie getCategorie() {
		return categorie;
	}

	public void setCategorie(OpdrachtCategorie categorie) {
		this.categorie = categorie;
	}

	public List<QuizOpdracht> getQuizzen() {
		return quizzen;
	}

	public void setQuizzen(List<QuizOpdracht> quizzen) {
		this.quizzen = quizzen;
	}

	public Leraar getAuteur() {
		return auteur;
	}

	public void setAuteur(Leraar auteur) {
		this.auteur = auteur;
	}

	public Datum getDatumRegistratie() {
		return datumRegistratie;
	}

	public void setDatumRegistratie(Datum datumRegistratie) {
		this.datumRegistratie = datumRegistratie;
	}

	/**
	 * Geeft true als het gegeven antwoord overeenkomt met het juiste antwoord.
	 * Anders wordt false teruggegeven
	 * 
	 * @param antwoord
	 *            een string die het te testen antwoord bevat.
	 * @return boolean
	 */
	public Boolean isJuisteAntwoord(String antwoord) {
		return antwoord.equals(juisteAntwoord);
	}

	/**
	 * Berekent een hashcode op basis van categorie, vraag en juisteAntwoord.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categorie == null) ? 0 : categorie.hashCode());
		result = prime * result
				+ ((juisteAntwoord == null) ? 0 : juisteAntwoord.hashCode());
		result = prime * result + ((vraag == null) ? 0 : vraag.hashCode());
		return result;
	}

	/**
	 * Houd rekening met categorie, vraag en juisteAntwoord.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Opdracht other = (Opdracht) obj;
		if (categorie != other.categorie)
			return false;
		if (juisteAntwoord == null) {
			if (other.juisteAntwoord != null)
				return false;
		} else if (!juisteAntwoord.equals(other.juisteAntwoord))
			return false;
		if (vraag == null) {
			if (other.vraag != null)
				return false;
		} else if (!vraag.equals(other.vraag))
			return false;
		return true;
	}

	/**
	 * Geeft een kopie van de huidige opdracht.
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Opdracht clone = new Opdracht();
		clone.setAntwoordHints(antwoordHints);
		clone.setAuteur(auteur);
		clone.setCategorie(categorie);
		clone.setDatumRegistratie(datumRegistratie);
		clone.setJuisteAntwoord(juisteAntwoord);
		clone.setMaxAantalPogingen(maxAantalPogingen);
		clone.setMaxAntwoordTijd(maxAntwoordTijd);
		clone.setQuizzen(quizzen);
		clone.setVraag(vraag);
		return clone;
	}

	/**
	 * Geeft de vraag terug.
	 */
	@Override
	public String toString() {
		return vraag;
	}

	/**
	 * Vergelijkt een opdracht op basis van de vraag (alfabetisch)
	 * 
	 * @param opdracht
	 *            De opdracht die vergeleken moet worden.
	 * @return 0: gelijk, negatieve waarde: kleiner, positieve waarde: groter
	 */
	@Override
	public int compareTo(Opdracht opdracht) {
		if (opdracht == null) {
			throw new IllegalArgumentException("opdracht is null");
		}
		return opdracht.vraag.compareTo(vraag);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
