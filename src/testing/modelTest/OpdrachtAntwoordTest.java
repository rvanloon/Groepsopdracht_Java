package testing.modelTest;

import static org.junit.Assert.*;

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
	private QuizOpdracht qOpdracht;
	private QuizDeelname qDeelname;
	private OpdrachtAntwoord opdrachtAntwoord;
	private OpdrachtAntwoord opdrachtAntwoord2;
	private QuizOpdracht qOpdracht2;

	@Before
	public void setUp() throws Exception {
		antwoord = "Parijs";
		pogingen = 2;
		tijd = 5;		
		Leerling leerling = new Leerling("Mathias", 5);
		Opdracht opdracht = new Opdracht("Hoofdstad Frankrijk", "Parijs", OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		opdracht.setMaxAntwoordTijd(10);
		Quiz quiz = new Quiz("Hoofdstaden", Leraar.Alain, true, 5);
		qOpdracht = new QuizOpdracht(10, opdracht, quiz);
		qDeelname = new QuizDeelname(leerling, quiz, new Datum());
		opdrachtAntwoord = new OpdrachtAntwoord(antwoord, pogingen, tijd, qOpdracht, qDeelname);
	}

	@Test
	public void test_Constructor_Correcte_waarden_Wordt_aanvaard() {
		opdrachtAntwoord = new OpdrachtAntwoord(antwoord, pogingen, tijd, qOpdracht, qDeelname);
		assertEquals(antwoord, opdrachtAntwoord.getLaatsteAntwoord());
		assertEquals(pogingen, opdrachtAntwoord.getAantalPogingen());
		assertEquals(tijd, opdrachtAntwoord.getAntwoordTijd());
		assertEquals(qOpdracht, opdrachtAntwoord.getQuizopdracht());
		assertEquals(qOpdracht, opdrachtAntwoord.getQuizopdracht());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_Exception_Als_antwoord_null() {
		antwoord = null;
		opdrachtAntwoord = new OpdrachtAntwoord(antwoord, pogingen, tijd, qOpdracht, qDeelname);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_Exception_Als_antwoord_leeg() {
		antwoord = "";
		opdrachtAntwoord = new OpdrachtAntwoord(antwoord, pogingen, tijd, qOpdracht, qDeelname);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_Exception_Als_pogingen_negatief() {
		pogingen = -5;
		opdrachtAntwoord = new OpdrachtAntwoord(antwoord, pogingen, tijd, qOpdracht, qDeelname);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_Exception_Als_pogingen_nul() {
		pogingen = 0;
		opdrachtAntwoord = new OpdrachtAntwoord(antwoord, pogingen, tijd, qOpdracht, qDeelname);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_Exception_Als_tijd_nul() {
		tijd = 0;
		opdrachtAntwoord = new OpdrachtAntwoord(antwoord, pogingen, tijd, qOpdracht, qDeelname);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_Exception_Als_tijd_negatief() {
		tijd = -5;
		opdrachtAntwoord = new OpdrachtAntwoord(antwoord, pogingen, tijd, qOpdracht, qDeelname);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_Exception_Als_QuizOpdracht_null() {
		qOpdracht = null;
		opdrachtAntwoord = new OpdrachtAntwoord(antwoord, pogingen, tijd, qOpdracht, qDeelname);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_Exception_Als_QuizDeelname_null() {
		qDeelname = null;
		opdrachtAntwoord = new OpdrachtAntwoord(antwoord, pogingen, tijd, qOpdracht, qDeelname);
	}
	
	@Test
	public void test_GetLaatsteAntwoord_OK() {
		assertEquals("Parijs", opdrachtAntwoord.getLaatsteAntwoord());
	}

	@Test (expected = IllegalArgumentException.class)
	public void test_SetLaatsteAntwoord_Exception_als_antwoord_null() {
		antwoord = null;
		opdrachtAntwoord.setLaatsteAntwoord(antwoord);
	}
	
	@Test (expected = IllegalArgumentException.class)
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

	@Test (expected = IllegalArgumentException.class)
	public void test_SetAantalPogingen_Exception_als_waarde_negatief() {
		pogingen = -5;
		opdrachtAntwoord.setAantalPogingen(pogingen);
	}
	
	@Test (expected = IllegalArgumentException.class)
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
	
	@Test (expected = IllegalArgumentException.class)
	public void test_SetAntwoordTijd_Exception_Als_waarde_negatief() {
		tijd = -1;
		opdrachtAntwoord.setAntwoordTijd(tijd);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_SetAntwoordTijd_Exception_Als_waarde_nul() {
		tijd = 0;
		opdrachtAntwoord.setAntwoordTijd(tijd);
	}

	@Test
	public void test_GetQuizopdracht_OK() {
		assertEquals(qOpdracht, opdrachtAntwoord.getQuizopdracht());
	}

	@Test
	public void test_SetQuizopdracht_Geldige_waarde_Wordt_aanvaard() {
		Opdracht opdracht2 = new Opdracht("Hoofdstad België", "Brussel", OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		Quiz quiz2 = new Quiz("Hoofdsteden", Leraar.Robrecht, true, 5);
		qOpdracht2 = new QuizOpdracht(5, opdracht2, quiz2);
		opdrachtAntwoord.setQuizopdracht(qOpdracht2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_SetQuizopdracht_Exception_Als_waarde_null() {
		qOpdracht2 = null;
		opdrachtAntwoord.setQuizopdracht(qOpdracht2);
	}

	@Test
	public void test_GetQuizdeelname_OK() {
		assertEquals(qDeelname, opdrachtAntwoord.getQuizdeelname());
	}

	@Test
	public void test_SetQuizdeelname_Geldige_waarde_Wordt_aanvaard() {
		Leerling l2 = new Leerling("Peter", 5);
		Quiz q2 = new Quiz("Badsteden", Leraar.Alain, true, 5);
		QuizDeelname qd2 = new QuizDeelname(l2, q2, new Datum());
		opdrachtAntwoord.setQuizdeelname(qd2);
		assertEquals(qd2, opdrachtAntwoord.getQuizdeelname());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_SetQuizdeelname_Exception_Als_waarde_null() {
		QuizDeelname qd2 = null;
		opdrachtAntwoord.setQuizdeelname(qd2);
	}

	@Test
	public void test_GetOpdrachtScore_Maximum_score_juist_antwoord_één_poging() {
		opdrachtAntwoord.setAantalPogingen(1);
		assertEquals(10, opdrachtAntwoord.GetOpdrachtScore(), 0.1);
	}
	
	@Test
	public void test_GetOpdrachtScore_Halve_score_juist_antwoord_meerdere_pogingen() {
		opdrachtAntwoord.setAantalPogingen(2);
		assertEquals(5, opdrachtAntwoord.GetOpdrachtScore(), 0.1);
	}
	
	@Test
	public void test_GetOpdrachtScore_Geen_score_fout_antwoord() {
		opdrachtAntwoord.setLaatsteAntwoord("Frankrijk");
		assertEquals(0, opdrachtAntwoord.GetOpdrachtScore(), 0.1);
	}

	@Test
	public void test_EqualsObject_OK() {
		opdrachtAntwoord2 = new OpdrachtAntwoord(antwoord, pogingen, tijd, qOpdracht, qDeelname);
		assertTrue(opdrachtAntwoord.equals(opdrachtAntwoord2));
		assertTrue(opdrachtAntwoord2.equals(opdrachtAntwoord));
		opdrachtAntwoord2 = new OpdrachtAntwoord(antwoord, 15, tijd, qOpdracht, qDeelname);
		assertFalse(opdrachtAntwoord.equals(opdrachtAntwoord2));
	}

	@Test
	public void test_ToString() {
		assertEquals("Parijs", opdrachtAntwoord.toString());
	}

	@Test
	public void test_CompareTo() {
		opdrachtAntwoord2 = new OpdrachtAntwoord(antwoord, pogingen, tijd, qOpdracht, qDeelname);
		assertTrue(opdrachtAntwoord.compareTo(opdrachtAntwoord2)==0);
		opdrachtAntwoord2.setLaatsteAntwoord("Frankrijk");
		assertTrue(opdrachtAntwoord.compareTo(opdrachtAntwoord2)>0);
		opdrachtAntwoord2.setLaatsteAntwoord(antwoord);
		opdrachtAntwoord.setLaatsteAntwoord("Frankrijk");
		assertTrue(opdrachtAntwoord.compareTo(opdrachtAntwoord2)<0);
	}

}
