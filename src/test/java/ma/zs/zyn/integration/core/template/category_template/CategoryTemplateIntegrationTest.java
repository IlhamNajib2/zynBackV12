package ma.zs.zyn.integration.core.template.category-template;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class CategoryTemplateIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("CategoryTemplateHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
