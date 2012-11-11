package testing.modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.Leraar;
import model.Opdracht;
import model.OpdrachtCatalogus;
import model.OpdrachtCategorie;
import model.Quiz;
import model.QuizOpdracht;

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
		opdracht1 = new Opdracht("Welke zee grenst aan Belgi�", "Noorzee",
				OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		opdracht2 = new Opdracht("1 + 2 =", "3", OpdrachtCategorie.rekenen,
				Leraar.Sven, new Datum());
		catalogus.addOpdracht(opdracht1);
		catalogus.addOpdracht(opdracht2);
	}

	@Test
	public void test_Constructor_OK_OpdrachtCatalogus() {
		@SuppressWarnings("unused")
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
	public void test_verwijderOpdracht_OK() {
		assertEquals(catalogus.getOpdrachten().size(), 2);
		catalogus.verwijderOpdracht(opdracht2);
		assertEquals(catalogus.getOpdrachten().size(), 1);
	}

	@Test (expected=IllegalArgumentException.class)
	public void test_verwijderOpdracht_Fout_NietInCatalogus() {
		Opdracht opd = new Opdracht("nn", "ll",
				OpdrachtCategorie.NederlandseTaal, Leraar.Robrecht, new Datum());
		catalogus.verwijderOpdracht(opd);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void test_verwijderOpdracht_Fout_AanQuizGelinkt() {
		Quiz quiz = new Quiz("rrrr", Leraar.Sven, true, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht1, 5);
		catalogus.verwijderOpdracht(opdracht1);
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
