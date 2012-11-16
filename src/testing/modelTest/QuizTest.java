package testing.modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import model.Leraar;
import model.Opdracht;
import model.OpdrachtCategorie;
import model.Quiz;
import model.QuizOpdracht;
import model.QuizStatus;

import org.junit.Before;
import org.junit.Test;

import utils.Datum;

public class QuizTest {
	private Quiz quiz;
	private Quiz quiz2;
	private Quiz quiz3;
	private Quiz quiz4;
	private ArrayList<Integer> list;
	Opdracht opdracht;
	Opdracht nieuweOpdracht;
	Datum datum;
	QuizOpdracht qo;
	QuizOpdracht qo2;
	ArrayList<QuizOpdracht> qoList;

	@Before
	public void setUp() throws Exception {
		quiz = new Quiz("Aardrijkskunde", Leraar.Alain, true, 1, 2, 3);
		quiz2 = new Quiz("Breiwerk", Leraar.Robrecht, true, 2, 3);
		quiz2.setStatus(QuizStatus.afgewerkt);
		quiz3 = new Quiz("Zorro", Leraar.Sven, false, 4, 5, 6);
		quiz3.setStatus(QuizStatus.laatsteKans);
		list = new ArrayList<Integer>();
		datum = new Datum();
		opdracht = new Opdracht("De hoofdstad van Frankrijk", "Parijs",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum(21,
						1, 2012));
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht, 5);
		nieuweOpdracht = new Opdracht("De hoofdstad van Belgie", "Brussel",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum(22,
						1, 2012));
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz, nieuweOpdracht, 5);
		qoList = new ArrayList<QuizOpdracht>();
	}

	@Test
	public void test_GetOnderwerp_OK() {
		assertEquals("Aardrijkskunde", quiz.getOnderwerp());
		assertEquals("Breiwerk", quiz2.getOnderwerp());
		assertEquals("Zorro", quiz3.getOnderwerp());
	}

	@Test
	public void test_SetOnderwerp_Correcte_waarde_Wordt_aanvaard() {
		quiz.setOnderwerp("Geografie");
		assertEquals("Geografie", quiz.getOnderwerp());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetOnderwerp_Exception_Als_onderwerp_null() {
		String onderwerp = null;
		quiz.setOnderwerp(onderwerp);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetOnderwerp_Exception_Als_onderwerp_leeg() {
		String onderwerp = "";
		quiz.setOnderwerp(onderwerp);
	}

	@Test
	public void test_GetLeerjaren_OK() {
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals(list, quiz.getLeerjaren());
		list.clear();
		list.add(2);
		list.add(3);
		assertEquals(list, quiz2.getLeerjaren());
	}

	@Test
	public void test_SetLeerjaren_Correcte_waarde_Wordt_aanvaard() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		quiz.setLeerjaren(1, 2, 3, 4, 5, 6);
		assertEquals(list, quiz.getLeerjaren());
		int[] intArray = { 1, 2, 3 };
		quiz.setLeerjaren(intArray);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetLeerjaren_Exception_Als_waarde_te_groot_of_te_klein() {
		quiz.setLeerjaren(0); // Hier stopt het al. Volgende wordt nooit
								// uitgevoerd
		quiz.setLeerjaren(-5);
		quiz.setLeerjaren(7);
		quiz.setLeerjaren(1, 5, 7);
		int[] intArray = { 5, 6, 7 };
		quiz.setLeerjaren(intArray);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetLeerjaren_Exception_Als_invoer_null() {
		int[] intArray = null;
		quiz.setLeerjaren(intArray);
	}

	@Test
	public void test_GetAuteur() {
		assertEquals(Leraar.Alain, quiz.getAuteur());
		assertEquals(Leraar.Robrecht, quiz2.getAuteur());
		assertEquals(Leraar.Sven, quiz3.getAuteur());
	}

	@Test
	public void test_SetAuteur_Correcte_waarde_Wordt_aanvaard() {
		quiz.setAuteur(Leraar.Robrecht);
		assertEquals(Leraar.Robrecht, quiz.getAuteur());
	}

	@Test
	public void test_GetIsTest_OK() {
		assertTrue(quiz.getIsTest());
		assertTrue(quiz2.getIsTest());
		assertFalse(quiz3.getIsTest());
	}

	@Test
	public void test_SetIsTest_Correcte_waarde_Wordt_aanvaard() {
		quiz.setIsTest(false);
		assertFalse(quiz.getIsTest());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetIsTest_Exception_Als_waarde_null() {
		Boolean test = null;
		quiz.setIsTest(test);
	}

	@Test
	public void test_GetDatumRegistratie() {
		quiz.setDatumRegistratie(datum);
		assertEquals(datum, quiz.getDatumRegistratie());
	}

	@Test
	public void test_SetDatumRegistratie_Correcte_waarde_Wordt_aanvaard() {
		datum = new Datum(15, 04, 2012);
		quiz.setDatumRegistratie(datum);
		assertEquals(datum, quiz.getDatumRegistratie());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetDatumRegistratie_Exception_Als_datum_null() {
		datum = null;
		quiz.setDatumRegistratie(datum);
	}

	@Test
	public void test_GetStatus_OK() {
		assertEquals(QuizStatus.InConstructie, quiz.getStatus());
		assertEquals(QuizStatus.afgewerkt, quiz2.getStatus());
		assertEquals(QuizStatus.laatsteKans, quiz3.getStatus());
	}

	@Test
	public void test_SetStatus_Correcte_waarde_Wordt_aanvaard() {
		quiz.setStatus(QuizStatus.opengesteld);
		assertEquals(QuizStatus.opengesteld, quiz.getStatus());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetStatus_Exception_Als_waarde_null() {
		QuizStatus status = null;
		quiz.setStatus(status);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetOpdrachten_Exception_Indien_waarde_null() {
		qoList = null;
		quiz2.setOpdrachten(qoList);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetOpdrachten_Exception_Als_lijst_leeg() {
		quiz2.setOpdrachten(qoList);
	}

	@Test
	public void test_Constructor_Correcte_waarden_Wordt_aanvaard() {
		quiz4 = new Quiz("Biologie", Leraar.Alain, true, 4, 5, 6);
		assertEquals("Biologie", quiz4.getOnderwerp());
		assertEquals(Leraar.Alain, quiz4.getAuteur());
		assertTrue(quiz4.getIsTest());
		list.add(4);
		list.add(5);
		list.add(6);
		assertEquals(list, quiz4.getLeerjaren());
		assertEquals(QuizStatus.InConstructie, quiz4.getStatus());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_Exception_Als_onderwerp_null() {
		String onderwerp = null;
		quiz4 = new Quiz(onderwerp, Leraar.Alain, true, 4, 5, 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_Exception_Als_onderwerp_leeg() {
		String onderwerp = "";
		quiz4 = new Quiz(onderwerp, Leraar.Alain, true, 4, 5, 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_Exception_Als_auteur_null() {
		Leraar auteur = null;
		quiz4 = new Quiz("Aardrijkskunde", auteur, true, 4, 5, 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_Exception_Als_test_null() {
		Boolean test = null;
		quiz4 = new Quiz("Aardrijkskunde", Leraar.Alain, test, 4, 5, 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_Exception_Als_leerjaar_te_groot() {
		quiz4 = new Quiz("Aardrijkskunde", Leraar.Alain, true, 5, 6, 7);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_Exception_Als_leerjaar_te_klein() {
		quiz4 = new Quiz("Aardrijkskunde", Leraar.Alain, true, 0, 1, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_Exception_Als_leerjaar_null() {
		int[] jaren = null;
		quiz4 = new Quiz("Aardrijkskunde", Leraar.Alain, true, jaren);
	}

	@Test
	public void test_Constructor_Dubbele_waarde_leerjaar_Wordt_aanvaard() {
		quiz4 = new Quiz("Aardrijkskunde", Leraar.Alain, true, 4, 4, 5, 6);
		list.add(4);
		list.add(5);
		list.add(6);
		assertEquals(list, quiz4.getLeerjaren());
	}

	@Test
	public void test_ToString() {
		assertEquals("Aardrijkskunde, Alain, InConstructie", quiz.toString());
	}

	@Test
	public void test_EqualsObject_Gelijke_waarde_Wordt_aanvaard() {
		Quiz quiz5 = quiz.clone();
		assertTrue(quiz.equals(quiz5));
	}

	@Test
	public void test_EqualsObject_Ongelijke_waarde_Wordt_aanvaard() {
		assertFalse(quiz.equals(quiz2));
	}

	@Test
	public void test_EqualsObject_False_Indien_waarde_null() {
		quiz2 = null;
		assertFalse(quiz.equals(quiz2));
	}

	@Test
	public void test_CompareTo_OK() {
		assertTrue((quiz.compareTo(quiz2) > 0));
		assertTrue((quiz2.compareTo(quiz) < 0));
		quiz2 = quiz.clone();
		assertTrue((quiz.compareTo(quiz2) == 0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_CompareTo_Exception_Als_waarde_null() {
		quiz2 = null;
		quiz.compareTo(quiz2);
	}

	@Test
	public void testClone_OK() {
		quiz2 = quiz.clone();
		assertEquals(quiz, quiz2);
	}

}
