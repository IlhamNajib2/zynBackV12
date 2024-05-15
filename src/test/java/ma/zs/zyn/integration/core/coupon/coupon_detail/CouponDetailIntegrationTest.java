package ma.zs.zyn.integration.core.coupon.coupon-detail;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class CouponDetailIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("CouponDetailHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
