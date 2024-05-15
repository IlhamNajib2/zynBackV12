package ma.zs.zyn.integration.core.project.paiment-collaborator-state;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class PaimentCollaboratorStateIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("PaimentCollaboratorStateHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
