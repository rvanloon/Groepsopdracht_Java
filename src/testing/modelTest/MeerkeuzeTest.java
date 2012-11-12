/**
 * 
 */
package testing.modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.Leraar;
import model.Meerkeuze;
import model.OpdrachtCategorie;

import org.junit.Before;
import org.junit.Test;

import utils.Datum;

/**
 * @author rvanloon
 * @version 1
 * 
 */
public class MeerkeuzeTest {

	/**
	 * Door te testen op 'is juiste antwoord' worden ook isvalide, voegkeuze toe
	 * en getvalideertekst getest.
	 */

	Meerkeuze meerkeuze;
	String valideertekst;

	@Before
	public void setUp() throws Exception {
		meerkeuze = new Meerkeuze("xxx", "aaa",
				OpdrachtCategorie.NederlandseTaal, Leraar.Sven, new Datum());
		meerkeuze.voegKeuzeToe("bbb");
		meerkeuze.voegKeuzeToe("ccc");
		meerkeuze.voegKeuzeToe("aaa");

		valideertekst = "Geef het nummer in behorende tot het juiste antwoord.";
	}

	@Test
	public void test_IsJuisteAntwoord_OK_Juiste_antwoord() {
		assertTrue(meerkeuze.isJuisteAntwoord("3"));
	}

	@Test
	public void test_IsJuisteAntwoord_OK_Foute_antwoord() {
		assertFalse(meerkeuze.isJuisteAntwoord("2"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_IsJuisteAntwoord_fout_Tekst_meegegeven() {
		meerkeuze.isJuisteAntwoord("aaa");
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_IsJuisteAntwoord_fout_nummer_te_klein_meegegeven() {
		meerkeuze.isJuisteAntwoord("0");
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_IsJuisteAntwoord_fout_nummer_te_groot_meegegeven() {
		meerkeuze.isJuisteAntwoord("4");
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_IsJuisteAntwoord_fout_null_meegegeven() {
		meerkeuze.isJuisteAntwoord(null);
	}

	@Test
	public void test_IsJuisteAntwoord_OK_Juiste_valideertekst() {
		String message = "";
		try {
			meerkeuze.isJuisteAntwoord("bbb");
		} catch (Exception e) {
			message = e.getMessage();
		}
		assertEquals(message, valideertekst);
	}

}
