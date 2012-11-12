package testing.modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.Leraar;
import model.OpdrachtCategorie;
import model.Opsomming;

import org.junit.Before;
import org.junit.Test;

import utils.Datum;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class OpsommingTest {

	Opsomming opsomming;
	String valideertekst;

	@Before
	public void setUp() throws Exception {
		opsomming = new Opsomming("xxx", "aaa;bbb;ccc", true,
				OpdrachtCategorie.rekenen, Leraar.Robrecht, new Datum());

		valideertekst = "Typ je antwoorden achter elkaar gescheiden door ;";
	}

	@Test
	public void test_IsJuisteAntwoord_OK_JuisteVolgorde_JuisteVolgorde() {
		assertTrue(opsomming.isJuisteAntwoord("aaa;bbb;ccc"));
	}

	@Test
	public void test_IsJuisteAntwoord_OK_JuisteVolgorde_FouteVolgorde() {
		assertFalse(opsomming.isJuisteAntwoord("aaa;ccc;bbb"));
	}

	@Test
	public void test_IsJuisteAntwoord_OK_VrijeVolgorde() {
		opsomming.setInJuisteVolgorde(false);
		assertTrue(opsomming.isJuisteAntwoord("aaa;ccc;bbb"));
	}

	@Test
	public void test_IsJuisteAntwoord_OK_VrijeVolgorde_Fout() {
		opsomming.setInJuisteVolgorde(false);
		assertFalse(opsomming.isJuisteAntwoord("aaa;ccc;ppp"));
	}

	@Test
	public void test_IsJuisteAntwoord_OK_LengteFoutief() {
		assertFalse(opsomming.isJuisteAntwoord("aaa;ccc;bbb,qqq"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_IsJuisteAntwoord_FOUT_foutief_splitteken() {
		opsomming.isJuisteAntwoord("aaa-bbb-ccc");
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_IsJuisteAntwoord_FOUT_null() {
		opsomming.isJuisteAntwoord(null);
	}

	@Test
	public void test_IsJuisteAntwoord_OK_Valideertekst() {
		String message = "";
		try {
			opsomming.isJuisteAntwoord("bbb");
		} catch (Exception e) {
			message = e.getMessage();
		}
		assertEquals(message, valideertekst);
	}

}
