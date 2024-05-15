package ma.zs.zyn.integration.core.template.level-template;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class LevelTemplateIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("LevelTemplateHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
