package ma.zs.zyn.integration.core.project.paiment-influencer-state;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class PaimentInfluencerStateIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("PaimentInfluencerStateHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
