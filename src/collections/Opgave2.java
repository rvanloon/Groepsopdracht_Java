package collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import model.Leraar;
import model.Opdracht;
import model.OpdrachtCategorie;
import model.Quiz;
import model.QuizOpdracht;
import utils.Datum;

public class Opgave2 {

	/**
	 * @param args
	 * 
	 */
	private static void main(String[] args) {
		Opdracht opdracht1 = new Opdracht("Wat is de hoofdstad van Franrijk?",
				"Parijs", OpdrachtCategorie.algemeneKennis, Leraar.Alain,
				new Datum());
		Opdracht opdracht2 = new Opdracht("Wat is de hoodstad van Spanje?",
				"Madrid", OpdrachtCategorie.algemeneKennis, Leraar.Alain,
				new Datum());
		Opdracht opdracht3 = new Opdracht("Wat is de hoofdstad van Italië?",
				"Rome", OpdrachtCategorie.algemeneKennis, Leraar.Alain,
				new Datum());
		Opdracht opdracht4 = new Opdracht("Wat is de hoodstad van Portugal?",
				"Lissabon", OpdrachtCategorie.algemeneKennis, Leraar.Alain,
				new Datum());
		Opdracht opdracht5 = new Opdracht("Hoeveel provincies telt België?",
				"Tien", OpdrachtCategorie.algemeneKennis, Leraar.Alain,
				new Datum());

		Quiz quiz1 = new Quiz("Hoofdsteden Europa", Leraar.Alain, true, 1, 2, 3);
		Quiz quiz2 = new Quiz("Aardrijkskunde", Leraar.Alain, true, 1, 2, 3);
		quiz1.setOnderwerp("Hoofdsteden Europa");
		quiz2.setOnderwerp("Aardrijkskunde");
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, opdracht1, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, opdracht2, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, opdracht3, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, opdracht4, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz2, opdracht1, 4);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz2, opdracht3, 3);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz2, opdracht5, 3);

		ArrayList<QuizOpdracht> opdrachtenQuiz1 = (ArrayList<QuizOpdracht>) quiz1
				.getOpdrachten();
		ArrayList<QuizOpdracht> opdrachtenQuiz2 = (ArrayList<QuizOpdracht>) quiz2
				.getOpdrachten();

		Set<Opdracht> opdrachtenVanQuiz1 = new HashSet<Opdracht>();
		Set<Opdracht> opdrachtenVanQuiz2 = new HashSet<Opdracht>();

		for (QuizOpdracht quizOpdracht : opdrachtenQuiz1) {
			opdrachtenVanQuiz1.add(quizOpdracht.getOpdracht());
		}
		for (QuizOpdracht quizOpdracht : opdrachtenQuiz2) {
			opdrachtenVanQuiz2.add(quizOpdracht.getOpdracht());
		}

		Set<Opdracht> gezamelijkeOpdrachten = new HashSet<Opdracht>(
				opdrachtenVanQuiz1);
		gezamelijkeOpdrachten.retainAll(opdrachtenVanQuiz2);

		System.out.println(gezamelijkeOpdrachten);

	}

}
