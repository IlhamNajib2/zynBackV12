package ma.zs.zyn.integration.core.inscription.inscription-collaborator-type;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class InscriptionCollaboratorTypeIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("InscriptionCollaboratorTypeHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
