package ma.zs.zyn.integration.core.template.template;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class TemplateIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("TemplateHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
