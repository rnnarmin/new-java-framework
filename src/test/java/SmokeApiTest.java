import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.Validate.notNull;
import static org.hamcrest.Matchers.notNullValue;

public class SmokeApiTest {
    private static final String BASE_URL = "https://petstore.swagger.io/v2/";

    @Test
    void createUserTest() {
        String bodyJSon = """
                    {
                      "id": 0,
                      "username": "string",
                      "firstName": "string",
                      "lastName": "string",
                      "email": "string",
                      "password": "string",
                      "phone": "string",
                      "userStatus": 0
                    }""";

        ValidatableResponse request =
                given()
                        .header("accept", "application/json")
                        .header("Content-Type" , "application/json")
                        .body(bodyJSon)
                        .when()
                            .post(BASE_URL+"user")
                .then()
                        .statusCode(200)
                        .body("code", Matchers.equalTo(200))
                        .body("type", Matchers.equalTo("unknown"))
                        .body("message", notNullValue(String.class));

    }

}

