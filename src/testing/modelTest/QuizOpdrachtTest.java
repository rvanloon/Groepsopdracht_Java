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

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class QuizOpdrachtTest {

	private Quiz quiz;
	private Opdracht opdracht;
	private int maxScore;
	private QuizOpdracht quizOpdracht;

	@Before
	public void setUp() throws Exception {
		quiz = new Quiz("Belgi�", Leraar.Robrecht, false, 2, 3);
		opdracht = new Opdracht("Welke zee grenst aan Belgi�?", "Noordzee",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum(20,
						10, 2012));
		maxScore = 3;
		quizOpdracht = new QuizOpdracht(maxScore, opdracht, quiz);
	}

	@Test
	public void test_Constructor_OK() {
		QuizOpdracht qo = new QuizOpdracht(maxScore, opdracht, quiz);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_Fout_Quiz_null() {
		QuizOpdracht qo = new QuizOpdracht(maxScore, opdracht, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_Fout_Opdracht_null() {
		QuizOpdracht qo = new QuizOpdracht(maxScore, null, quiz);
	}

	@Test
	public void test_GetMaxScore_OK() {
		assertEquals(quizOpdracht.getMaxScore(), maxScore);
	}

	@Test
	public void test_GetOpdracht_OK() {
		assertEquals(quizOpdracht.getOpdracht(), opdracht);
	}

	@Test
	public void test_GetQuiz_OK() {
		assertEquals(quizOpdracht.getQuiz(), quiz);
	}

	@Test
	public void test_EqualsObject_OK_Gelijk() {
		QuizOpdracht qo = new QuizOpdracht(maxScore, opdracht, quiz);
		assertEquals(quizOpdracht, qo);
	}

	@Test
	public void test_EqualsObject_OK_ScoreNietGelijk() {
		QuizOpdracht qo = new QuizOpdracht(2, opdracht, quiz);
		assertFalse(quizOpdracht.equals(qo));
	}

	// Nog testen op opdracht en quiz niet gelijk TODO
	
	@Test
	public void test_GetGemiddeldeScore_ok() {
		OpdrachtAntwoord antwoord1 = new OpdrachtAntwoord("Noordzee", 1, 5,
				quizOpdracht, new QuizDeelname(new Leerling("fff", 3), quiz,
						new Datum()));
		OpdrachtAntwoord antwoord2 = new OpdrachtAntwoord("xxx", 1, 5,
				quizOpdracht, new QuizDeelname(new Leerling("fff", 3), quiz,
						new Datum()));
		quizOpdracht.getOpdrachtAntwoorden().add(antwoord1);
		quizOpdracht.getOpdrachtAntwoorden().add(antwoord2);
		assertTrue(quizOpdracht.getGemiddeldeScore() == 1.5);

	}
}
