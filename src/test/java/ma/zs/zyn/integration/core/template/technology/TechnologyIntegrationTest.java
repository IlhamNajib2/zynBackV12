package ma.zs.zyn.integration.core.template.technology;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class TechnologyIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("TechnologyHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
