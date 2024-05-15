package ma.zs.zyn.integration.core.collaborator.collaborator;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class CollaboratorIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("CollaboratorHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
