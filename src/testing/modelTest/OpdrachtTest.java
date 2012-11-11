package testing.modelTest;

import static org.junit.Assert.assertEquals;
import model.Leraar;
import model.Opdracht;
import model.OpdrachtCategorie;

import org.junit.Before;
import org.junit.Test;

import utils.Datum;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class OpdrachtTest {

	Opdracht opdracht;
	String vraag;
	String antwoord;
	OpdrachtCategorie categorie;
	Leraar leraar;
	Datum datum;

	@Before
	public void setUp() throws Exception {
		vraag = "Welke zee grenst aan Belgi�?";
		antwoord = "Noordzee";
		categorie = OpdrachtCategorie.algemeneKennis;
		leraar = Leraar.Alain;
		datum = new Datum(20, 10, 2012);

		opdracht = new Opdracht(vraag, antwoord, categorie, leraar, datum);
	}

	@Test
	public void test_Constructor_OK() {
		Opdracht opdracht = new Opdracht("Welke zee grenst aan Belgi�?",
				"Noordzee", OpdrachtCategorie.algemeneKennis, Leraar.Alain,
				new Datum(20, 10, 2012));
		assertEquals(opdracht, this.opdracht);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_vraag_null() {
		@SuppressWarnings("unused")
		Opdracht opdracht = new Opdracht(null, "Noordzee",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum(20,
						10, 2012));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_vraag_blanco() {
		@SuppressWarnings("unused")
		Opdracht opdracht = new Opdracht("", "Noordzee",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum(20,
						10, 2012));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_antwoord_null() {
		@SuppressWarnings("unused")
		Opdracht opdracht = new Opdracht("Welke zee grenst aan Belgi�?",
				null, OpdrachtCategorie.algemeneKennis, Leraar.Alain,
				new Datum(20, 10, 2012));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_antwoord_blanco() {
		@SuppressWarnings("unused")
		Opdracht opdracht = new Opdracht("Welke zee grenst aan Belgi�?", "",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum(20,
						10, 2012));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_categorie_null() {
		@SuppressWarnings("unused")
		Opdracht opdracht = new Opdracht("Welke zee grenst aan Belgi�?",
				"Noordzee", null, Leraar.Alain, new Datum(20, 10, 2012));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_Leraar_null() {
		@SuppressWarnings("unused")
		Opdracht opdracht = new Opdracht("Welke zee grenst aan Belgi�?",
				"Noordzee", OpdrachtCategorie.algemeneKennis, null, new Datum(
						20, 10, 2012));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_Datum_null() {
		@SuppressWarnings("unused")
		Opdracht opdracht = new Opdracht("Welke zee grenst aan Belgi�?",
				"Noordzee", OpdrachtCategorie.algemeneKennis, Leraar.Alain,
				null);
	}

	@Test
	public void test_GetVraag_OK() {
		assertEquals(opdracht.getVraag(), "Welke zee grenst aan Belgi�?");
	}

	@Test
	public void test_GetJuisteAntwoord_OK() {
		assertEquals(opdracht.getJuisteAntwoord(), "Noordzee");
	}

	@Test
	public void test_GetMaxAantalPogingen_OK() {
		assertEquals(opdracht.getMaxAantalPogingen(), 1);
	}

	@Test
	public void test_SetMaxAantalPogingen_OK() {
		opdracht.setMaxAantalPogingen(3);
		assertEquals(opdracht.getMaxAantalPogingen(), 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetMaxAantalPogingen_Fout_negatief() {
		opdracht.setMaxAantalPogingen(-3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetMaxAantalPogingen_Fout_0() {
		opdracht.setMaxAantalPogingen(0);
	}

	@Test
	public void test_AddAntwoordHint_OK() {
		opdracht.addAntwoordHint("Niet het zuiden.");
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_AddAntwoordHint_Fout_Null() {
		opdracht.addAntwoordHint(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_AddAntwoordHint_Fout_legeString() {
		opdracht.addAntwoordHint("");
	}

	@Test
	public void test_GetAntwoordHints_OK() {
		opdracht.addAntwoordHint("Niet het zuiden.");
		opdracht.addAntwoordHint("Noorden.");
		assertEquals(opdracht.getAntwoordHints().get(0), "Niet het zuiden.");
		assertEquals(opdracht.getAntwoordHints().get(1), "Noorden.");
	}

	@Test
	public void test_GetMaxAntwoordTijd_OK() {
		assertEquals(opdracht.getMaxAntwoordTijd(), 0);
	}

	@Test
	public void test_SetMaxAntwoordTijd_OK() {
		opdracht.setMaxAntwoordTijd(10);
	}

	@Test
	public void test_SetMaxAntwoordTijd_OK_Randwaarde() {
		opdracht.setMaxAntwoordTijd(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetMaxAntwoordTijd_Fout_Negatief() {
		opdracht.setMaxAntwoordTijd(-1);
	}

	/**
	 * Onderstaande tests dienen nog uitgewerkt te worden.
	 */

	/*
	 * @Test public void testGetCategorie() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testGetQuizzen() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testGetAuteur() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testGetDatumRegistratie() {
	 * fail("Not yet implemented"); }
	 * 
	 * @Test public void testAddQuizopdracht() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testIsJuisteAntwoord() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testEqualsObject() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testClone() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testToString() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testCompareTo() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testIsOpdrachtWijzigbaar() {
	 * fail("Not yet implemented"); }
	 */

}
