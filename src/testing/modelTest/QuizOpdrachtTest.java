package testing.modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht, maxScore);
		quizOpdracht = quiz.getOpdrachten().get(0);
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
		QuizOpdracht qo = quiz.getOpdrachten().get(0);
		assertEquals(quizOpdracht, qo);
	}

	@Test
	public void test_EqualsObject_OK_ScoreNietGelijk() {

		Opdracht opdracht2 = new Opdracht("Wat is de hoofstad", "Brussel",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum(20,
						10, 2012));
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht2, maxScore);
		QuizOpdracht qo = quiz.getOpdrachten().get(1);
		assertFalse(quizOpdracht.equals(qo));
	}

	// Nog testen op opdracht en quiz niet gelijk TODO

	@Test
	public void test_GetGemiddeldeScore_ok() {
		Leerling leerling = new Leerling("Peter", 4);
		opdracht.setMaxAntwoordTijd(10);
		quiz.setStatus(quiz.getOpengesteld());
		QuizDeelname.KoppelLeerlingAanQuiz(quiz, leerling, new Datum(4, 11,
				2012));
		QuizDeelname qd = quiz.getQuizDeelnames().get(0);
		OpdrachtAntwoord.koppelOpdrachtAanDeelname("Noordzee", 1, 5, quiz
				.getOpdrachten().get(0), qd);
		OpdrachtAntwoord.koppelOpdrachtAanDeelname("Noordzee", 2, 5, quiz
				.getOpdrachten().get(0), qd);
		// 3 punten + 1.5 punten = 4.5/2
		assertEquals(2.25, quizOpdracht.getGemiddeldeScore(), 0);

	}
}
