package cloud.autotests.tests.api;

import cloud.autotests.config.TestDataConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public class PreRequestForApiTest {

    static public String  getTokenUser(){
        TestDataConfig config = ConfigFactory.create(TestDataConfig.class);
        String  usernamePhone = config.userLogin().substring(1),
                userPassword = config.userPassword(),
                headerBasicAuth = config.testHeaderAuthBasic();

    RequestAuthSuccessModelPojo resp = given()
            .formParam("grant_type", "password")
            .formParam("username", usernamePhone)
            .formParam("password", userPassword)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .header("authorization", headerBasicAuth)
            .when().log().all()
            .post("https://api.kazanexpress.ru/api/oauth/token")
            .then().log().all()
            .statusCode(200)
            .extract().as(RequestAuthSuccessModelPojo.class);
    return resp.access_token;
}
}
