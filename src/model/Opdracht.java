package model;

import java.util.ArrayList;

import org.junit.Before;

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
	private int maxAantalPogingen = 1;
	private ArrayList<String> antwoordHints;
	private int maxAntwoordTijd = 0;
	private OpdrachtCategorie categorie;
	private ArrayList<QuizOpdracht> quizzen;
	private Leraar auteur;
	private Datum datumRegistratie;

	/**
	 * Constructor. Maakt een nieuwe opdracht aan.
	 * 
	 * @param vraag
	 * @param juisteAntwoord
	 * @param categorie
	 * @param auteur
	 * @param datumRegistratie
	 */
	public Opdracht(String vraag, String juisteAntwoord,
			OpdrachtCategorie categorie, Leraar auteur, Datum datumRegistratie) {
		this.setVraag(vraag);
		this.setJuisteAntwoord(juisteAntwoord);
		this.setCategorie(categorie);
		this.setAuteur(auteur);
		this.setDatumRegistratie(datumRegistratie);

		this.antwoordHints = new ArrayList<String>();
		this.quizzen = new ArrayList<QuizOpdracht>();
	}

	/**
	 * Geeft de vraag terug.
	 * 
	 * @return string vraag
	 */
	public String getVraag() {
		return vraag;
	}

	public void setVraag(String vraag) {
		if (vraag == null || vraag.isEmpty()) {
			throw new IllegalArgumentException("Geen vraag meegegeven");
		}
		this.vraag = vraag;
	}

	/**
	 * Geeft het juiste antwoord terug
	 * 
	 * @return string
	 */
	public String getJuisteAntwoord() {
		return juisteAntwoord;
	}

	public void setJuisteAntwoord(String juisteAntwoord) {
		if (juisteAntwoord == null || juisteAntwoord.isEmpty()) {
			throw new IllegalArgumentException("Geen juisteAntwoord meegegeven");
		}
		this.juisteAntwoord = juisteAntwoord;
	}

	/**
	 * Geeft het maximaal aantal pogingen terug.
	 * 
	 * @return int
	 */
	public int getMaxAantalPogingen() {
		return maxAantalPogingen;
	}

	/**
	 * bepaal het max. aantal pogingen die iemand mag ondernemen.
	 * 
	 * @param maxAantalPogingen
	 *            int >0
	 */
	public void setMaxAantalPogingen(int maxAantalPogingen) {
		if (maxAantalPogingen <= 0) {
			throw new IllegalArgumentException(
					"max. aantal pogingen moet minstens één zijn.");
		}
		this.maxAantalPogingen = maxAantalPogingen;
	}

	/**
	 * Geeft een list met de antwoordhints.
	 * 
	 * @return ArrayList met strings
	 */
	public ArrayList<String> getAntwoordHints() {
		return antwoordHints;
	}

	private void setAntwoordHints(ArrayList<String> antwoordHints) {
		if (antwoordHints == null) {
			throw new IllegalArgumentException(
					"antwoordHints mag niet null zijn.");
		}

		this.antwoordHints = antwoordHints;
	}

	/**
	 * Geeft de tijd terug in seconden die aan een vraag mag besteed worden.
	 * indien geen maximum tijd, wordt 0 teruggegeven.
	 * 
	 * @return int
	 */
	public int getMaxAntwoordTijd() {
		return maxAntwoordTijd;
	}

	/**
	 * stel de maximum tijd in (in seconden) die aan een vraag mag besteed
	 * worden. 0 voor onbeperkt.
	 * 
	 * @param maxAntwoordTijd
	 *            int
	 */
	public void setMaxAntwoordTijd(int maxAntwoordTijd) {
		if (maxAntwoordTijd < 0) {
			throw new IllegalArgumentException(
					"maxAntwoordTijd moet minstens 0 zijn.");
		}
		this.maxAntwoordTijd = maxAntwoordTijd;
	}

	/**
	 * Geeft de categorie van de opdracht terug
	 * 
	 * @return OpdrachtCategorie
	 */
	public OpdrachtCategorie getCategorie() {
		return categorie;
	}

	public void setCategorie(OpdrachtCategorie categorie) {
		if (categorie == null) {
			throw new IllegalArgumentException("categorie mag niet null zijn");
		}
		this.categorie = categorie;
	}

	/**
	 * Geeft een list terug met de gekoppelde quizen
	 * 
	 * @return ArrayList met quizzen.
	 */
	public ArrayList<QuizOpdracht> getQuizzen() {
		return quizzen;
	}

	private void setQuizzen(ArrayList<QuizOpdracht> quizzen) {
		if (quizzen == null) {
			throw new IllegalArgumentException("quizzen mag niet null zijn");
		}

		this.quizzen = quizzen;
	}

	/**
	 * Geeft de auteur van de opdracht
	 * 
	 * @return Leraar
	 */
	public Leraar getAuteur() {
		return auteur;
	}

	/**
	 * Stel de auteur in van de opdracht
	 * 
	 * @param auteur
	 */
	public void setAuteur(Leraar auteur) {
		if (auteur == null) {
			throw new IllegalArgumentException("Geen auteur meegegeven");
		}
		this.auteur = auteur;
	}

	/**
	 * Geeft de datum van registratie terug.
	 * 
	 * @return Datum
	 */
	public Datum getDatumRegistratie() {
		return datumRegistratie;
	}

	/**
	 * Stel de datum va registratie in
	 * 
	 * @param datumRegistratie
	 *            Datum
	 */
	public void setDatumRegistratie(Datum datumRegistratie) {
		if (datumRegistratie == null) {
			throw new IllegalArgumentException(
					"datumRegistratie mag niet null zijn");
		}
		this.datumRegistratie = datumRegistratie;
	}

	/**
	 * Voegt een hint toe aan de antwoordhints
	 * 
	 * @param hint
	 *            sting
	 */
	public void addAntwoordHint(String hint) {
		if (hint == null || hint.isEmpty()) {
			throw new IllegalArgumentException("Geen juisteAntwoord meegegeven");
		}
		this.antwoordHints.add(hint);
	}

	/**
	 * Koppelt deze opdracht met een quiz
	 * 
	 * @param quiz
	 *            De toe te voegen quiz
	 * @param maxscore
	 *            de maximumscore die met deze vraag gehaald kan worden
	 */
	public void addQuiz(Quiz quiz, int maxscore) {
		// Validatie gebeurt in constructor van QuizOpdracht
		QuizOpdracht quizopdracht = new QuizOpdracht(maxscore, this, quiz);
		quizzen.add(quizopdracht);
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
	 * Geeft aan of een opdracht wijzigbaar is. Een opdracht is wijzigbaar
	 * indien ze nog niet gelinkt is aan een quiz/test die door leerlingen al is
	 * uitgevoerd.
	 * 
	 * @return boolean
	 */
	public boolean isOpdrachtWijzigbaar() {
		for (QuizOpdracht quizopdracht : quizzen) {
			if (!(quizopdracht.getOpdrachtAntwoorden().isEmpty())) {
				return false;
			}
		}
		return true;
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
	@SuppressWarnings("unchecked")
	@Override
	public Object clone() throws CloneNotSupportedException {
		Opdracht clone = new Opdracht(vraag, juisteAntwoord, categorie, auteur,
				datumRegistratie);
		clone.setAntwoordHints((ArrayList<String>) antwoordHints.clone());
		clone.setMaxAantalPogingen(maxAantalPogingen);
		clone.setMaxAntwoordTijd(maxAntwoordTijd);
		clone.setQuizzen((ArrayList<QuizOpdracht>) quizzen.clone());
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
		return vraag.compareTo(opdracht.getVraag());
	}

	public static void main(String[] args) {
		Opdracht opdracht;
		String vraag;
		String antwoord;
		OpdrachtCategorie categorie;
		Leraar leraar;
		Datum datum;

		vraag = "";
		antwoord = "";
		categorie = OpdrachtCategorie.algemeneKennis;
		leraar = Leraar.Alain;
		datum = new Datum(20, 10, 2012);

		opdracht = new Opdracht(vraag, antwoord, categorie, leraar, datum);
	}

}
