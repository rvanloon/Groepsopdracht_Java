package collections;

import java.util.SortedSet;
import java.util.TreeSet;

import model.Leraar;
import model.Opdracht;
import model.OpdrachtCategorie;
import model.Quiz;
import model.QuizOpdracht;
import utils.Datum;
import view.IO;

public class TreeMapOef {

	/**
	 * @param args
	 */
	private static void main(String[] args) {
		Opdracht o1 = new Opdracht("Vraag1", "Antwoord1",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		Opdracht o2 = new Opdracht("Vraag2", "Antwoord2",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		Opdracht o3 = new Opdracht("Vraag3", "Antwoord3",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		Opdracht o4 = new Opdracht("Vraag4", "Antwoord4",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		Opdracht o5 = new Opdracht("Vraag5", "Antwoord5",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		Opdracht o6 = new Opdracht("Vraag6", "Antwoord6",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		Quiz q1 = new Quiz("Vragen1", Leraar.Alain, true, 4);
		Quiz q2 = new Quiz("Vragen2", Leraar.Robrecht, true, 4);
		Quiz q3 = new Quiz("Vragen3", Leraar.Alain, true, 4);
		Quiz q4 = new Quiz("Vragen4", Leraar.Robrecht, true, 4);
		Quiz q5 = new Quiz("Aardrijkskunde", Leraar.Robrecht, true, 4);
		QuizOpdracht.koppelOpdrachtAanQuiz(q1, o1, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q1, o2, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q1, o3, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q1, o4, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q2, o3, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q2, o4, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q2, o5, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q2, o6, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q3, o5, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q4, o6, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q4, o3, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q5, o6, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q5, o3, 5);
		SortedSet<Quiz> quizzen = new TreeSet<Quiz>(new QuizComparator2());
		quizzen.add(q1);
		quizzen.add(q2);
		quizzen.add(q3);
		quizzen.add(q4);
		quizzen.add(q5);
		for (Quiz q : quizzen) {
			IO.toonString("Size: " + q.getOpdrachten().size() + ", onderwerp: "
					+ q.getOnderwerp() + " / ");
		}
	}

}
