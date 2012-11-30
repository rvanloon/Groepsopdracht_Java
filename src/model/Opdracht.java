package model;

import java.util.ArrayList;

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
	private ArrayList<QuizOpdracht> quizOpdracten;
	private Leraar auteur;
	private Datum datumRegistratie;
	private int key;

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
		this.quizOpdracten = new ArrayList<QuizOpdracht>();
		this.setKey(-1);
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

	public void setAntwoordHints(ArrayList<String> antwoordHints) {
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
	public ArrayList<QuizOpdracht> getQuizOpdracten() {
		return quizOpdracten;
	}

	private void setQuizOpdrachten(ArrayList<QuizOpdracht> quizzen) {
		if (quizzen == null) {
			throw new IllegalArgumentException("quizzen mag niet null zijn");
		}

		this.quizOpdracten = quizzen;
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
	 * @return the key
	 */
	public int getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(int key) {
		this.key = key;
	}

	/**
	 * Voegt een hint toe aan de antwoordhints
	 * 
	 * @param hint
	 *            sting
	 */
	public void addAntwoordHint(String hint) {
		if (hint == null || hint.isEmpty()) {
			throw new IllegalArgumentException("Geen antwoordhint meegegeven");
		}
		this.antwoordHints.add(hint);
	}

	/**
	 * Functie waarmee men een QuizOpdracht kan toevoegen aan de
	 * QuizOpdrachtlijst.
	 * 
	 * @param opdracht
	 *            QuizOpdracht
	 * @throws IllegalArgumentException
	 *             Gooit een exception als de opdracht null is of reeds
	 *             toegevoegs is.
	 */
	protected void voegQuizOpdrachtToe(QuizOpdracht opdracht)
			throws IllegalArgumentException {
		if (opdracht == null) {
			throw new IllegalArgumentException(
					"De quizDeelname mag niet null zijn");
		}
		if (quizOpdracten.contains(opdracht)) {
			throw new IllegalArgumentException(
					"Deze opdracht is al toegevoegd.");
		}
		quizOpdracten.add(opdracht);
	}

	/**
	 * Functie waarmee men een QuizOpdracht kan verwijderen uit de
	 * QuizOpdrachtlijst. Indien de opdracht niet aanwezig is wordt een
	 * exception gegooid
	 * 
	 * @param opdracht
	 *            QuizOpdracht
	 * @throws IllegalArgumentException
	 *             Gooit een Exception als de opdracht null is of als de
	 *             opdracht niet aanwezig is in de lijst
	 */
	protected void verwijderQuizOpdracht(QuizOpdracht opdracht)
			throws IllegalArgumentException {
		if (opdracht == null) {
			throw new IllegalArgumentException("De opdracht mag niet null zijn");
		}
		if (!(quizOpdracten.contains(opdracht))) {
			throw new IllegalArgumentException(
					"De opdracht zit niet in de lijst.");
		}
		quizOpdracten.remove(opdracht);

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
		for (QuizOpdracht quizopdracht : quizOpdracten) {
			if (!(quizopdracht.getOpdrachtAntwoorden().isEmpty())) {
				return false;
			}
		}
		return true;
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
				+ ((antwoordHints == null) ? 0 : antwoordHints.hashCode());
		result = prime * result + ((auteur == null) ? 0 : auteur.hashCode());
		result = prime * result
				+ ((categorie == null) ? 0 : categorie.hashCode());
		result = prime
				* result
				+ ((datumRegistratie == null) ? 0 : datumRegistratie.hashCode());
		result = prime * result
				+ ((juisteAntwoord == null) ? 0 : juisteAntwoord.hashCode());
		result = prime * result + maxAantalPogingen;
		result = prime * result + maxAntwoordTijd;
//		result = prime * result
//				+ ((quizOpdracten == null) ? 0 : quizOpdracten.hashCode());
		result = prime * result + ((vraag == null) ? 0 : vraag.hashCode());
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
		Opdracht other = (Opdracht) obj;
		if (antwoordHints == null) {
			if (other.antwoordHints != null)
				return false;
		} else if (!antwoordHints.equals(other.antwoordHints))
			return false;
		if (auteur != other.auteur)
			return false;
		if (categorie != other.categorie)
			return false;
		if (datumRegistratie == null) {
			if (other.datumRegistratie != null)
				return false;
		} else if (!datumRegistratie.equals(other.datumRegistratie))
			return false;
		if (juisteAntwoord == null) {
			if (other.juisteAntwoord != null)
				return false;
		} else if (!juisteAntwoord.equals(other.juisteAntwoord))
			return false;
		if (maxAantalPogingen != other.maxAantalPogingen)
			return false;
		if (maxAntwoordTijd != other.maxAntwoordTijd)
			return false;
		if (quizOpdracten == null) {
			if (other.quizOpdracten != null)
				return false;
		} else if (!quizOpdracten.equals(other.quizOpdracten))
			return false;
		if (vraag == null) {
			if (other.vraag != null)
				return false;
		} else if (!vraag.equals(other.vraag))
			return false;
		if (key != other.key)
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
		clone.setQuizOpdrachten((ArrayList<QuizOpdracht>) quizOpdracten.clone());
		return clone;
	}

	/**
	 * Geeft de vraag terug.
	 */
	@Override
	public String toString() {
		return String.format("|%1$-25s |%2$-10s |%3$-10s |\n", vraag, getClass().getSimpleName(), categorie);
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
			Opdracht o = new Opdracht("aaaa", "bbbbb", OpdrachtCategorie.NederlandseTaal, Leraar.Robrecht, new Datum());
			System.out.println(o);
	}

}
