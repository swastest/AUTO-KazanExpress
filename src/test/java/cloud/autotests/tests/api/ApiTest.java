package cloud.autotests.tests.api;

import cloud.autotests.config.TestDataConfig;
import cloud.autotests.helpers.AllureRestAssuredFilter;
import cloud.autotests.tests.api.models.request.Request;
import cloud.autotests.tests.api.models.respo.Resp;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static cloud.autotests.helpers.AllureRestAssuredFilter.withCustomTemplates;
import static cloud.autotests.tests.api.Specs.request;
import static cloud.autotests.tests.api.Specs.response200;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
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

    @Test
    void authTokensTest00() {
        RequestAuthSuccessModelPojo resp = given()
                .filter(withCustomTemplates())
                .formParam("grant_type", "password")
                .formParam("username", usernamePhone)
                .formParam("password", userPassword)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("authorization", headerBasicAuth)
                .when()
                .post("https://api.kazanexpress.ru/api/oauth/token")
                .then()
                .spec(response200)
                .extract().as(RequestAuthSuccessModelPojo.class);
        Assertions.assertNotNull(resp.getAccess_token());
        Assertions.assertNotNull(resp.getRefresh_token());
        Assertions.assertEquals(resp.getToken_type(), "bearer");
    }

    @Test
    void searchTest() {
//        Request q = new Request();
//      q.setOperationName("getMakeSearch");
//      q.getQuery("query getMakeSearch($queryInput: MakeSearchQueryInput!) {\n  makeSearch(query: $queryInput) {\n    id\n    queryId\n    queryText\n    category {\n      ...CategoryShortFragment\n      __typename\n    }\n    categoryTree {\n      category {\n        ...CategoryFragment\n        __typename\n      }\n      total\n      __typename\n    }\n    items {\n      catalogCard {\n        __typename\n        ...SkuGroupCardFragment\n      }\n      __typename\n    }\n    facets {\n      ...FacetFragment\n      __typename\n    }\n    total\n    mayHaveAdultContent\n    __typename\n  }\n}\n\nfragment FacetFragment on Facet {\n  filter {\n    id\n    title\n    type\n    measurementUnit\n    description\n    __typename\n  }\n  buckets {\n    filterValue {\n      id\n      description\n      image\n      name\n      __typename\n    }\n    total\n    __typename\n  }\n  range {\n    min\n    max\n    __typename\n  }\n  __typename\n}\n\nfragment CategoryFragment on Category {\n  id\n  icon\n  parent {\n    id\n    __typename\n  }\n  seo {\n    header\n    metaTag\n    __typename\n  }\n  title\n  adult\n  __typename\n}\n\nfragment CategoryShortFragment on Category {\n  id\n  parent {\n    id\n    title\n    __typename\n  }\n  title\n  __typename\n}\n\nfragment SkuGroupCardFragment on SkuGroupCard {\n  ...DefaultCardFragment\n  photos {\n    key\n    link(trans: PRODUCT_540) {\n      high\n      low\n      __typename\n    }\n    previewLink: link(trans: PRODUCT_240) {\n      high\n      low\n      __typename\n    }\n    __typename\n  }\n  badges {\n    ... on BottomTextBadge {\n      backgroundColor\n      description\n      id\n      link\n      text\n      textColor\n      __typename\n    }\n    __typename\n  }\n  characteristicValues {\n    id\n    value\n    title\n    characteristic {\n      values {\n        id\n        title\n        value\n        __typename\n      }\n      title\n      id\n      __typename\n    }\n    __typename\n  }\n  __typename\n}\n\nfragment DefaultCardFragment on CatalogCard {\n  adult\n  favorite\n  feedbackQuantity\n  id\n  minFullPrice\n  minSellPrice\n  offer {\n    due\n    icon\n    text\n    textColor\n    __typename\n  }\n  badges {\n    backgroundColor\n    text\n    textColor\n    __typename\n  }\n  ordersQuantity\n  productId\n  rating\n  title\n  __typename\n}");

        String body = "{\n" +
                "    \"operationName\": \"getMakeSearch\",\n" +
                "    \"variables\": {\n" +
                "        \"queryInput\": {\n" +
                "            \"text\": \"стиральный порошок tide 3 кг\",\n" +
                "            \"showAdultContent\": \"NONE\",\n" +
                "            \"filters\": [],\n" +
                "            \"sort\": \"BY_RELEVANCE_DESC\",\n" +
                "            \"pagination\": {\n" +
                "                \"offset\": 0,\n" +
                "                \"limit\": 0\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"query\": \"query getMakeSearch($queryInput: MakeSearchQueryInput!) {\\n  makeSearch(query: $queryInput) {\\n    id\\n    queryId\\n    queryText\\n    category {\\n      ...CategoryShortFragment\\n      __typename\\n    }\\n    categoryTree {\\n      category {\\n        ...CategoryFragment\\n        __typename\\n      }\\n      total\\n      __typename\\n    }\\n    items {\\n      catalogCard {\\n        __typename\\n        ...SkuGroupCardFragment\\n      }\\n      __typename\\n    }\\n    facets {\\n      ...FacetFragment\\n      __typename\\n    }\\n    total\\n    mayHaveAdultContent\\n    __typename\\n  }\\n}\\n\\nfragment FacetFragment on Facet {\\n  filter {\\n    id\\n    title\\n    type\\n    measurementUnit\\n    description\\n    __typename\\n  }\\n  buckets {\\n    filterValue {\\n      id\\n      description\\n      image\\n      name\\n      __typename\\n    }\\n    total\\n    __typename\\n  }\\n  range {\\n    min\\n    max\\n    __typename\\n  }\\n  __typename\\n}\\n\\nfragment CategoryFragment on Category {\\n  id\\n  icon\\n  parent {\\n    id\\n    __typename\\n  }\\n  seo {\\n    header\\n    metaTag\\n    __typename\\n  }\\n  title\\n  adult\\n  __typename\\n}\\n\\nfragment CategoryShortFragment on Category {\\n  id\\n  parent {\\n    id\\n    title\\n    __typename\\n  }\\n  title\\n  __typename\\n}\\n\\nfragment SkuGroupCardFragment on SkuGroupCard {\\n  ...DefaultCardFragment\\n  photos {\\n    key\\n    link(trans: PRODUCT_540) {\\n      high\\n      low\\n      __typename\\n    }\\n    previewLink: link(trans: PRODUCT_240) {\\n      high\\n      low\\n      __typename\\n    }\\n    __typename\\n  }\\n  badges {\\n    ... on BottomTextBadge {\\n      backgroundColor\\n      description\\n      id\\n      link\\n      text\\n      textColor\\n      __typename\\n    }\\n    __typename\\n  }\\n  characteristicValues {\\n    id\\n    value\\n    title\\n    characteristic {\\n      values {\\n        id\\n        title\\n        value\\n        __typename\\n      }\\n      title\\n      id\\n      __typename\\n    }\\n    __typename\\n  }\\n  __typename\\n}\\n\\nfragment DefaultCardFragment on CatalogCard {\\n  adult\\n  favorite\\n  feedbackQuantity\\n  id\\n  minFullPrice\\n  minSellPrice\\n  offer {\\n    due\\n    icon\\n    text\\n    textColor\\n    __typename\\n  }\\n  badges {\\n    backgroundColor\\n    text\\n    textColor\\n    __typename\\n  }\\n  ordersQuantity\\n  productId\\n  rating\\n  title\\n  __typename\\n}\"\n" +
                "}";
        Resp r =given()
                .when()
                .contentType(ContentType.JSON)
                .body(body)
                .post("https://dshop.kznexpress.ru/")
                .then().log().all()
                .statusCode(200)
                .extract().as(Resp.class);
        Assertions.assertEquals(r.getData().getMakeSearch().getQueryText(),"стиральный порошок tide 3 кг");
        Assertions.assertNotNull(r.getData().getMakeSearch().getId());
        Assertions.assertNotNull(r.getData().getMakeSearch().getQueryId());
    }
}
