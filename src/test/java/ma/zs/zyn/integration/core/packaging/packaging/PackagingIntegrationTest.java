package ma.zs.zyn.integration.core.packaging.packaging;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class PackagingIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("PackagingHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
