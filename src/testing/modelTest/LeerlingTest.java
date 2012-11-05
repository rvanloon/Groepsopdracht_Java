package testing.modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import model.Leerling;
import model.Leraar;
import model.Quiz;
import model.QuizDeelname;

import org.junit.Before;
import org.junit.Test;

import utils.Datum;

public class LeerlingTest {
	private Leerling leerling;
	private Leerling leerlingGelijk;
	private Leerling leerlingOngelijk;
	private String naam;
	private int jaar;
	private ArrayList<QuizDeelname> listQd;
	
	@Before
	public void setUp() throws Exception {
		naam = "Mathias";
		jaar = 5;
		leerling = new Leerling(naam, jaar);
		leerlingGelijk = new Leerling(naam, jaar);
		leerlingOngelijk = new Leerling("Karel", 5);
		listQd = new ArrayList<QuizDeelname>();
		Quiz quiz = new Quiz("Aardrijkskunde", Leraar.Alain, true, 5);		
		QuizDeelname qd = new QuizDeelname(leerling, quiz, new Datum());
		listQd.add(qd);
	}

	@Test
	public void test_Constructor_Geldige_waarden_Wordt_aanvaard() {
		leerling = new Leerling(naam, jaar);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_Nullwaarde_naam_Niet_aanvaard() {
		naam = null;
		leerling = new Leerling(naam, jaar);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_Leerjaar_te_klein_Niet_aanvaard() {
		jaar = 0;	
		leerling = new Leerling(naam, jaar);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_Leerjaar_negatief_Niet_aanvaard() {
		jaar = -5;	
		leerling = new Leerling(naam, jaar);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_Leerjaar_te_groot_Niet_aanvaard() {
		jaar = 7;	
		leerling = new Leerling(naam, jaar);
	}
	
	@Test
	public void test_GetLeerlingNaam_OK() {
		assertEquals("Mathias", leerling.getLeerlingNaam());
	}
	
	@Test
	public void test_SetLeerlingNaam_Geldige_waarde_Wordt_aanvaard() {
		naam = "Karel";
		leerling.setLeerlingNaam(naam);
		assertEquals("Karel", leerling.getLeerlingNaam());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_SetLeerlingNaam_Nullwaarde_Niet_aanvaard() {
		naam = null;
		leerling.setLeerlingNaam(naam);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_SetLeerlingNaam_Lege_waarde_Niet_aanvaard() {
		naam = "";
		leerling.setLeerlingNaam(naam);
	}

	@Test
	public void test_GetLeerjaar_OK() {
		assertEquals(5, leerling.getLeerjaar());
		jaar = 6;
		leerling.setLeerjaar(jaar);
		assertEquals(6, leerling.getLeerjaar());
	}

	@Test
	public void test_SetLeerjaar_Geldige_waarde_Wordt_aanvaard() {
		jaar = 1;
		leerling.setLeerjaar(jaar);
		assertEquals(1, leerling.getLeerjaar());
		jaar = 2;
		leerling.setLeerjaar(jaar);
		assertEquals(2, leerling.getLeerjaar());
		jaar = 3;
		leerling.setLeerjaar(jaar);
		assertEquals(3, leerling.getLeerjaar());
		jaar = 4;
		leerling.setLeerjaar(jaar);
		assertEquals(4, leerling.getLeerjaar());
		jaar = 5;
		leerling.setLeerjaar(jaar);
		assertEquals(5, leerling.getLeerjaar());
		jaar = 6;
		leerling.setLeerjaar(jaar);
		assertEquals(6, leerling.getLeerjaar());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_SetLeerjaar_Negatieve_waarde_Niet_aanvaard() {
		jaar = -5;
		leerling.setLeerjaar(jaar);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_SetLeerjaar_0_waarde_Niet_aanvaard() {
		jaar = 0;
		leerling.setLeerjaar(jaar);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_SetLeerjaar_Te_grote_waarde_Niet_aanvaard() {
		jaar = 7;
		leerling.setLeerjaar(jaar);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_SetLeerjaar_Veel_te_grote_waarde_Niet_aanvaard() {
		jaar = 50;
		leerling.setLeerjaar(jaar);
	}
	
	@Test
	public void test_GetQuizdeelnames_OK() {
		leerling.setQuizdeelnames(listQd);
		assertEquals(listQd, leerling.getQuizdeelnames());
	}

	@Test
	public void test_SetQuizdeelnames_Geldige_waarde_Wordt_aanvaard() {
		leerling.setQuizdeelnames(listQd);
		assertEquals(listQd, leerling.getQuizdeelnames());
	}

	@Test (expected = IllegalArgumentException.class)
	public void test_SetQuizdeelnames_Null_waarde_Niet_aanvaard() {
		listQd = null;
		leerling.setQuizdeelnames(listQd);
	}
	
	@Test
	public void test_EqualsObject_True() {
		assertTrue(leerling.equals(leerlingGelijk));
		assertTrue(leerlingGelijk.equals(leerling));
	}
	
	@Test
	public void test_EqualsObject_False() {
		assertFalse(leerling.equals(leerlingOngelijk));
		assertFalse(leerlingOngelijk.equals(leerling));
	}

	@Test
	public void test_Clone_OK() throws CloneNotSupportedException {
		Leerling leerlingClone = (Leerling)leerling.clone();
		assertEquals(leerling, leerlingClone);
	}
	
	@Test (expected = NullPointerException.class)
	public void test_Clone_Exception_als_object_null() throws CloneNotSupportedException {
		leerling = null;
		@SuppressWarnings("unused")
		Leerling leerlingClone = (Leerling)leerling.clone();
	}

	@Test
	public void test_ToString_OK() {
		assertEquals("Mathias", leerling.toString());
	}

	@Test
	public void test_CompareTo_True() {
		assertTrue(leerling.compareTo(leerlingOngelijk) > 0);
		assertTrue(leerling.compareTo(leerlingGelijk) == 0);
		assertTrue(leerlingOngelijk.compareTo(leerling) < 0);
	}
	
	@Test
	public void test_CompareTo_False() {
		assertFalse(leerling.compareTo(leerlingOngelijk) < 0);
		assertFalse(leerling.compareTo(leerlingGelijk) > 0);
		assertFalse(leerlingOngelijk.compareTo(leerling) == 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_CompareTo_Exception_Als_waarde_null() {
		leerlingOngelijk = null;
		leerling.compareTo(leerlingOngelijk);
	}

}
