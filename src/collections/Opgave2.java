package collections ;
import model.*;

import java.util.*;

import utils.Datum;

public class Opgave2 {

	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		Opdracht opdracht1 = new Opdracht("Wat is de hoofdstad van Franrijk?","Parijs",OpdrachtCategorie.algemeneKennis,Leraar.Alain,new Datum());
		Opdracht opdracht2 = new Opdracht("Wat is de hoodstad van Spanje?","Madrid",OpdrachtCategorie.algemeneKennis,Leraar.Alain,new Datum());
		Opdracht opdracht3 = new Opdracht("Wat is de hoofdstad van Italië?","Rome",OpdrachtCategorie.algemeneKennis,Leraar.Alain,new Datum());
		Opdracht opdracht4 = new Opdracht("Wat is de hoodstad van Portugal?","Lissabon",OpdrachtCategorie.algemeneKennis,Leraar.Alain,new Datum());
		Opdracht opdracht5 = new Opdracht("Hoeveel provincies telt België?","Tien",OpdrachtCategorie.algemeneKennis,Leraar.Alain,new Datum());
		
		Quiz quiz1 = new Quiz("Hoofdsteden Europa",Leraar.Alain, true, 1, 2, 3);
		Quiz quiz2 = new Quiz("Aardrijkskunde",Leraar.Alain, true, 1, 2, 3);
		quiz1.setOnderwerp("Hoofdsteden Europa");
		quiz2.setOnderwerp("Aardrijkskunde");
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, opdracht1, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, opdracht2, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, opdracht3, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, opdracht4, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz2, opdracht1, 4);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz2, opdracht3, 3);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz2, opdracht5, 3);
		
		ArrayList <QuizOpdracht> opdrachtenQuiz1 = (ArrayList<QuizOpdracht>) quiz1.getOpdrachten();
		ArrayList <QuizOpdracht> opdrachtenQuiz2 = (ArrayList<QuizOpdracht>) quiz2.getOpdrachten();
		Set <QuizOpdracht> opdrachtenVanQuiz1 = new HashSet<QuizOpdracht>(opdrachtenQuiz1);
		Set <QuizOpdracht> opdrachtenVanQuiz2 = new HashSet<QuizOpdracht>(opdrachtenQuiz2);
		
		Set <QuizOpdracht> gezamelijkeOpdrachten = new HashSet<QuizOpdracht>(opdrachtenVanQuiz1);
		gezamelijkeOpdrachten.retainAll(opdrachtenVanQuiz2);
		
		System.out.println(gezamelijkeOpdrachten);
	

	}

}
