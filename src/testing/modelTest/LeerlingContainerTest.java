package testing.modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import model.Leerling;
import model.LeerlingContainer;

import org.junit.Before;
import org.junit.Test;

public class LeerlingContainerTest {
	LeerlingContainer lc;
	ArrayList<Leerling> listLeerlingen;
	Leerling l1;
	Leerling l2;
	Leerling l3;

	@Before
	public void setUp() throws Exception {
		lc = new LeerlingContainer();
		listLeerlingen = new ArrayList<Leerling>();
		l1 = new Leerling("Andre", 1);
		l2 = new Leerling("Bart", 2);
		l3 = new Leerling("Dirk", 3);
		listLeerlingen.add(l1);
		listLeerlingen.add(l2);
		listLeerlingen.add(l3);
	}

	@Test
	public void test_Constructor_OK() {
		lc = new LeerlingContainer();
	}

	@Test
	public void test_GetLeerlingen_OK() {
		lc.setLeerlingen(listLeerlingen);
		assertEquals(listLeerlingen, lc.getLeerlingen());
	}

	@Test
	public void test_SetLeerlingen_Geldige_waarde_Wordt_aanvaard() {
		lc.setLeerlingen(listLeerlingen);
		assertEquals(listLeerlingen, lc.getLeerlingen());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_SetLeerlingen_Null_waarde_Niet_aanvaard() {
		listLeerlingen = null;
		lc.setLeerlingen(listLeerlingen);
	}

	@Test
	public void test_AddLeerling() {
		lc.addLeerling(l1);
		lc.addLeerling(l2);
		lc.addLeerling(l3);
		assertEquals(listLeerlingen, lc.getLeerlingen());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_AddLeerling_Null_waarde_Niet_aanvaard() {
		l1 = null;
		lc.addLeerling(l1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_AddLeerling_Bestaande_waarde_Niet_aanvaard() {
		lc.setLeerlingen(listLeerlingen);
		lc.addLeerling(l1);
	}

	@Test
	public void test_EqualsObject_True() {
		lc.setLeerlingen(listLeerlingen);
		LeerlingContainer lc2 = new LeerlingContainer();
		lc2.setLeerlingen(listLeerlingen);
		assertTrue(lc.equals(lc2));
	}

	@Test
	public void test_EqualsObject_False() {
		lc.setLeerlingen(listLeerlingen);
		LeerlingContainer lc2 = new LeerlingContainer();
		assertFalse(lc.equals(lc2));
	}

}
