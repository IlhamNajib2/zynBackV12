package ma.zs.zyn.integration.core.category.category-packaging;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class CategoryPackagingIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("CategoryPackagingHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
