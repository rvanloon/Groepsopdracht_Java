package model;

import java.util.ArrayList;
import java.util.Iterator;

import utils.DatumGregorian;

/**
 * 
 * @author sven
 * 
 */
public class QuizCatalogus implements Iterable<Quiz>, Cloneable {

	private ArrayList<Quiz> quizzen;

	/**
	 * Geeft een ArrayList terug met daarin de aanwezige quizzen
	 * 
	 * @return ArrayList
	 */
	public ArrayList<Quiz> getQuizzen() {
		return quizzen;
	}

	/**
	 * Functie waarmee men een ArrayList van quizzen kan meegeven aan de
	 * catalogus
	 * 
	 * @param quizLijst
	 *            ArrayList
	 * @throws IllegalArgumentException
	 *             Gooit een exception als de lijst leeg of null is
	 */
	public void setQuizzen(ArrayList<Quiz> quizLijst)
			throws IllegalArgumentException {
		if (quizLijst == null || quizLijst.isEmpty()) {
			throw new IllegalArgumentException(
					"Quizlijst mag niet null of leeg zijn zijn");
		}
		this.quizzen = quizLijst;
	}

	/**
	 * Constructor om een nieuwe QuizCatalogus aan te maken
	 */
	public QuizCatalogus() {
		quizzen = new ArrayList<Quiz>();
	}

	/**
	 * Functie om een enkele quiz toe te voegen aan de catalogus
	 * 
	 * @param quiz
	 *            Quiz
	 * @throws IllegalArgumentException
	 *             gooit een exception als de meegegeven quiz null is of als er
	 *             al een quiz aanwezig is met hetzelfde onderwerp
	 */
	public void voegQuizToe(Quiz quiz) throws IllegalArgumentException {
		if (quiz == null) {
			throw new IllegalArgumentException("quiz mag niet null zijn");
		} else if (bestaatOnderwerp(quiz.getOnderwerp())) {
			throw new IllegalArgumentException("Onderwerp quiz bestaat al");
		}
		quiz.setDatumRegistratie(new DatumGregorian());
		quizzen.add(quiz);
	}

	/**
	 * Functie om een enkele quiz te verwijderen uit de catalogus
	 * 
	 * @param quiz
	 *            Quiz
	 * @throws IllegalArgumentException
	 *             Gooit een exception als de meegegeven quiz null is, als de
	 *             quiz niet aanwezig is of als de status van de quiz
	 *             verwijdering niet toelaat
	 */
	public void verwijderQuiz(Quiz quiz) throws IllegalArgumentException {
		int index = quizzen.indexOf(quiz);
		if (index == -1) {
			throw new IllegalArgumentException("Quiz is niet aanwezig in lijst");
		} else if (quiz.getStatus() != QuizStatus.InConstructie
				&& quiz.getStatus() != QuizStatus.afgewerkt) {
			throw new IllegalArgumentException(
					"Quiz kan niet verwijderd worden wegens status.");
		} else {
			quizzen.remove(index);
		}
	}

	private boolean bestaatOnderwerp(String titel) {
		boolean test = false;
		for (Quiz q : quizzen) {
			if (q.getOnderwerp().compareToIgnoreCase(titel) == 0) {
				test = true;
			}
		}
		return test;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((quizzen == null) ? 0 : quizzen.hashCode());
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
		QuizCatalogus other = (QuizCatalogus) obj;
		if (quizzen == null) {
			if (other.quizzen != null)
				return false;
		} else if (!quizzen.equals(other.quizzen))
			return false;
		return true;
	}

	/**
	 * Functie om een QuizCatalogus te klonen
	 * 
	 * @return QuizCatalogus
	 */
	@Override
	public QuizCatalogus clone() {
		QuizCatalogus catalogusClone = new QuizCatalogus();
		catalogusClone.setQuizzen(this.getQuizzen());
		return catalogusClone;
	}

	@Override
	public Iterator<Quiz> iterator() {
		return quizzen.iterator();
	}
}
