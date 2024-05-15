package ma.zs.zyn.integration.core.template.domain-template;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DomainTemplateIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DomainTemplateHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
