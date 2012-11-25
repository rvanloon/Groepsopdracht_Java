package collections;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import model.Leraar;
import model.Opdracht;
import model.OpdrachtCategorie;
import model.Quiz;
import model.QuizOpdracht;
import utils.Datum;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class Opgave3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Voorbereiding: een aantal opdrachte en quizen aanmaken en deze
		// koppelen.
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

		Quiz quiz1 = new Quiz("kkk", Leraar.Alain, true, 1, 2, 3);
		Quiz quiz2 = new Quiz("sss", Leraar.Alain, true, 1, 2, 3);
		Quiz quiz3 = new Quiz("aaa", Leraar.Alain, true, 1, 2, 3);
		Quiz quiz4 = new Quiz("zzz", Leraar.Alain, true, 1, 2, 3);
		Quiz quiz5 = new Quiz("bbb", Leraar.Alain, true, 1, 2, 3);

		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, opdracht1, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, opdracht2, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, opdracht3, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, opdracht4, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz2, opdracht1, 4);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz2, opdracht3, 3);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz2, opdracht5, 3);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz3, opdracht1, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz3, opdracht2, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz3, opdracht3, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz4, opdracht4, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz4, opdracht1, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz4, opdracht2, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz5, opdracht1, 2);

		// Een treeMap aanmaken met een specifieke comperator die de quizen
		// volgens de regels ordent.

		Map<Quiz, Quiz> alleQuizen = new TreeMap<Quiz, Quiz>(
				new QuizComparator());
		alleQuizen.put(quiz1, quiz1);
		alleQuizen.put(quiz2, quiz2);
		alleQuizen.put(quiz3, quiz3);
		alleQuizen.put(quiz4, quiz4);
		alleQuizen.put(quiz5, quiz5);

		for (Quiz quiz : alleQuizen.values()) {
			System.out.println("Aantal onderwerpen: "
					+ quiz.getOpdrachten().size() + ", onderwerp: "
					+ quiz.getOnderwerp());
		}

		System.out.println();
		// zelfde, maar nu met een treeSet
		Set<Quiz> alleQuizenSet = new TreeSet<Quiz>(new QuizComparator());
		alleQuizenSet.add(quiz1);
		alleQuizenSet.add(quiz2);
		alleQuizenSet.add(quiz3);
		alleQuizenSet.add(quiz4);
		alleQuizenSet.add(quiz5);

		for (Quiz quizS : alleQuizenSet) {
			System.out.println("Aantal onderwerpen: "
					+ quizS.getOpdrachten().size() + ", onderwerp: "
					+ quizS.getOnderwerp());
		}

	}

}
