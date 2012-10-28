package testing.modelTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Leraar;
import model.Opdracht;
import model.OpdrachtCatalogus;
import model.OpdrachtCategorie;

import org.junit.Before;
import org.junit.Test;

import utils.Datum;

/**
 * 
 * @author rvanloon
 * @version 1
 */
public class OpdrachtCatalogusTest {

	private OpdrachtCatalogus catalogus;
	private Opdracht opdracht1;
	private Opdracht opdracht2;

	@Before
	public void setUp() throws Exception {
		catalogus = new OpdrachtCatalogus();
		opdracht1 = new Opdracht("Welke zee grenst aan België", "Noorzee",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		opdracht2 = new Opdracht("1 + 2 =", "3", OpdrachtCategorie.rekenen,
				Leraar.Sven, new Datum());
		catalogus.addOpdracht(opdracht1);
		catalogus.addOpdracht(opdracht2);
	}

	@Test
	public void test_Constructor_OK_OpdrachtCatalogus() {
		OpdrachtCatalogus cat = new OpdrachtCatalogus();
	}

	@Test
	public void test_GetOpdrachten_OK() {
		assertEquals(opdracht1, catalogus.getOpdrachten().get(0));
		assertEquals(opdracht2, catalogus.getOpdrachten().get(1));
	}

	@Test
	public void test_AddOpdracht_OK() {
		catalogus = new OpdrachtCatalogus();
		catalogus.addOpdracht(opdracht1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_AddOpdracht_Fout_Null() {
		catalogus.addOpdracht(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_AddOpdracht_Fout_BestaandeOpdracht() {
		catalogus.addOpdracht(opdracht1);
	}

	@Test
	public void test_Iterator_OK() {
		assertTrue(catalogus.iterator().hasNext());
	}

	@Test
	public void test_Equal_OK_ZelfdeWaarde() {
		OpdrachtCatalogus catalogus2 = new OpdrachtCatalogus();
		catalogus2.addOpdracht(opdracht1);
		catalogus2.addOpdracht(opdracht2);
		assertTrue(catalogus.equals(catalogus2));
	}

	@Test
	public void test_Equal_OK_AndereWaarde() {
		OpdrachtCatalogus catalogus2 = new OpdrachtCatalogus();
		catalogus2.addOpdracht(opdracht1);
		assertFalse(catalogus.equals(catalogus2));
	}

	@Test
	public void test_Clone() throws CloneNotSupportedException {
		OpdrachtCatalogus catalogus2 = (OpdrachtCatalogus) catalogus.clone();
		assertEquals(catalogus, catalogus2);
	}
}
