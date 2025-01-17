package cloud.autotests.tests.api;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static cloud.autotests.helpers.AllureRestAssuredFilter.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;

public class Specs {
    public static RequestSpecification request;

    static {
        request = with()
                .filter(withCustomTemplates())
                .contentType(ContentType.JSON)
                .baseUri("https://api.kazanexpress.ru")
                .basePath("/api")
                .log().all();
    }


    public static ResponseSpecification response200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();

    public static ResponseSpecification response403 = new ResponseSpecBuilder()
            .expectStatusCode(403)
            .log(ALL)
            .build();

    public static ResponseSpecification response405 = new ResponseSpecBuilder()
            .expectStatusCode(405)
            .log(ALL)
            .build();
}
