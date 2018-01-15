package ai.testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AIAssessorTest.class, AISimpleTest.class,
        PersonalityTest.class, ScaleTest.class, StrategyTest.class })
public class AITests {

}
