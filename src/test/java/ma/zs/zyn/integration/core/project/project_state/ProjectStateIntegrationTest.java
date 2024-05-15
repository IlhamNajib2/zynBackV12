package ma.zs.zyn.integration.core.project.project-state;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class ProjectStateIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("ProjectStateHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
