package ma.zs.zyn.integration.core.paiment.paiment-influencer;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class PaimentInfluencerIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("PaimentInfluencerHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
