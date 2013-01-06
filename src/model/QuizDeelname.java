package model;

import java.io.IOException;
import java.util.ArrayList;

import utils.Datum;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class QuizDeelname extends RapporteerbaarObject implements
		Comparable<QuizDeelname> {

	private Leerling leerling;
	// private Leraar leraar;
	private Quiz quiz;
	private Datum datumDeelname;
	private ArrayList<OpdrachtAntwoord> opdrachtAntwoorden;
	private QuizScore quizScore;

	public void setQuizScore(QuizScore quizScore) {
		this.quizScore = quizScore;
	}

	/**
	 * Constructor.
	 * 
	 * @param leerling
	 * @param quiz
	 * @param datumDeelname
	 */
	private QuizDeelname(Leerling leerling, Quiz quiz, Datum datumDeelname) {
		setLeerling(leerling);
		setQuiz(quiz);
		setDatumDeelname(datumDeelname);
		opdrachtAntwoorden = new ArrayList<OpdrachtAntwoord>();

		try {
			quizScore = QuizScoreRegelsFactory.getInstance().getQuizScore(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the leerling
	 */
	public Leerling getLeerling() {
		return leerling;
	}

	/**
	 * Stel de leerling in.
	 * 
	 * @param leerling
	 *            the leerling to set
	 * @throws IllegalArgumentException
	 */
	public void setLeerling(Leerling leerling) throws IllegalArgumentException {
		if (leerling == null) {
			throw new IllegalArgumentException("leerling is null");
		}
		this.leerling = leerling;
	}

	/**
	 * @return the quiz
	 */
	public Quiz getQuiz() {
		return quiz;
	}

	/**
	 * @param quiz
	 *            the quiz to set
	 */
	public void setQuiz(Quiz quiz) throws IllegalArgumentException {
		if (quiz == null) {
			throw new IllegalArgumentException("quiz is null");
		}
		this.quiz = quiz;
	}

	/**
	 * @return the datumDeelname
	 */
	public Datum getDatumDeelname() {
		return datumDeelname;
	}

	/**
	 * @param datumDeelname
	 *            the datumDeelname to set
	 */
	public void setDatumDeelname(Datum datumDeelname)
			throws IllegalArgumentException {
		if (datumDeelname == null) {
			throw new IllegalArgumentException("datumDeelname is null");
		}
		this.datumDeelname = datumDeelname;
	}

	/**
	 * @return the opdrachtAntwoorden
	 */
	public ArrayList<OpdrachtAntwoord> getOpdrachtAntwoorden() {
		return opdrachtAntwoorden;
	}

	/**
	 * @param opdrachtAntwoorden
	 *            the opdrachtAntwoorden to set
	 */
	public void setOpdrachtAntwoorden(
			ArrayList<OpdrachtAntwoord> opdrachtAntwoorden)
			throws IllegalArgumentException {
		if (opdrachtAntwoorden == null) {
			throw new IllegalArgumentException("opdrachtAntwoorden is null");
		}
		this.opdrachtAntwoorden = opdrachtAntwoorden;
	}

	/**
	 * Geeft de sore van deze deelname. Wordt herleid naar 10, afgerond op 0
	 * cijfers.
	 * 
	 * @return int, de score
	 */
	public int getDeelnameScore() {
		int aantalAntwoorden = opdrachtAntwoorden.size();
		if (aantalAntwoorden == 0)
			return 0;
		return quizScore.getScore();
	}

	/**
	 * Maakt een nieuwe quizdeelname aan en koppelt deze aan de quiz en aan de
	 * leerling.
	 * 
	 * @param quiz
	 *            De quiz
	 * @param leerling
	 *            de leerling die aan de quiz moet gekoppelt worden
	 * @param datumDeelname
	 *            Datum
	 */
	public static void KoppelLeerlingAanQuiz(Quiz quiz, Leerling leerling,
			Datum datumDeelname) {
		QuizDeelname quizDeelname = new QuizDeelname(leerling, quiz,
				datumDeelname);
		leerling.voegQuizDeelnameToe(quizDeelname);
		quiz.voegQuizDeelnameToe(quizDeelname);
	}

	/**
	 * verwijdert dit object bij zijn quiz en zijn leerling.
	 */
	public void OntkoppelLeerlingVanQuiz() {
		quiz.verwijderQuizDeelname(this);
		leerling.verwijderQuizDeelname(this);
	}

	/**
	 * Voegt een opdrachtAntwoord toe aan de lijst van OpdrachtAntwoorden
	 * 
	 * @param opdrachtAntwoord
	 *            OpdrachtAntwoord
	 * @throws IllegalArgumentException
	 *             Gooit een exception als het antwoord al aanwezig is of als
	 *             het antwoord null is
	 */
	protected void voegOpdrachtAntwoordToe(OpdrachtAntwoord opdrachtAntwoord)
			throws IllegalArgumentException {
		if (opdrachtAntwoord == null) {
			throw new IllegalArgumentException(
					"OpdrachtAntwoord mag niet null zijn");
		}
		if (opdrachtAntwoorden.contains(opdrachtAntwoord)) {
			throw new IllegalArgumentException("OpdrachtAntwoord bestaat al");
		}
		opdrachtAntwoorden.add(opdrachtAntwoord);
	}

	/**
	 * Verwijdert een opdrachtAntwoord uit de lijst van OpdrachtAntwoorden
	 * 
	 * @param opdrachtAntwoord
	 * @throws IllegalArgumentException
	 *             Gooit een exception als het antwoord null is of niet aanwezig
	 *             is
	 */
	protected void verwijderOpdrachtAntwoord(OpdrachtAntwoord opdrachtAntwoord)
			throws IllegalArgumentException {
		if (opdrachtAntwoord == null) {
			throw new IllegalArgumentException("Opdracht mag niet null zijn");
		}
		if (!(opdrachtAntwoorden.contains(opdrachtAntwoord))) {
			throw new IllegalArgumentException("Opdracht bestaat niet");
		}
		opdrachtAntwoorden.remove(opdrachtAntwoord);
	}

	/**
	 * Geeft het rapport van de leerling terug in Stringvorm
	 * 
	 * @return String
	 */
	@Override
	public String getRapport() {
		rapport += "Beste " + leerling.getLeerlingNaam() + ",\n\n";
		rapport += "Ziehier het overzicht van al uw antwoorden:  \n\n";
		for (OpdrachtAntwoord oa : this.getOpdrachtAntwoorden()) {
			rapport += "Vraag: "
					+ oa.getQuizopdracht().getOpdracht().getVraag() + "\n";
			rapport += "Juiste antwoord: "
					+ oa.getQuizopdracht().getOpdracht().getJuisteAntwoord()
					+ "\n";
			rapport += "Uw antwoord: " + oa.getLaatsteAntwoord() + "\n";
		}
		rapport += "U behaalde op de quiz \"" + getQuiz().getOnderwerp()
				+ "\"\n";
		rapport += "de score van " + getDeelnameScore() + " punten\n";
		rapport += "op een totaal van 10 punten";
		return rapport;
	}

	/**
	 * Geeft de gemiddelde score terug die behaald werd op de quiz waar de
	 * leerling aan deelnam
	 * 
	 * @return double
	 */
	public double getGemiddeldeScore() {
		double gemiddeldeScore = getQuiz().getGemiddeldeQuizScore();
		return gemiddeldeScore;
	}

	/**
	 * Geeft de auteur terug van de quiz waar de leerling aan heeft deelgenomen
	 * 
	 * @return Leraar
	 */
	public Leraar getAuteur() {
		return getQuiz().getAuteur();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((datumDeelname == null) ? 0 : datumDeelname.hashCode());
		result = prime * result
				+ ((leerling == null) ? 0 : leerling.hashCode());
		result = prime
				* result
				+ ((opdrachtAntwoorden == null) ? 0 : opdrachtAntwoorden
						.hashCode());
		result = prime * result + ((quiz == null) ? 0 : quiz.hashCode());
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
		QuizDeelname other = (QuizDeelname) obj;
		if (datumDeelname == null) {
			if (other.datumDeelname != null)
				return false;
		} else if (!datumDeelname.equals(other.datumDeelname))
			return false;
		if (leerling == null) {
			if (other.leerling != null)
				return false;
		} else if (!leerling.equals(other.leerling))
			return false;
		if (opdrachtAntwoorden == null) {
			if (other.opdrachtAntwoorden != null)
				return false;
		} else if (!opdrachtAntwoorden.equals(other.opdrachtAntwoorden))
			return false;
		if (quiz == null) {
			if (other.quiz != null)
				return false;
		} else if (!quiz.equals(other.quiz))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QuizDeelname [leerling=" + leerling + ", quiz=" + quiz
				+ ", datumDeelname=" + datumDeelname + "]";
	}

	/**
	 * gebruikt de score om te vergelijken.
	 * 
	 * @param o
	 * @return int
	 * @throws IllegalArgumentException
	 */
	@Override
	public int compareTo(QuizDeelname o) {
		if (o == null) {
			throw new IllegalArgumentException("QuizDeelname is null.");
		}
		double verschil = getDeelnameScore() - o.getDeelnameScore();
		return verschil == 0 ? 0 : verschil > 0 ? 1 : -1;
	}

	private static void main(String[] args) {
		Leerling leerling = new Leerling("Peter", 4);
		Quiz quiz = new Quiz("Hoofdsteden", Leraar.Alain, true, 5);
		Opdracht opdracht = new Opdracht("Hoofdstad Frankrijk", "Parijs",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		Opdracht opdracht2 = new Opdracht("Hoofdstad belgië", "Brussel",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		opdracht.setMaxAntwoordTijd(10);
		opdracht2.setMaxAntwoordTijd(10);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht, 7);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht2, 7);
		quiz.setStatus(quiz.getOpengesteld());
		QuizDeelname.KoppelLeerlingAanQuiz(quiz, leerling, new Datum(4, 11,
				2012));
		QuizDeelname qd = quiz.getQuizDeelnames().get(0);
		OpdrachtAntwoord.koppelOpdrachtAanDeelname("Parijs", 1, 5, quiz
				.getOpdrachten().get(0), qd);
		OpdrachtAntwoord.koppelOpdrachtAanDeelname("Brussel", 2, 5, quiz
				.getOpdrachten().get(1), qd);
		// System.out.println(qd.getDeelnameScore());
		// RapportKopRegels rapport = new RapportKopRegels(qd);
		RapporteerbaarObject rapport = qd;
		rapport = new RapportKopRegels(rapport, qd);
		rapport = new RapportSlotRegels(rapport, qd);
		System.out.println(rapport.getRapport());
	}
}
