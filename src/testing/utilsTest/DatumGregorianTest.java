package testing.utilsTest;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import utils.DatumGregorian;

public class DatumGregorianTest {

	private String vandaagInEuropeesFormaat;
	private DatumGregorian geldigeDatum;
	private String geldigeDatumString;
	private String geldigeDatumInEuropeesFormaat;
	private String geldigeDatumInAmerikaansFormaat;
	private DatumGregorian datumVandaag;

	@Before
	public void setUp() throws Exception {
		geldigeDatum = new DatumGregorian(18, 02, 1980);
		geldigeDatumString = "18 februari 1980";
		geldigeDatumInAmerikaansFormaat = "1980/2/18";
		geldigeDatumInEuropeesFormaat = "18/2/1980";
		datumVandaag = new DatumGregorian();
		
		// Aanpassen alvorens de tests te runnen
		vandaagInEuropeesFormaat = "14/10/2012";

	}

	@Test
	public void test_contructor_ok_geen_parameters() {
		DatumGregorian d = new DatumGregorian();
		assertEquals(d.getDatumInEuropeesFormaat(), vandaagInEuropeesFormaat);
	}

	@Test
	public void test_contructor_ok_DatumParameter() {
		DatumGregorian d = new DatumGregorian(geldigeDatum);
		assertEquals(d.toString(), geldigeDatumString);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_contructor_fout_nullparameter() {
		DatumGregorian nulDatum = null;
		DatumGregorian d = new DatumGregorian(nulDatum);
	}

	@Test
	public void test_Constructor_ok_IntIntInt_geldig() {
		DatumGregorian d = new DatumGregorian(18, 02, 1980);
		assertEquals(d.compareTo(geldigeDatum), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_IntIntInt_ongeldige_dag() {
		DatumGregorian d = new DatumGregorian(33, 02, 1980);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_IntIntInt_ongeldige_maand() {
		DatumGregorian d = new DatumGregorian(33, 15, 1980);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Constructor_fout_IntIntInt_ongeldige_combinatie() {
		DatumGregorian d = new DatumGregorian(30, 02, 1980);
	}

	@Test
	public void test_contructor_ok_StringParameter() {
		DatumGregorian d = new DatumGregorian("18/02/1980");
		assertTrue(d.equals(geldigeDatum));
	}

	@Test(expected = NumberFormatException.class)
	public void test_contructor_fout_LegeStringParameter() {
		DatumGregorian d = new DatumGregorian("");
	}

	@Test(expected = NumberFormatException.class)
	public void test_contructor_fout_fouteStringParameter() {
		DatumGregorian d = new DatumGregorian("18/feb/1980");
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
		DatumGregorian d = new DatumGregorian(10, 10, 2012);
		assertTrue(d.verschilInJaren(geldigeDatum) == 32);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_VerschilInJaren_fout_null_parameter() {
		geldigeDatum.verschilInJaren(null);
	}

	@Test
	public void test_VerschilInMaanden_ok() {
		DatumGregorian d = new DatumGregorian(19, 3, 1981);
		assertTrue(d.verschilInMaanden(geldigeDatum) == 13);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_VerschilInMaande_fout_null_parameter() {
		geldigeDatum.verschilInMaanden(null);
	}

	@Test
	public void test_VerschilInDagen_ok_geen_schrikkeljaar() {
		DatumGregorian d1 = new DatumGregorian(01, 05, 2009);
		DatumGregorian d2 = new DatumGregorian(03, 05, 2010);
		assertTrue(d1.verschilInDagen(d2) == 367);
	}

	@Test
	public void test_VerschilInDagen_ok_schrikkeljaar() {
		DatumGregorian d1 = new DatumGregorian(01, 01, 2012);
		DatumGregorian d2 = new DatumGregorian(02, 01, 2013);
		assertTrue(d1.verschilInDagen(d2) == 367);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_VerschilInDagen_fout_nullparameter() {
		geldigeDatum.verschilInDagen(null);
	}

	@Test
	public void test_VeranderDatum_ok() {
		DatumGregorian d = new DatumGregorian(1, 3, 1980);
		geldigeDatum.veranderDatum(12);
		assertEquals(d, geldigeDatum);
	}

	@Test
	public void test_GetveranderdeDatum_ok() {
		DatumGregorian d1 = new DatumGregorian(01, 05, 2009);
		DatumGregorian d2 = new DatumGregorian(03, 05, 2010);

		assertEquals(d1.getVeranderdeDatum(367), d2);
	}

	@Test
	public void test_ToString_ok() {
		assertEquals(geldigeDatum.toString(), geldigeDatumString);
	}

	@Test
	public void test_Equals_Object_ok_zelfde_datum() {
		DatumGregorian d1 = new DatumGregorian(03, 05, 2010);
		DatumGregorian d2 = new DatumGregorian(03, 05, 2010);
		assertTrue(d1.equals(d2));
	}

	@Test
	public void test_Equals_Object_ok_verschillende_datum() {
		DatumGregorian d1 = new DatumGregorian(03, 05, 2010);
		DatumGregorian d2 = new DatumGregorian(03, 05, 2011);
		assertFalse(d1.equals(d2));
	}

	@Test
	public void test_Equals_Object_ok_null() {
		DatumGregorian d1 = new DatumGregorian(03, 05, 2010);
		assertFalse(d1.equals(null));
	}

	@Test
	public void test_Equals_Object_ok_object() {
		DatumGregorian d1 = new DatumGregorian(03, 05, 2010);
		assertFalse(d1.equals(new Object()));
	}

	@Test
	public void test_CompareTo_Ok_Gelijk() {
		DatumGregorian d1 = new DatumGregorian(03, 05, 2010);
		DatumGregorian d2 = new DatumGregorian(03, 05, 2010);
		assertTrue(d1.compareTo(d2) == 0);
	}

	@Test
	public void test_CompareTo_Ok_kleiner() {
		DatumGregorian d1 = new DatumGregorian(03, 05, 2010);
		DatumGregorian d2 = new DatumGregorian(03, 05, 2011);
		assertTrue(d1.compareTo(d2) < 0);
	}

	@Test
	public void test_CompareTo_Ok_Groter() {
		DatumGregorian d1 = new DatumGregorian(03, 05, 2011);
		DatumGregorian d2 = new DatumGregorian(03, 05, 2010);
		assertTrue(d1.compareTo(d2) > 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_CompareTo_Fout_null() {
		DatumGregorian d1 = new DatumGregorian(03, 05, 2011);
		assertTrue(d1.compareTo(null) > 0);
	}

}
