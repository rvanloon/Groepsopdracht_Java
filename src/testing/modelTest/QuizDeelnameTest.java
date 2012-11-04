package testing.modelTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
	ArrayList<OpdrachtAntwoord> listOpdrachtAntwoord;

	@Before
	public void setUp() throws Exception {
		leerling = new Leerling("Peter", 4);
		quiz = new Quiz("Hoofdsteden", Leraar.Alain, true, 5);
		qd = new QuizDeelname(leerling, quiz, new Datum(4,11,2012));
		qd2 = new QuizDeelname(Leraar.Alain, quiz, new Datum(4,11,2012));
		listOpdrachtAntwoord = new ArrayList<OpdrachtAntwoord>();
		Opdracht opdracht = new Opdracht("Hoofdstad Frankrijk", "Parijs", OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		Opdracht opdracht2 = new Opdracht("Hoofdstad belgië", "Brussel", OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		opdracht.setMaxAntwoordTijd(10);
		opdracht2.setMaxAntwoordTijd(10);
		QuizOpdracht qOpdracht = new QuizOpdracht(10, opdracht, quiz);
		QuizOpdracht qOpdracht2 = new QuizOpdracht(10, opdracht2, quiz);
		OpdrachtAntwoord opdrachtAntwoord = new OpdrachtAntwoord("Parijs", 1, 5, qOpdracht, qd);
		OpdrachtAntwoord opdrachtAntwoord2 = new OpdrachtAntwoord("Brussel", 2, 5, qOpdracht2, qd);
		listOpdrachtAntwoord.add(opdrachtAntwoord);
		listOpdrachtAntwoord.add(opdrachtAntwoord2);
		qd.setOpdrachtAntwoorden(listOpdrachtAntwoord);
		ArrayList<OpdrachtAntwoord> list2 = new ArrayList<OpdrachtAntwoord>();
		list2.add(opdrachtAntwoord2);
		qd2.setOpdrachtAntwoorden(list2);
	}

	@Test
	public void test_Constructor_leerling_quiz_datum_Geldige_waarde_Wordt_aanvaard() {
		qd = new QuizDeelname(leerling, quiz, new Datum());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_leerling_quiz_datum_Exception_als_leerling_null() {
		leerling = null;
		qd = new QuizDeelname(leerling, quiz, new Datum());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_leerling_quiz_datum_Exception_als_quiz_null() {
		quiz = null;
		qd = new QuizDeelname(leerling, quiz, new Datum());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_leerling_quiz_datum_Exception_als_datum_null() {
		Datum datum = null;
		qd = new QuizDeelname(leerling, quiz, datum);
	}

	@Test
	public void test_constructor_leraar_quiz_datum_Geldige_waarden_Worden_aanvaard() {
		qd = new QuizDeelname(Leraar.Alain, quiz, new Datum());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_constructor_leraar_quiz_datum_Exception_als_leraar_null() {
		Leraar leraar = null;
		qd = new QuizDeelname(leraar, quiz, new Datum());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_constructor_leraar_quiz_datum_Exception_als_quiz_null() {
		quiz = null;
		qd = new QuizDeelname(Leraar.Alain, quiz, new Datum());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_constructor_leraar_quiz_datum_Exception_als_datum_null() {
		Datum datum = null;
		qd = new QuizDeelname(Leraar.Alain, quiz, datum);
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
	
	@Test(expected = IllegalArgumentException.class)
	public void test_SetLeerling_Exception_als_leraar_ingevuld() {
		qd2.setLeerling(leerling);
	}

	@Test
	public void test_GetLeraar_OK() {
		assertEquals(Leraar.Alain, qd2.getLeraar());
	}

	@Test
	public void test_SetLeraar_Geldige_waarde_Wordt_Aanvaard() {
		qd2.setLeraar(Leraar.Robrecht);
		assertEquals(Leraar.Robrecht, qd2.getLeraar());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_SetLeraar_Exception_als_waarde_null() {
		Leraar leraar = null;
		qd2.setLeraar(leraar);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_SetLeraar_Exception_als_leerling_ingevuld() {
		qd.setLeraar(Leraar.Alain);
	}

	@Test
	public void test_GetQuiz_OK() {
		assertEquals(quiz, qd.getQuiz());
		assertEquals(quiz, qd2.getQuiz());
	}

	@Test
	public void test_SetQuiz_Correcte_waarde_Wordt_aanvaard() {
		Quiz quiz2 = new Quiz("biologie", Leraar.Alain, true, 4);
		qd.setQuiz(quiz2);
		assertEquals(quiz2, qd.getQuiz());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_SetQuiz_Exception_als_quiz_null() {
		Quiz quiz2 = null;
		qd.setQuiz(quiz2);
	}

	@Test
	public void test_GetDatumDeelname_OK() {
		Datum d = new Datum(4,11,2012);
		assertEquals(d, qd.getDatumDeelname());
	}

	@Test
	public void test_SetDatumDeelname_Geldige_waarde_Wordt_aanvaard() {
		Datum d = new Datum(21,1,2012);
		qd.setDatumDeelname(d);
		assertEquals(d, qd.getDatumDeelname());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_SetDatumDeelname_Exception_als_datum_null() {
		Datum d = null;
		qd.setDatumDeelname(d);
	}

	@Test
	public void test_GetOpdrachtAntwoorden_OK() {
		qd.setOpdrachtAntwoorden(listOpdrachtAntwoord);
		assertEquals(listOpdrachtAntwoord, qd.getOpdrachtAntwoorden());
	}

	@Test
	public void test_SetOpdrachtAntwoorden_Geldige_waarde_Wordt_aanvaard() {
		qd.setOpdrachtAntwoorden(listOpdrachtAntwoord);
		assertEquals(listOpdrachtAntwoord, qd.getOpdrachtAntwoorden());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_SetOpdrachtAntwoorden_Exception_als_opdrachten_null() {
		listOpdrachtAntwoord = null;
		qd.setOpdrachtAntwoorden(listOpdrachtAntwoord);
	}

	@Test
	public void test_GetDeelnameScore_OK() {
		// 10 punten op opdracht 1, 5 punten op opdracht 2, 15/20, 7,5/10 afgerond naar acht

		assertEquals(8, qd.getDeelnameScore(), 0.1);
	}

	@Test
	public void test_EqualsObject_OK() {
		assertFalse(qd.equals(qd2));
		qd2 = new QuizDeelname(leerling, quiz, new Datum(4,11,2012));
		qd2.setOpdrachtAntwoorden(listOpdrachtAntwoord);
		assertTrue(qd.equals(qd2));
		
	}
	
	@Test
	public void test_EqualsObject_False_als_waarde_null() {
		qd2 = null;
		assertFalse(qd.equals(qd2));
	}
	
	@Test
	public void test_ToString_OK() {
		assertEquals("QuizDeelname [leerling=Peter, leraar=null, quiz=Hoofdsteden, Alain, InConstructie, datumDeelname=4 november 2012]", qd.toString());
	}

	@Test
	public void test_CompareTo_OK() {
		assertTrue(qd.compareTo(qd2)>0);
		assertTrue(qd2.compareTo(qd)<0);
		assertTrue(qd.compareTo(qd)==0);
	}

}
