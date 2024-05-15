package ma.zs.zyn.integration.core.coupon.influencer;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class InfluencerIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("InfluencerHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
