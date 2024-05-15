package ma.zs.zyn.integration.core.project.project;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class ProjectIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("ProjectHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
