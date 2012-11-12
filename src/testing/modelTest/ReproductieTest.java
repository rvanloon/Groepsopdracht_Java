package testing.modelTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.Leraar;
import model.OpdrachtCategorie;
import model.Reproductie;

import org.junit.Before;
import org.junit.Test;

import utils.Datum;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class ReproductieTest {

	Reproductie reproductie;

	@Before
	public void setUp() throws Exception {
		reproductie = new Reproductie("xxx", OpdrachtCategorie.FranseTaal,
				Leraar.Alain, new Datum(), 3);
		reproductie.VoegTrefwoordToe("aaa");
		reproductie.VoegTrefwoordToe("bbb");
		reproductie.VoegTrefwoordToe("ccc");
	}

	@Test
	public void test_IsJuisteAntwoord_OK_Voldoende_trefwoorden() {
		String antwoord = "De aaa is niet bbb maar ccc.";
		assertTrue(reproductie.isJuisteAntwoord(antwoord));
	}

	@Test
	public void test_IsJuisteAntwoord_OK_Onvoldoende_trefwoorden() {
		String antwoord = "De aaa is niet bbb maar ddd.";
		assertFalse(reproductie.isJuisteAntwoord(antwoord));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_IsJuisteAntwoord_FOUT_Leeg_antwoord() {
		String antwoord = "";
		reproductie.isJuisteAntwoord(antwoord);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_IsJuisteAntwoord_FOUT_null_antwoord() {
		reproductie.isJuisteAntwoord(null);
	}

}
