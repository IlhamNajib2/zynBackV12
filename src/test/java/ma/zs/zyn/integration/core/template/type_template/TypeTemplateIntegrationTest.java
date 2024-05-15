package ma.zs.zyn.integration.core.template.type-template;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class TypeTemplateIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("TypeTemplateHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
