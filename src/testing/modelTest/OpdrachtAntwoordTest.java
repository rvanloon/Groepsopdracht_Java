package testing.modelTest;

import static org.junit.Assert.assertEquals;
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

public class OpdrachtAntwoordTest {
	private String antwoord;
	private int pogingen;
	private int tijd;
	private QuizOpdracht quizOpdracht;
	private QuizDeelname quizDeelname;
	private OpdrachtAntwoord opdrachtAntwoord;
	private OpdrachtAntwoord opdrachtAntwoord2;
	private QuizOpdracht qOpdracht2;

	@Before
	public void setUp() throws Exception {
		antwoord = "Parijs";
		pogingen = 2;
		tijd = 5;
		Leerling leerling = new Leerling("Mathias", 5);
		Opdracht opdracht = new Opdracht("Hoofdstad Frankrijk", "Parijs",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		opdracht.setMaxAntwoordTijd(10);
		Quiz quiz = new Quiz("Hoofdsteden", Leraar.Alain, true, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht, 5);
		quiz.setStatus(quiz.getOpengesteld());
		QuizDeelname.KoppelLeerlingAanQuiz(quiz, leerling, new Datum());
		quizDeelname = quiz.getQuizDeelnames().get(0);
		quizOpdracht = quiz.getOpdrachten().get(0);
		;
		OpdrachtAntwoord.koppelOpdrachtAanDeelname(antwoord, pogingen, tijd,
				quizOpdracht, quizDeelname);
		opdrachtAntwoord = quizOpdracht.getOpdrachtAntwoorden().get(0);
	}

	@Test
	public void test_GetLaatsteAntwoord_OK() {
		assertEquals("Parijs", opdrachtAntwoord.getLaatsteAntwoord());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetLaatsteAntwoord_Exception_als_antwoord_null() {
		antwoord = null;
		opdrachtAntwoord.setLaatsteAntwoord(antwoord);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetLaatsteAntwoord_Exception_als_antwoord_leeg() {
		antwoord = "";
		opdrachtAntwoord.setLaatsteAntwoord(antwoord);
	}

	@Test
	public void test_GetAantalPogingen_OK() {
		assertEquals(2, opdrachtAntwoord.getAantalPogingen());
	}

	@Test
	public void test_SetAantalPogingen_Geldige_waarde_Wordt_aanvaard() {
		pogingen = 5;
		opdrachtAntwoord.setAantalPogingen(pogingen);
		assertEquals(5, opdrachtAntwoord.getAantalPogingen());
		pogingen = 1;
		opdrachtAntwoord.setAantalPogingen(pogingen);
		assertEquals(1, opdrachtAntwoord.getAantalPogingen());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetAantalPogingen_Exception_als_waarde_negatief() {
		pogingen = -5;
		opdrachtAntwoord.setAantalPogingen(pogingen);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetAantalPogingen_Exception_als_waarde_nul() {
		pogingen = 0;
		opdrachtAntwoord.setAantalPogingen(pogingen);
	}

	@Test
	public void test_GetAntwoordTijd_OK() {
		assertEquals(5, opdrachtAntwoord.getAntwoordTijd());
	}

	@Test
	public void test_SetAntwoordTijd_Geldige_waarde_Wordt_aanvaard() {
		tijd = 1;
		opdrachtAntwoord.setAntwoordTijd(tijd);
		assertEquals(1, opdrachtAntwoord.getAntwoordTijd());
		tijd = 10;
		opdrachtAntwoord.setAntwoordTijd(tijd);
		assertEquals(10, opdrachtAntwoord.getAntwoordTijd());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetAntwoordTijd_Exception_Als_waarde_negatief() {
		tijd = -1;
		opdrachtAntwoord.setAntwoordTijd(tijd);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetAntwoordTijd_Exception_Als_waarde_nul() {
		tijd = 0;
		opdrachtAntwoord.setAntwoordTijd(tijd);
	}

	@Test
	public void test_GetQuizopdracht_OK() {
		assertEquals(quizOpdracht, opdrachtAntwoord.getQuizopdracht());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetQuizopdracht_Exception_Als_waarde_null() {
		qOpdracht2 = null;
		opdrachtAntwoord.setQuizopdracht(qOpdracht2);
	}

	@Test
	public void test_GetQuizdeelname_OK() {
		assertEquals(quizDeelname, opdrachtAntwoord.getQuizdeelname());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetQuizdeelname_Exception_Als_waarde_null() {
		QuizDeelname qd2 = null;
		opdrachtAntwoord.setQuizdeelname(qd2);
	}

	@Test
	public void test_GetOpdrachtScore_Maximum_score_juist_antwoord_1_poging() {
		opdrachtAntwoord.setAantalPogingen(1);
		assertEquals(5, opdrachtAntwoord.GetOpdrachtScore(), 0.1);
	}

	@Test
	public void test_GetOpdrachtScore_Halve_score_juist_antwoord_meerdere_pogingen() {
		opdrachtAntwoord.setAantalPogingen(2);
		assertEquals(2.5, opdrachtAntwoord.GetOpdrachtScore(), 0.1);
	}

	@Test
	public void test_GetOpdrachtScore_Geen_score_fout_antwoord() {
		opdrachtAntwoord.setLaatsteAntwoord("Frankrijk");
		assertEquals(0, opdrachtAntwoord.GetOpdrachtScore(), 0.1);
	}

	@Test
	public void test_EqualsObject_OK() {
		opdrachtAntwoord2 = quizOpdracht.getOpdrachtAntwoorden().get(0);
		assertTrue(opdrachtAntwoord.equals(opdrachtAntwoord2));
	}

	@Test
	public void test_ToString() {
		assertEquals("Parijs", opdrachtAntwoord.toString());
	}

	@Test
	public void test_CompareTo() {
		opdrachtAntwoord2 = quizOpdracht.getOpdrachtAntwoorden().get(0);
		assertTrue(opdrachtAntwoord.compareTo(opdrachtAntwoord2) == 0);
	}

}
