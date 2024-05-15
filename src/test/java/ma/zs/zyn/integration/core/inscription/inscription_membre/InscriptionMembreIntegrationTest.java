package ma.zs.zyn.integration.core.inscription.inscription-membre;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class InscriptionMembreIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("InscriptionMembreHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
