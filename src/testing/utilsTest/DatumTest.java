package testing.utilsTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import utils.Datum;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class DatumTest {

	private String vandaagInEuropeesFormaat;
	private Datum geldigeDatum;
	private String geldigeDatumString;
	private String geldigeDatumInEuropeesFormaat;
	private String geldigeDatumInAmerikaansFormaat;
	private Datum datumVandaag;

	@Before
	public void setUp() throws Exception {
		geldigeDatum = new Datum(18, 02, 1980);
		geldigeDatumString = "18 februari 1980";
		geldigeDatumInAmerikaansFormaat = "1980/2/18";
		geldigeDatumInEuropeesFormaat = "18/2/1980";
		datumVandaag = new Datum();

		// Aanpassen alvorens de tests te runnen
		vandaagInEuropeesFormaat = "1/11/2012";

	}

	@Test
	public void test_contructor_ok_geen_parameters() {
		Datum d = new Datum();
		assertEquals(d.getDatumInEuropeesFormaat(), vandaagInEuropeesFormaat);
	}

	@Test
	public void test_contructor_ok_DatumParameter() {
		Datum d = new Datum(geldigeDatum);
		assertEquals(d.toString(), geldigeDatumString);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_contructor_fout_nullparameter() {
		Datum nulDatum = null;
		Datum d = new Datum(nulDatum);
	}

	@Test
	public void test_Constructor_ok_IntIntInt_geldig() {
		Datum d = new Datum(18, 02, 1980);
		assertEquals(d.compareTo(geldigeDatum), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_IntIntInt_ongeldige_dag() {
		Datum d = new Datum(33, 02, 1980);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_IntIntInt_ongeldige_maand() {
		Datum d = new Datum(33, 15, 1980);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_IntIntInt_ongeldige_combinatie() {
		Datum d = new Datum(30, 02, 1980);
	}

	@Test
	public void test_contructor_ok_StringParameter() {
		Datum d = new Datum("18/02/1980");
		assertTrue(d.equals(geldigeDatum));
	}

	@Test(expected = NumberFormatException.class)
	public void test_contructor_fout_LegeStringParameter() {
		Datum d = new Datum("");
	}

	@Test(expected = NumberFormatException.class)
	public void test_contructor_fout_fouteStringParameter() {
		Datum d = new Datum("18/feb/1980");
	}

	@Test
	public void test_SetDatum_ok() {
		datumVandaag.setDatum(18, 02, 1980);
		assertEquals(datumVandaag.compareTo(geldigeDatum), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetDatum_fout_IntIntInt_ongeldige_dag() {
		datumVandaag.setDatum(0, 02, 1980);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetDatum_fout_IntIntInt_ongeldige_maand() {
		datumVandaag.setDatum(1, -1, 1980);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetDatum_fout_IntIntInt_ongeldige_combinatie() {
		datumVandaag.setDatum(30, 02, 1980);
	}

	@Test
	public void test_GetDatumInAmerikaansFormaat_ok() {
		assertEquals(geldigeDatum.getDatumInAmerikaansFormaat(),
				geldigeDatumInAmerikaansFormaat);
	}

	@Test
	public void test_GetDatumInEuropeesFormaat_ok() {
		assertEquals(geldigeDatum.getDatumInEuropeesFormaat(),
				geldigeDatumInEuropeesFormaat);
	}

	@Test
	public void test_KleinerDan_ok_Grotere_waarde_meegegeven() {
		assertFalse(geldigeDatum.kleinerDan(datumVandaag));
	}

	@Test
	public void test_KleinerDan_ok_Kleinere_waarde_meegegeven() {
		assertTrue(datumVandaag.kleinerDan(geldigeDatum));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_KleinerDan_fout_null_parameter() {
		datumVandaag.kleinerDan(null);
	}

	@Test
	public void test_VerschilInJaren_ok() {
		Datum d = new Datum(10, 10, 2012);
		assertTrue(d.verschilInJaren(geldigeDatum) == 32);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_VerschilInJaren_fout_null_parameter() {
		geldigeDatum.verschilInJaren(null);
	}

	@Test
	public void test_VerschilInMaanden_ok() {
		Datum d = new Datum(19, 3, 1981);
		assertTrue(d.verschilInMaanden(geldigeDatum) == 13);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_VerschilInMaande_fout_null_parameter() {
		geldigeDatum.verschilInMaanden(null);
	}

	@Test
	public void test_VerschilInDagen_ok_geen_schrikkeljaar() {
		Datum d1 = new Datum(01, 05, 2009);
		Datum d2 = new Datum(03, 05, 2010);
		assertTrue(d1.verschilInDagen(d2) == 367);
	}

	@Test
	public void test_VerschilInDagen_ok_schrikkeljaar() {
		Datum d1 = new Datum(01, 01, 2012);
		Datum d2 = new Datum(02, 01, 2013);
		assertTrue(d1.verschilInDagen(d2) == 367);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_VerschilInDagen_fout_nullparameter() {
		geldigeDatum.verschilInDagen(null);
	}

	@Test
	public void test_VeranderDatum_ok() {
		Datum d = new Datum(1, 3, 1980);
		geldigeDatum.veranderDatum(12);
		assertEquals(d, geldigeDatum);
	}

	@Test
	public void test_GetveranderdeDatum_ok() {
		Datum d1 = new Datum(01, 05, 2009);
		Datum d2 = new Datum(03, 05, 2010);

		assertEquals(d1.getVeranderdeDatum(367), d2);
	}

	@Test
	public void test_ToString_ok() {
		assertEquals(geldigeDatum.toString(), geldigeDatumString);
	}

	@Test
	public void test_Equals_Object_ok_zelfde_datum() {
		Datum d1 = new Datum(03, 05, 2010);
		Datum d2 = new Datum(03, 05, 2010);
		assertTrue(d1.equals(d2));
	}

	@Test
	public void test_Equals_Object_ok_verschillende_datum() {
		Datum d1 = new Datum(03, 05, 2010);
		Datum d2 = new Datum(03, 05, 2011);
		assertFalse(d1.equals(d2));
	}

	@Test
	public void test_Equals_Object_ok_null() {
		Datum d1 = new Datum(03, 05, 2010);
		assertFalse(d1.equals(null));
	}

	@Test
	public void test_Equals_Object_ok_object() {
		Datum d1 = new Datum(03, 05, 2010);
		assertFalse(d1.equals(new Object()));
	}

	@Test
	public void test_CompareTo_Ok_Gelijk() {
		Datum d1 = new Datum(03, 05, 2010);
		Datum d2 = new Datum(03, 05, 2010);
		assertTrue(d1.compareTo(d2) == 0);
	}

	@Test
	public void test_CompareTo_Ok_kleiner() {
		Datum d1 = new Datum(03, 05, 2010);
		Datum d2 = new Datum(03, 05, 2011);
		assertTrue(d1.compareTo(d2) < 0);
	}

	@Test
	public void test_CompareTo_Ok_Groter() {
		Datum d1 = new Datum(03, 05, 2011);
		Datum d2 = new Datum(03, 05, 2010);
		assertTrue(d1.compareTo(d2) > 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_CompareTo_Fout_null() {
		Datum d1 = new Datum(03, 05, 2011);
		assertTrue(d1.compareTo(null) > 0);
	}

}
