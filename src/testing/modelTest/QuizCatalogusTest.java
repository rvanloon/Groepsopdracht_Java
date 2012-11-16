package testing.modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import model.Leraar;
import model.Quiz;
import model.QuizCatalogus;
import model.QuizStatus;

import org.junit.Before;
import org.junit.Test;

public class QuizCatalogusTest {
	QuizCatalogus catalogus;
	QuizCatalogus catalogus2;
	Quiz quiz1;
	Quiz quiz2;
	ArrayList<Quiz> list;

	@Before
	public void setUp() throws Exception {
		catalogus = new QuizCatalogus();
		catalogus2 = new QuizCatalogus();
		quiz1 = new Quiz("Aardrijkskunde", Leraar.Alain, true, 1, 2, 3);
		quiz2 = new Quiz("Breiwerk", Leraar.Robrecht, true, 2, 3);
		list = new ArrayList<Quiz>();
		list.add(quiz1);
		list.add(quiz2);
		catalogus.voegQuizToe(quiz1);
		catalogus.voegQuizToe(quiz2);
	}

	@Test
	public void test_GetQuizzen() {
		assertEquals(list, catalogus.getQuizzen());
	}

	@Test
	public void test_SetQuizzen_Geldige_waarde_Wordt_aanvaard() {
		catalogus2.setQuizzen(list);
		assertEquals(list, catalogus2.getQuizzen());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetQuizzen_Exception_Als_waarde_null() {
		list = null;
		catalogus2.setQuizzen(list);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetQuizzen_Exception_Als_waarde_leeg() {
		list = new ArrayList<Quiz>();
		catalogus2.setQuizzen(list);
	}

	@Test
	public void test_Constructor_ok() {
		@SuppressWarnings("unused")
		QuizCatalogus qc = new QuizCatalogus();
	}

	@Test
	public void test_VoegQuizToe_Geldige_waarde_Wordt_aanvaard() {
		QuizCatalogus qc = new QuizCatalogus();
		list.remove(quiz2);
		qc.voegQuizToe(quiz1);
		assertEquals(list, qc.getQuizzen());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_VoegQuizToe_Exception_Als_waarde_null() {
		quiz1 = null;
		catalogus.voegQuizToe(quiz1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_VoegQuizToe_Exception_Als_quiz_reeds_bestaat() {
		catalogus.voegQuizToe(quiz1);
	}

	@Test
	public void test_VerwijderQuiz_Geldige_waarde_Wordt_aanvaard() {
		list.remove(quiz1);
		catalogus.verwijderQuiz(quiz1);
		assertEquals(list, catalogus.getQuizzen());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_VerwijderQuiz_Exception_Als_status_quiz_fout() {
		quiz1.setStatus(QuizStatus.opengesteld);
		catalogus.verwijderQuiz(quiz1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_VerwijderQuiz_Exception_Als_waarde_null() {
		quiz1 = null;
		catalogus.verwijderQuiz(quiz1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_VerwijderQuiz_Exception_Als_quiz_niet_aanwezig() {
		Quiz quiz3 = new Quiz("Geologie", Leraar.Sven, true, 1);
		catalogus.verwijderQuiz(quiz3);
	}

	@Test
	public void test_EqualsObject() {
		QuizCatalogus clone = catalogus.clone();
		QuizCatalogus qc2 = new QuizCatalogus();
		assertTrue(catalogus.equals(clone));
		assertFalse(catalogus.equals(qc2));
	}

	@Test
	public void test_EqualsObject_False_als_waarde_null() {
		QuizCatalogus qc2 = null;
		assertFalse(catalogus.equals(qc2));
	}

	@Test
	public void testClone() {
		QuizCatalogus clone = catalogus.clone();
		assertTrue(catalogus.equals(clone));
	}
	
	@Test
	public void test_SchrijfCatalogusNaarFile() throws Exception{
		catalogus.schrijfCatalogusNaarFile();
	}
	
	@Test
	public void test_Lezen_ok(){
		//TODO
	}

}
