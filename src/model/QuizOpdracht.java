package model;

import java.util.ArrayList;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class QuizOpdracht {

	private int maxScore;
	private Opdracht opdracht;
	private Quiz quiz;
	private ArrayList<OpdrachtAntwoord> opdrachtAntwoorden;

	/**
	 * Constructor. Koppelt een quiz aan een opdracht met een max.score
	 * 
	 * @param maxScore
	 *            int, positief
	 * @param opdracht
	 *            Opdract
	 * @param quiz
	 *            Quiz
	 */
	private QuizOpdracht(int maxScore, Opdracht opdracht, Quiz quiz)
			throws IllegalArgumentException {
		setMaxScore(maxScore);
		setOpdracht(opdracht);
		setQuiz(quiz);
		opdrachtAntwoorden = new ArrayList<OpdrachtAntwoord>();
	}

	/**
	 * Geeft de maximum score
	 * 
	 * @return the maxScore int
	 */
	public int getMaxScore() {
		return maxScore;
	}

	/**
	 * @param maxScore
	 *            int, the maxScore to set (mag niet negatief zijn)
	 */
	public void setMaxScore(int maxScore) {
		if (maxScore < 0) {
			throw new IllegalArgumentException(
					"maxscore mag niet negatief zijn.");
		}
		this.maxScore = maxScore;
	}

	/**
	 * Geeft de opdracht
	 * 
	 * @return the opdracht
	 */
	public Opdracht getOpdracht() {
		return opdracht;
	}

	/**
	 * @param opdracht
	 *            the opdracht to set
	 */
	public void setOpdracht(Opdracht opdracht) {
		if (opdracht == null) {
			throw new IllegalArgumentException("opdracht is null.");
		}

		this.opdracht = opdracht;
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
	public void setQuiz(Quiz quiz) {
		if (quiz == null) {
			throw new IllegalArgumentException("quiz is null.");
		}

		this.quiz = quiz;
	}

	/**
	 * Geeft een arraylist met alle opdrachtantwoorden.
	 * 
	 * @return the opdrachtAntwoorden
	 */
	public ArrayList<OpdrachtAntwoord> getOpdrachtAntwoorden() {
		return opdrachtAntwoorden;
	}

	/**
	 * @param opdrachtAntwoorden
	 *            the opdrachtAntwoorden to set
	 * @throws IllegalArgumentException
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
	 * Geeft de gemiddelde score van alle deelnames aan deze quizopdracht.
	 * 
	 * @return double, gemiddelde score.
	 */
	public double getGemiddeldeScore() {
		int aantalAntwoorden = opdrachtAntwoorden.size();
		if (aantalAntwoorden == 0)
			return 0.0;
		double somScore = 0;
		for (OpdrachtAntwoord antwoord : opdrachtAntwoorden) {
			somScore += antwoord.GetOpdrachtScore();
		}
		return somScore / aantalAntwoorden;
	}

	/**
	 * Maakt een nieuw Quizopdracht object aan en voegt dit toe aan opdract en
	 * quiz.
	 * 
	 * @param quiz
	 * @param opdracht
	 * @param maxScore
	 */
	public static void koppelOpdrachtAanQuiz(Quiz quiz, Opdracht opdracht,
			int maxScore) {
		QuizOpdracht quizOpdracht = new QuizOpdracht(maxScore, opdracht, quiz);
		quiz.voegQuizOpdrachtToe(quizOpdracht);
		opdracht.voegQuizOpdrachtToe(quizOpdracht);
	}

	/**
	 * Verwijdert dit quizopdracht object bij de quiz en bij de opdracht.
	 */
	public void ontKoppelOpdrachtVanQuiz() {
		quiz.verwijderQuizOpdracht(this);
		opdracht.verwijderQuizOpdracht(this);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maxScore;
		result = prime * result
				+ ((opdracht == null) ? 0 : opdracht.hashCode());
		result = prime * result + ((quiz == null) ? 0 : quiz.hashCode());
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
		QuizOpdracht other = (QuizOpdracht) obj;
		if (maxScore != other.maxScore)
			return false;
		if (opdracht == null) {
			if (other.opdracht != null)
				return false;
		} else if (!opdracht.equals(other.opdracht))
			return false;
		if (quiz == null) {
			if (other.quiz != null)
				return false;
		} else if (!quiz.equals(other.quiz))
			return false;
		return true;
	}

	public static void main(String[] args) {

	}

}
