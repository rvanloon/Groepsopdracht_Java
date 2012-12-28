package controller;

import java.io.IOException;

import model.BeheerQuizApplicatie;
import model.Leraar;
import model.Meerkeuze;
import model.Opdracht;
import model.OpdrachtCatalogus;
import model.OpdrachtCategorie;
import model.Opsomming;
import model.Quiz;
import model.QuizCatalogus;
import model.QuizOpdracht;
import model.Reproductie;
import model.SoortenScores;
import utils.Datum;

public class Test {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		//testLezenSchrijven();
		 magweg();
	}

	public static void testLezenSchrijven() throws Exception {
		Opdracht o1 = new Opdracht("aaa", "bbb",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());

		Opdracht o2 = new Opdracht("cccc", "dddd",
				OpdrachtCategorie.FranseTaal, Leraar.Sven, new Datum());
		o2.addAntwoordHint("na de c");
		o2.addAntwoordHint("voor de e");

		Meerkeuze o3 = new Meerkeuze("ooo", "xxx", OpdrachtCategorie.rekenen,
				Leraar.Robrecht, new Datum());
		o3.voegKeuzeToe("YYY");
		o3.voegKeuzeToe("xxx");

		Reproductie o4 = new Reproductie("ppp", OpdrachtCategorie.FranseTaal,
				Leraar.Alain, new Datum(), 4);
		o4.VoegTrefwoordToe("jn");
		o4.VoegTrefwoordToe("ok");
		o4.VoegTrefwoordToe("sdse");

		Opsomming o5 = new Opsomming("lplp", "aaa;bbb;ccc", true,
				OpdrachtCategorie.NederlandseTaal, Leraar.Sven, new Datum());

		OpdrachtCatalogus cat1 = new OpdrachtCatalogus();

		cat1.addOpdracht(o1);
		cat1.addOpdracht(o2);
		cat1.addOpdracht(o3);
		cat1.addOpdracht(o4);
		cat1.addOpdracht(o5);

		Quiz quiz1 = new Quiz("Aardrijkskunde", Leraar.Alain, true, 1, 2, 3);
		Quiz quiz2 = new Quiz("Breiwerk", Leraar.Robrecht, true, 2, 3);

		QuizCatalogus quizCat1 = new QuizCatalogus();
		quizCat1.voegQuizToe(quiz1);
		quizCat1.voegQuizToe(quiz2);

		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, o1, 4);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, o2, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, o3, 3);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz2, o2, 6);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz2, o4, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz2, o5, 4);

		cat1.schrijfCatalogusNaarFile();
		quizCat1.schrijfCatalogusNaarFile();

		OpdrachtCatalogus cat2 = new OpdrachtCatalogus();
		QuizCatalogus quizCat2 = new QuizCatalogus();

		cat2.lezen();
		quizCat2.lezen();

		cat2.schrijfCatalogusNaarFile();
		quizCat2.schrijfCatalogusNaarFile();

		System.out.println(quizCat1.equals(quizCat2));

	}

	public static void magweg() {

		try {
			BeheerQuizApplicatie.setQuizscoreStrategy(SoortenScores.ScoreAntwoordenMetPogingenEnTijd.toString());
			//BeheerQuizApplicatie.magweg("abcde");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
