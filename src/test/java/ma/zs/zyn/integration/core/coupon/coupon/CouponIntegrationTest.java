package ma.zs.zyn.integration.core.coupon.coupon;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class CouponIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("CouponHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
