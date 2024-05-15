package ma.zs.zyn.integration.core.collaborator.member;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class MemberIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("MemberHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
