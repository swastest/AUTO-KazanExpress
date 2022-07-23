package cloud.autotests.tests.api;

import cloud.autotests.config.TestDataConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static cloud.autotests.tests.api.Specs.request;
import static cloud.autotests.tests.api.Specs.response200;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ApiTest {

    TestDataConfig config = ConfigFactory.create(TestDataConfig.class);
    String usernamePhone = config.userLogin().substring(1),
            userPassword = config.userPassword(),
            headerBasicAuth = config.testHeaderAuthBasic(),
            userName = "Татьяна";

    @Test
    @DisplayName("Позитивная проверка получения токенов(Access||Refresh)")
    void authTokensTest() {
        RequestAuthSuccessModelPojo resp = given()
                .spec(request)
                .formParam("grant_type", "password")
                .formParam("username", usernamePhone)
                .formParam("password", userPassword)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("authorization", headerBasicAuth)
                .when()
                .post("/oauth/token")
                .then()
                .spec(response200)
                .extract().as(RequestAuthSuccessModelPojo.class);
        Assertions.assertNotNull(resp.getAccess_token());
        Assertions.assertNotNull(resp.getRefresh_token());
        Assertions.assertEquals(resp.getToken_type(), "bearer");
    }

    @DisplayName("Запрос информации о контактных данных")
    @Test
    void contactInfoTest() {
        String accessToken = PreRequestForApiTest.getTokenUser();
        given()
                .spec(request)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/user/contacts")
                .then()
                .spec(response200)
                .body("payload.phone", equalTo(usernamePhone))
                .body("payload.firstname", is(userName));
    }

    @Test
    @DisplayName("Негативная проверка получения токенов(Access||Refresh)")
    void authTokensTest1() {
        given()
                .spec(request)
                .formParam("grant_type", "password")
                .formParam("username", usernamePhone)
                .formParam("password", "123445")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("authorization", headerBasicAuth)
                .when()
                .post("/oauth/token")
                .then().log().all()
                .statusCode(400)
                .body("errors.message", is(contains("Bad request")));
    }
}
