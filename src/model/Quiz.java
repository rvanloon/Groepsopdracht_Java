package model;

import java.util.ArrayList;

import utils.Datum;

/**
 * 
 * @author Sven
 * 
 */

public class Quiz implements Cloneable, Comparable<Quiz>, Observable {
	private String onderwerp;
	private ArrayList<Integer> leerjaren;
	private Leraar auteur;
	private Datum datumRegistratie;
	private Boolean isTest;
	private ArrayList<QuizOpdracht> opdrachten;
	private ArrayList<QuizDeelname> quizDeelnames;
	private QuizStatus inConstructie;
	private QuizStatus afgewerkt;
	private QuizStatus opengesteld;
	private QuizStatus laatsteKans;
	private QuizStatus afgesloten;
	private QuizStatus status;
	private ArrayList<Observer> observers;

	/**
	 * Geeft het onderwerp van de Quiz terug
	 * 
	 * @return String
	 */
	public String getOnderwerp() {
		return this.onderwerp;
	}

	/**
	 * Functie om het onderwerp van de quiz mee te geven
	 * 
	 * @param onderwerp
	 *            String
	 * @throws IllegalArgumentException
	 *             indien de meegegeven string null of leeg is
	 */
	public void setOnderwerp(String onderwerp) throws IllegalArgumentException {
		if (status.setOnderwerpToegestaan()) {
			if (onderwerp == null || onderwerp.isEmpty()) {
				throw new IllegalArgumentException(
						"Onderwerp mag niet null zijn");
			}
			this.onderwerp = onderwerp;
		} else {
			throw new IllegalArgumentException("Niet toegestaan wegens status");
		}
	}

	/**
	 * Geeft een ArrayList van de leerjaren terug waarvoor de quiz bedoeld is
	 * 
	 * @return ArrayList<Integer>
	 */
	public ArrayList<Integer> getLeerjaren() {
		return this.leerjaren;
	}

	/**
	 * Functie om de leerjaren mee te geven waar de quiz voor bedoeld is. Alle
	 * leerjaren moeten meegegeven worden.
	 * 
	 * @param leerjaren
	 *            int array
	 * @throws IllegalArgumentException
	 *             wanneer de meegegen int array null is, of de meegegeven
	 *             leerjaren kleiner dan nul of groter dan zes
	 */
	public void setLeerjaren(int... leerjaren) throws IllegalArgumentException {
		if (status.setLeerjarenToegestaan()) {
			if (leerjaren == null) {
				throw new IllegalArgumentException(
						"Aantal leerjaren mag niet null zijn");
			}
			this.leerjaren.clear();
			for (int jaar : leerjaren) {
				if (jaar < 1 || jaar > 6) {
					throw new IllegalArgumentException(
							"De waarde voor de leerjaren moet tussen 1 en 6 liggen");
				}
				if (!this.leerjaren.contains(jaar)) {
					this.leerjaren.add(jaar);
				}
			}
		} else {
			throw new IllegalArgumentException("Niet toegestaan wegens status");
		}

	}

	/**
	 * Functie om de leerjaren mee te geven waar de quiz voor bedoeld is. Alle
	 * leerjaren moeten meegegeven worden.
	 * 
	 * @param leerjaren
	 *            ArrayList<Integer>
	 * @throws IllegalArgumentException
	 *             wanneer de meegegen int array null is, of de meegegeven
	 *             leerjaren kleiner dan nul of groter dan zes
	 */
	public void setLeerjaren(ArrayList<Integer> leerjaren)
			throws IllegalArgumentException {
		if (status.setLeerjarenToegestaan()) {
			if (leerjaren == null) {
				throw new IllegalArgumentException(
						"Aantal leerjaren mag niet null zijn");
			}
			this.leerjaren.clear();
			for (int jaar : leerjaren) {
				if (jaar < 1 || jaar > 6) {
					throw new IllegalArgumentException(
							"De waarde voor de leerjaren moet tussen 1 en 6 liggen");
				}
				if (!this.leerjaren.contains(jaar)) {
					this.leerjaren.add(jaar);
				}
			}
		} else {
			throw new IllegalArgumentException("Niet toegestaan wegens status");
		}
	}

	/**
	 * Geeft de auteur terug die de quiz opgesteld heeft
	 * 
	 * @return enum Leraar
	 */
	public Leraar getAuteur() {
		return this.auteur;
	}

	/**
	 * Functie om de auteur mee te geven die de quiz heeft opgesteld
	 * 
	 * @param auteur
	 *            enum Auteur
	 * @throws IllegalArgumentException
	 *             indien de auteur null is
	 */
	public void setAuteur(Leraar auteur) throws IllegalArgumentException {
		if (status.setAuteurToegestaan()) {
			if (auteur == null) {
				throw new IllegalArgumentException("Auteur mag niet null zijn");
			}
			this.auteur = auteur;
		} else {
			throw new IllegalArgumentException("Niet toegestaan wegens status");
		}

	}

	/**
	 * Geeft true terug als een leerling maar één keer aan de quiz mag
	 * deelnemen. Geeft false terug als een leerling meerdere keren aan een quiz
	 * mag deelnemen
	 * 
	 * @return boolean
	 */
	public Boolean getIsTest() {
		return this.isTest;
	}

	/**
	 * Functie om aan te geven of de quiz een test is of niet
	 * 
	 * @param isTest
	 *            Boolean
	 * @throws IllegalArgumentException
	 *             indien de meegegeven Boolean null is
	 */
	public void setIsTest(Boolean isTest) throws IllegalArgumentException {
		if (status.setIsTestToegestaan()) {
			if (isTest == null) {
				throw new IllegalArgumentException("auteur mag niet null zijn");
			}
			this.isTest = isTest;
		} else {
			throw new IllegalArgumentException("Niet toegestaan wegens status");
		}
	}

	/**
	 * Geeft de registratiedatum van de quiz terug
	 * 
	 * @return DatumGregorian
	 * @throws NullPointerException
	 *             als de datum null is
	 */
	public Datum getDatumRegistratie() {
		return this.datumRegistratie;
	}

	/**
	 * Functie waarmee men een registratiedatum voor de quiz kan vastleggen
	 * 
	 * @param datumRegistratie
	 *            DatumGregorian
	 * @throws IllegalArgumentException
	 *             indien de meegegeven datum null is
	 */
	public void setDatumRegistratie(Datum datumRegistratie)
			throws IllegalArgumentException {
		if (status.setDatumRegistratieToegestaan()) {
			if (datumRegistratie == null) {
				throw new IllegalArgumentException("Datum mag niet null zijn");
			}
			this.datumRegistratie = datumRegistratie;
		} else {
			throw new IllegalArgumentException("Niet toegestaan wegens status");
		}

	}

	/**
	 * Geeft een lijst terug van de QuizOpdrachten
	 * 
	 * @return List QuizOpdrachten
	 */
	public ArrayList<QuizOpdracht> getOpdrachten() {
		return opdrachten;
	}

	/**
	 * Functie waarmee men een ArrayList van opdrachten kan meegeven aan de quiz
	 * 
	 * @param opdrachten
	 *            ArrayList
	 * @throws IllegalArgumentException
	 *             Gooit een Exception als de ArrayList null is of geen
	 *             opdrachten bevat
	 */
	public void setOpdrachten(ArrayList<QuizOpdracht> opdrachten)
			throws IllegalArgumentException {
		if (opdrachten == null || opdrachten.isEmpty()) {
			throw new IllegalArgumentException(
					"De opdrachtenlijst mag niet null zijn");
		}
		this.opdrachten = opdrachten;
	}

	/**
	 * Geeft een ArrayList terug van QuizDeelnames
	 * 
	 * @return the quizDeelnames
	 */
	public ArrayList<QuizDeelname> getQuizDeelnames() {
		return quizDeelnames;
	}

	/**
	 * Functie waarmee men een ArrayList van QuizDeelnames kan meegeven aan de
	 * quiz
	 * 
	 * @param quizDeelnames
	 *            the quizDeelnames to set
	 * @throws IllegalArgumentException
	 */
	public void setQuizDeelnames(ArrayList<QuizDeelname> quizDeelnames)
			throws IllegalArgumentException {
		if (quizDeelnames == null) {
			throw new IllegalArgumentException("quizdeelnames is null");
		}
		this.quizDeelnames = quizDeelnames;
	}

	/**
	 * Functie waarmee men de quiz een bepaalde status kan meegeven
	 * 
	 * @param QuizStatus
	 *            status
	 */
	public void setStatus(QuizStatus status) {
		if (status == null) {
			throw new IllegalArgumentException("status mag niet null zijn");
		}
		this.status = status;
	}

	/**
	 * Geeft de status van de quiz terug
	 * 
	 * @return QuizStatus: de status van de quiz
	 */
	public QuizStatus getStatus() {
		return this.status;
	}

	/**
	 * Geeft de QuizStatus InConstructieStatus terug
	 * 
	 * @return QuizStatus InConstructieStatus
	 */
	public QuizStatus getInConstructie() {
		return inConstructie;
	}

	/**
	 * Geeft de QuizStatus AfgewerktStatus terug
	 * 
	 * @return QuizStatus AFgewerktStatus
	 */
	public QuizStatus getAfgewerkt() {
		return afgewerkt;
	}

	/**
	 * Geeft de QuizStatus OpengesteldStatus terug
	 * 
	 * @return QuizStatus Opengesteld
	 */
	public QuizStatus getOpengesteld() {
		return opengesteld;
	}

	/**
	 * Geeft de QuizStatus LaatsteKansStatus terug
	 * 
	 * @return QuizStatus LaatsteKansStatus
	 */
	public QuizStatus getLaatsteKans() {
		notifyObservers();
		return laatsteKans;
	}

	/**
	 * Geeft de QuizStatus AfgeslotenStatus terug
	 * 
	 * @return QuizStatus AfgeslotenStatus
	 */
	public QuizStatus getAfgesloten() {
		notifyObservers();
		return afgesloten;
	}

	/**
	 * Constructor waarmee men een nieuwe quiz kan aanmaken. Men moet een geldig
	 * onderwerp, een geldige leraar, een boolean om aan te geven of het een
	 * test betreft en tenslotte het leerjaar (of de leerjaren) waarvoor de quiz
	 * bedoeld is. De status wordt default als "in constructie" geplaatst. De
	 * registratiedatum krijgt de systeemdatum mee.
	 * 
	 * @param onderwerp
	 *            String
	 * @param auteur
	 *            enum Leraar
	 * @param test
	 *            Boolean
	 * @param jaren
	 *            integer of meerdere integers
	 */
	public Quiz(String onderwerp, Leraar auteur, Boolean test, int... jaren) {
		inConstructie = new InConstructieStatus(this);
		afgewerkt = new AfgewerktStatus(this);
		opengesteld = new OpengesteldStatus(this);
		laatsteKans = new LaatsteKansStatus(this);
		afgesloten = new AfgeslotenStatus(this);
		status = inConstructie;
		setOnderwerp(onderwerp);
		setAuteur(auteur);
		setIsTest(test);
		this.leerjaren = new ArrayList<Integer>();
		setLeerjaren(jaren);
		this.opdrachten = new ArrayList<QuizOpdracht>();
		this.setDatumRegistratie(new Datum());
		quizDeelnames = new ArrayList<QuizDeelname>();
		observers = new ArrayList<Observer>();
	}

	/**
	 * Default constuctor
	 */
	public Quiz() {
		inConstructie = new InConstructieStatus(this);
		afgewerkt = new AfgewerktStatus(this);
		opengesteld = new OpengesteldStatus(this);
		laatsteKans = new LaatsteKansStatus(this);
		afgesloten = new AfgeslotenStatus(this);
		status = inConstructie;
		this.opdrachten = new ArrayList<QuizOpdracht>();
		this.leerjaren = new ArrayList<Integer>();
		quizDeelnames = new ArrayList<QuizDeelname>();
		observers = new ArrayList<Observer>();
	}

	/**
	 * Functie waarmee men een QuizOpdracht kan toevoegen aan de
	 * opdrachtenlijst.
	 * 
	 * @param opdracht
	 *            QuizOpdracht
	 * @throws IllegalArgumentException
	 *             Gooit een exception als de opdracht null is of reeds
	 *             toegevoegs is.
	 */
	protected void voegQuizOpdrachtToe(QuizOpdracht opdracht)
			throws IllegalArgumentException {
		status.voegQuizOpdrachtToe(opdracht);
	}

	/**
	 * Functie waarmee men een QuizOpdracht kan verwijderen uit de
	 * opdrachtenlijst. Indien de opdracht niet aanwezig is wordt een exception
	 * gegooid
	 * 
	 * @param opdracht
	 *            QuizOpdracht
	 * @throws IllegalArgumentException
	 *             Gooit een Exception als de opdracht null is of als de
	 *             opdracht niet aanwezig is in de lijst
	 */
	protected void verwijderQuizOpdracht(QuizOpdracht opdracht)
			throws IllegalArgumentException {
		status.verwijderQuizOpdracht(opdracht);
	}

	/**
	 * Functie waarmee men een quizDeelname kan toevoegen aan de
	 * quizDeelnamelijst.
	 * 
	 * @param quizDeelname
	 *            quizDeelname
	 * @throws IllegalArgumentException
	 *             Gooit een exception als de quizDeelname null is of reeds
	 *             toegevoegs is.
	 */
	protected void voegQuizDeelnameToe(QuizDeelname quizDeelname)
			throws IllegalArgumentException {
		status.voegQuizDeelnameToe(quizDeelname);
	}

	/**
	 * Functie waarmee men een QuizDeelname quizDeelname kan verwijderen uit de
	 * QuizDeelname quizDeelnamelijst. Indien de QuizDeelname quizDeelname niet
	 * aanwezig is wordt een exception gegooid
	 * 
	 * @param quizDeelname
	 *            quizDeelname
	 * @throws IllegalArgumentException
	 */
	protected void verwijderQuizDeelname(QuizDeelname quizDeelname)
			throws IllegalArgumentException {

	}

	/**
	 * Geeft de gemiddelde score van alle deelnames aan deze quiz.
	 * 
	 * @return double, gemiddelde score.
	 */
	public double getGemiddeldeQuizScore() {
		int aantalDeelnames = quizDeelnames.size();
		if (aantalDeelnames == 0)
			return 0.0;
		double somScore = 0.0;
		for (QuizDeelname deelname : quizDeelnames) {
			somScore += (double) deelname.getDeelnameScore();
		}
		return somScore / aantalDeelnames;
	}

	/**
	 * Geeft de maximumscore terug die op de quiz kan behaalt worden.
	 * 
	 * @return
	 */
	public int getTotaleMaximumScore() {
		int somScore = 0;
		for (QuizOpdracht qo : opdrachten) {
			somScore += qo.getMaxScore();
		}
		return somScore;
	}

	/**
	 * Voegt een observer toe aan de ArrayList van observers
	 * 
	 * @param Observer
	 */
	@Override
	public void addObserver(Observer observer) {
		if (observer == null) {
			throw new IllegalArgumentException(
					"observer object mag niet null zijn");
		}
		if (observers.contains(observer)) {
			throw new IllegalArgumentException(
					"De observer is al toegevoegd in de lijst");
		}
		observers.add(observer);
	}

	/**
	 * verwijdert een observer van de ArrayList van observers
	 * 
	 * @param Observer
	 */
	@Override
	public void removeObserver(Observer observer) {
		if (observer == null) {
			throw new IllegalArgumentException(
					"observer object mag niet null zijn");
		}
		if (observers.contains(observer)) {
			int index = observers.indexOf(observer);
			observers.remove(index);
		} else {
			throw new IllegalArgumentException(
					"De observer is niet teruggevonden in de lijst");
		}
	}

	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update();
		}
	}

	/**
	 * Geeft een string terug die de opdracht beschrijft in de vorm
	 * "(onderwerp), (auteur), (status)"
	 */
	@Override
	public String toString() {
		return this.onderwerp + ", " + this.auteur + ", " + this.status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auteur == null) ? 0 : auteur.hashCode());
		result = prime * result + ((isTest == null) ? 0 : isTest.hashCode());
		result = prime * result
				+ ((leerjaren == null) ? 0 : leerjaren.hashCode());
		result = prime * result
				+ ((onderwerp == null) ? 0 : onderwerp.hashCode());
		// result = prime * result
		// + ((opdrachten == null) ? 0 : opdrachten.hashCode());
		result = prime
				* result
				+ ((datumRegistratie == null) ? 0 : datumRegistratie.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quiz other = (Quiz) obj;
		if (auteur != other.auteur)
			return false;
		if (isTest == null) {
			if (other.isTest != null)
				return false;
		} else if (!isTest.equals(other.isTest))
			return false;
		if (leerjaren == null) {
			if (other.leerjaren != null)
				return false;
		} else if (!leerjaren.equals(other.leerjaren))
			return false;
		if (onderwerp == null) {
			if (other.onderwerp != null)
				return false;
		} else if (!onderwerp.equals(other.onderwerp))
			return false;
		// if (opdrachten == null) {
		// if (other.opdrachten != null)
		// return false;
		// } else if (!opdrachten.equals(other.opdrachten))
		// return false;
		if (datumRegistratie == null) {
			if (other.datumRegistratie != null)
				return false;
		} else if (!datumRegistratie.equals(other.datumRegistratie))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	/**
	 * Vergelijkt een quiz op basis van het onderwerp (alfabetisch)
	 * 
	 * @param Quiz
	 *            De quiz die vergeleken moet worden.
	 * @return 0: gelijk, negatieve waarde: kleiner, positieve waarde: groter
	 */
	@Override
	public int compareTo(Quiz q) {
		if (q == null) {
			throw new IllegalArgumentException("Quiz mag niet null zijn");
		}
		return q.onderwerp.compareTo(this.onderwerp);
	}

	/**
	 * Geeft een kopie terug van de huidige quiz
	 */
	@Override
	public Quiz clone() {
		int[] jarenClone = new int[this.leerjaren.size()];
		int i = 0;
		for (int jaar : this.leerjaren) {
			jarenClone[i] = jaar;
			i++;
		}
		Quiz clone = new Quiz(this.onderwerp, this.auteur, this.isTest,
				jarenClone);
		clone.setDatumRegistratie(datumRegistratie);
		clone.setStatus(this.status);
		for (QuizOpdracht opd : this.opdrachten) {
			clone.opdrachten.add(opd);
		}
		return clone;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Quiz quiz4 = new Quiz("Aardrijkskunde", Leraar.Alain, true, 4, 4,
					5, 6);
			System.out.println(quiz4.getLeerjaren());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
