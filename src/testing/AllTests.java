package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import testing.modelTest.OpdrachtCatalogusTest;
import testing.modelTest.OpdrachtTest;
import testing.utilsTest.DatumGregorianTest;
import testing.utilsTest.DatumTest;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({ DatumGregorianTest.class, DatumTest.class, OpdrachtTest.class, OpdrachtCatalogusTest.class })
public class AllTests {

}
