package testing.modelTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LeerlingContainerTest.class, LeerlingTest.class,
		MeerkeuzeTest.class, OpdrachtAntwoordTest.class,
		OpdrachtCatalogusTest.class, OpdrachtTest.class, OpsommingTest.class,
		QuizCatalogusTest.class, QuizDeelnameTest.class,
		QuizOpdrachtTest.class, QuizTest.class, ReproductieTest.class })
public class AllModelTests {

}
