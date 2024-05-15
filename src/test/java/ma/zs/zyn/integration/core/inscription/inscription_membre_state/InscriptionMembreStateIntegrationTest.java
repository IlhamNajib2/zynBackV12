package ma.zs.zyn.integration.core.inscription.inscription-membre-state;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class InscriptionMembreStateIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("InscriptionMembreStateHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
