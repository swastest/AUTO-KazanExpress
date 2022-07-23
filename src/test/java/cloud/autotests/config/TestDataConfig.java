package cloud.autotests.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources("classpath:config/testData.properties")
public interface TestDataConfig extends Config {
    String userLogin();
    String userPassword();
    String testHeaderAuthBasic();
}
