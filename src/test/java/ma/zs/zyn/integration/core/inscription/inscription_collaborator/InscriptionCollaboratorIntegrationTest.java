package ma.zs.zyn.integration.core.inscription.inscription-collaborator;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class InscriptionCollaboratorIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("InscriptionCollaboratorHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
