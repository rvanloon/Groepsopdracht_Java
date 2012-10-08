package testing.utilsTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utils.DatumGregorian;

public class DatumTest {

	private String vandaagInEuropeesFormaat;
	private DatumGregorian geldigeDatum;
	private String geldigeDatumString;
	private DatumGregorian datum;

	@Before
	public void setUp() throws Exception {
		vandaagInEuropeesFormaat = "7/10/2012";
		geldigeDatum = new DatumGregorian(18, 02, 1980);
		geldigeDatumString = "18 februari 1980";
		datum = new DatumGregorian();
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
		assertEquals(d.compareTo(geldigeDatum), 0);
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
		datum.setDatum(18, 02, 1980);
		assertEquals(datum.compareTo(geldigeDatum), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetDatum_fout_IntIntInt_ongeldige_dag() {
		datum.setDatum(0, 02, 1980);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetDatum_fout_IntIntInt_ongeldige_maand() {
		datum.setDatum(1, -1, 1980);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetDatum_fout_IntIntInt_ongeldige_combinatie() {
		datum.setDatum(30, 02, 1980);
	}

	@Test
	public void testGetDatumInAmerikaansFormaat() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDatumInEuropeesFormaat() {
		fail("Not yet implemented");
	}

	@Test
	public void testKleinerDan() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerschilInJaren() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerschilInMaanden() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerschilInDagen() {
		fail("Not yet implemented");
	}

	@Test
	public void testVeranderDatum() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetveranderdeDatum() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompareTo() {
		fail("Not yet implemented");
	}

}
