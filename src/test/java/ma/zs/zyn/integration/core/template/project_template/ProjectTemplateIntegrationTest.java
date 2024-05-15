package ma.zs.zyn.integration.core.template.project-template;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class ProjectTemplateIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("ProjectTemplateHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
