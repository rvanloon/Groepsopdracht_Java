package testing.modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.Leerling;
import model.Leraar;
import model.Opdracht;
import model.OpdrachtAntwoord;
import model.OpdrachtCategorie;
import model.Quiz;
import model.QuizDeelname;
import model.QuizOpdracht;

import org.junit.Before;
import org.junit.Test;

import utils.Datum;

public class QuizDeelnameTest {
	Leerling leerling;
	Quiz quiz;
	QuizDeelname qd;
	QuizDeelname qd2;

	@Before
	public void setUp() throws Exception {
		leerling = new Leerling("Peter", 4);
		quiz = new Quiz("Hoofdsteden", Leraar.Alain, true, 5);
		Opdracht opdracht = new Opdracht("Hoofdstad Frankrijk", "Parijs",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		Opdracht opdracht2 = new Opdracht("Hoofdstad belgië", "Brussel",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		opdracht.setMaxAntwoordTijd(10);
		opdracht2.setMaxAntwoordTijd(10);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht2, 5);
		QuizDeelname.KoppelLeerlingAanQuiz(quiz, leerling, new Datum(4, 11,
				2012));
		qd = quiz.getQuizDeelnames().get(0);
		OpdrachtAntwoord.koppelOpdrachtAanDeelname("Parijs", 1, 5, quiz
				.getOpdrachten().get(0), qd);
		OpdrachtAntwoord.koppelOpdrachtAanDeelname("Brussel", 2, 5, quiz
				.getOpdrachten().get(1), qd);
		// OpdrachtAntwoord.koppelOpdrachtAanDeelname(laatsteAntwoord,
		// aantalPogingen, antwoordTijd, quizOpdracht, quizDeelname)
	}

	@Test
	public void test_GetLeerling_OK() {
		assertEquals(leerling, qd.getLeerling());
	}

	@Test
	public void test_SetLeerling_Geldige_waarde_Wordt_aanvaard() {
		Leerling l2 = new Leerling("Karel", 1);
		qd.setLeerling(l2);
		assertEquals(l2, qd.getLeerling());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetLeerling_Exception_als_waarde_null() {
		Leerling l2 = null;
		qd.setLeerling(l2);
	}

	@Test
	public void test_GetQuiz_OK() {
		assertEquals(quiz, qd.getQuiz());
	}

	@Test
	public void test_SetQuiz_Correcte_waarde_Wordt_aanvaard() {
		Quiz quiz2 = new Quiz("biologie", Leraar.Alain, true, 4);
		qd.setQuiz(quiz2);
		assertEquals(quiz2, qd.getQuiz());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetQuiz_Exception_als_quiz_null() {
		Quiz quiz2 = null;
		qd.setQuiz(quiz2);
	}

	@Test
	public void test_GetDatumDeelname_OK() {
		Datum d = new Datum(4, 11, 2012);
		assertEquals(d, qd.getDatumDeelname());
	}

	@Test
	public void test_SetDatumDeelname_Geldige_waarde_Wordt_aanvaard() {
		Datum d = new Datum(21, 1, 2012);
		qd.setDatumDeelname(d);
		assertEquals(d, qd.getDatumDeelname());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetDatumDeelname_Exception_als_datum_null() {
		Datum d = null;
		qd.setDatumDeelname(d);
	}

	@Test
	public void test_GetDeelnameScore_OK() {
		// 5 punten op opdracht 1, 2.5 punten op opdracht 2, 7.5/10 afgerond
		// naar acht
		assertEquals(8, qd.getDeelnameScore(), 0.1);
		Opdracht opdracht3 = new Opdracht("Hoofdstad Duitsland", "Berlijn",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht3, 5);
		OpdrachtAntwoord.koppelOpdrachtAanDeelname("Kinshasa", 1, 5, quiz
				.getOpdrachten().get(2), qd);
		// 5 punten opdracht 1, 2.5 punten opdracht 2, 0 opdracht 3, 7.5/15
		assertEquals(5, qd.getDeelnameScore(), 0.1);
		Opdracht opdracht4 = new Opdracht("Hoofdstad Griekenland", "Athene",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht4, 5);
		OpdrachtAntwoord.koppelOpdrachtAanDeelname("Athene", 1, 5, quiz
				.getOpdrachten().get(3), qd);
		// 5 punten opdracht 1, 2.5 punten opdracht 2, 0 opdracht 3, 5 opdracht
		// 4, 12.5/20 = 6
		assertEquals(6, qd.getDeelnameScore(), 0.1);
	}

	@Test
	public void test_EqualsObject_OK() {
		QuizDeelname qd2 = quiz.getQuizDeelnames().get(0);
		assertTrue(qd.equals(qd2));

	}

	@Test
	public void test_EqualsObject_False_als_waarde_null() {
		qd2 = null;
		assertFalse(qd.equals(qd2));
	}

	@Test
	public void test_ToString_OK() {
		assertEquals(
				"QuizDeelname [leerling=Peter, quiz=Hoofdsteden, Alain, InConstructie, datumDeelname=4 november 2012]",
				qd.toString());
	}

	// compareto()

}
