package testing.modelTest;

import static org.junit.Assert.*;

import model.Leraar;
import model.Opdracht;
import model.OpdrachtCategorie;

import org.junit.Before;
import org.junit.Test;

import utils.Datum;

public class OpdrachtTest {

	Opdracht opdracht;
	String vraag;
	String antwoord;
	OpdrachtCategorie categorie;
	Leraar leraar;
	Datum datum;

	@Before
	public void setUp() throws Exception {
		vraag = "Welke zee grenst aan België?";
		antwoord = "Noordzee";
		categorie = OpdrachtCategorie.algemeneKennis;
		leraar = Leraar.Alain;
		datum = new Datum(20, 10, 2012);

		opdracht = new Opdracht(vraag, antwoord, categorie, leraar, datum);
	}

	@Test
	public void test_Constructor_OK() {
		Opdracht opdracht = new Opdracht("Welke zee grenst aan België?",
				"Noordzee", OpdrachtCategorie.algemeneKennis, Leraar.Alain,
				new Datum(20, 10, 2012));
		assertEquals(opdracht, this.opdracht);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_vraag_null() {
		Opdracht opdracht = new Opdracht(null, "Noordzee",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum(20,
						10, 2012));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_vraag_blanco() {
		Opdracht opdracht = new Opdracht("", "Noordzee",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum(20,
						10, 2012));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_antwoord_null() {
		Opdracht opdracht = new Opdracht("Welke zee grenst aan België?", null,
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum(20,
						10, 2012));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_antwoord_blanco() {
		Opdracht opdracht = new Opdracht("Welke zee grenst aan België?", "",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum(20,
						10, 2012));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_categorie_null() {
		Opdracht opdracht = new Opdracht("Welke zee grenst aan België?",
				"Noordzee", null, Leraar.Alain, new Datum(20, 10, 2012));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_Leraar_null() {
		Opdracht opdracht = new Opdracht("Welke zee grenst aan België?",
				"Noordzee", OpdrachtCategorie.algemeneKennis, null, new Datum(
						20, 10, 2012));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_Datum_null() {
		Opdracht opdracht = new Opdracht("Welke zee grenst aan België?",
				"Noordzee", OpdrachtCategorie.algemeneKennis, Leraar.Alain,
				null);
	}

	@Test
	public void testGetVraag() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetVraag() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetJuisteAntwoord() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetJuisteAntwoord() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMaxAantalPogingen() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMaxAantalPogingen() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAntwoordHints() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMaxAntwoordTijd() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMaxAntwoordTijd() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCategorie() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCategorie() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetQuizzen() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAuteur() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAuteur() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDatumRegistratie() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDatumRegistratie() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAntwoordHint() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddQuizopdracht() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsJuisteAntwoord() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompareTo() {
		fail("Not yet implemented");
	}

}
