package collections;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import utils.Datum;
import view.IO;

import model.Leraar;
import model.Opdracht;
import model.OpdrachtCategorie;
import model.Quiz;
import model.QuizOpdracht;

public class SetOefening {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Opdracht o1 = new Opdracht("Vraag1", "Antwoord1", OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		Opdracht o2 = new Opdracht("Vraag2", "Antwoord2", OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		Opdracht o3 = new Opdracht("Vraag3", "Antwoord3", OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		Opdracht o4 = new Opdracht("Vraag4", "Antwoord4", OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		Opdracht o5 = new Opdracht("Vraag5", "Antwoord5", OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		Opdracht o6 = new Opdracht("Vraag6", "Antwoord6", OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		Quiz q1 = new Quiz("Vragen1", Leraar.Alain, true, 4);
		Quiz q2 = new Quiz("Vragen2", Leraar.Robrecht, true, 4);
		QuizOpdracht.koppelOpdrachtAanQuiz(q1, o1, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q1, o2, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q1, o3, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q1, o4, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q2, o3, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q2, o4, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q2, o5, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q2, o6, 5);
		HashSet<Opdracht> opdrachtenQuiz1 = new HashSet<Opdracht>();
		HashSet<Opdracht> opdrachtenQuiz2 = new HashSet<Opdracht>();
		for(QuizOpdracht o : q1.getOpdrachten()){
			opdrachtenQuiz1.add(o.getOpdracht());
		}
		for(QuizOpdracht o : q2.getOpdrachten()){
			opdrachtenQuiz2.add(o.getOpdracht());
		}
		IO.toonStringMetVenster(toonUnie(opdrachtenQuiz1, opdrachtenQuiz2));

	}
	
	public static String toonUnie(Set<Opdracht> set1, Set<Opdracht> set2){
		HashSet<Opdracht> opdrachtenUnie = new HashSet<Opdracht>();
		for (Opdracht o : set1){
			opdrachtenUnie.add(o);
		}
		opdrachtenUnie.retainAll(set2);
		String opdrachten = "";
		Iterator<Opdracht> itr = opdrachtenUnie.iterator();
		while(itr.hasNext()){
			opdrachten += itr.next().toString() + ", ";
		}
		return opdrachten.length() > 2 ? opdrachten.substring(0, opdrachten.length() -2) : opdrachten;
	}

}
