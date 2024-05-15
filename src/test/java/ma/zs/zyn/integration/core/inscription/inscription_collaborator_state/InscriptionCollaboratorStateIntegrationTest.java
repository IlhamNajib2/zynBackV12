package ma.zs.zyn.integration.core.inscription.inscription-collaborator-state;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class InscriptionCollaboratorStateIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("InscriptionCollaboratorStateHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
