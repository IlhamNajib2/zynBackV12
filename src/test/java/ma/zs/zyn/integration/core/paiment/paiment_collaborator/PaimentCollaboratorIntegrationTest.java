package ma.zs.zyn.integration.core.paiment.paiment-collaborator;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class PaimentCollaboratorIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("PaimentCollaboratorHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
