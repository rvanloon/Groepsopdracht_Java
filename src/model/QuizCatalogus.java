package model;

import java.util.List;
import utils.DatumGregorian;

public class QuizCatalogus {

	private List<Quiz> quizLijst;

	public void voegQuizToe(Quiz quiz) throws IllegalArgumentException {
		if (quiz == null) {
			throw new IllegalArgumentException("quiz mag niet null zijn");
		} else if (bestaatOnderwerp(quiz.getOnderwerp())) {
			throw new IllegalArgumentException("Onderwerp quiz bestaat al");
		}
		quiz.setDatumRegistratie(new DatumGregorian());
		quizLijst.add(quiz);
	}

	public void verwijderQuiz(Quiz quiz) throws IllegalArgumentException {
		int index = quizLijst.indexOf(quiz);
		if (index == -1) {
			throw new IllegalArgumentException("Quiz is niet aanwezig in lijst");
		} else if (quiz.getStatus() != QuizStatus.InConstructie
				|| quiz.getStatus() != QuizStatus.afgewerkt) {
			throw new IllegalArgumentException(
					"Quiz kan niet verwijderd worden wegens status.");
		} else {
			quizLijst.remove(index);
		}
	}

	private boolean bestaatOnderwerp(String titel) {
		boolean test = false;
		for (Quiz q : quizLijst) {
			if (q.getOnderwerp().compareToIgnoreCase(titel) == 0) {
				test = true;
			}
		}
		return test;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
