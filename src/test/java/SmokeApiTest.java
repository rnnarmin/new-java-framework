import Controllers.UserController;
import Models.AddUserResponse;
import Models.DeleteUserResponse;
import Models.User;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static Constants.CommonConstants.BASE_URI;
import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.Validate.notNull;
import static org.hamcrest.Matchers.notNullValue;
import static TestDate.TestDate.*;


public class SmokeApiTest {


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    void checkUserResponseBody() {
        String body = """
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

        given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(BASE_URI + "user")
                .then()
                .statusCode(200)
                .body("code", Matchers.equalTo(200))
                .body("type", Matchers.equalTo("unknown"))
                .body("message", notNullValue(String.class));
    }

    @Test
    void createUserControllersTest2() {

        Response response = new UserController().createUser(INVALID_USER);
        AddUserResponse createUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createUserResponse.getCode());
        Assertions.assertEquals("unknown", createUserResponse.getType());
        Assertions.assertFalse(createUserResponse.getMessage().isEmpty());
    }

    @Test
    void createUserControllerTest() {
        Response response = new UserController().createDefaultUser();
        AddUserResponse createUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createUserResponse.getCode());
        Assertions.assertEquals("unknown", createUserResponse.getType());
        Assertions.assertFalse(createUserResponse.getMessage().isEmpty());

    }

    @Test
    void updateUserControllersTest() {
        Response response = new UserController().UpdateUser(DEFAULT_USER);
        AddUserResponse updateUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, updateUserResponse.getCode());
        Assertions.assertEquals("unknown", updateUserResponse.getType());
        Assertions.assertFalse(updateUserResponse.getMessage().isEmpty());
    }

    @Test
    void getUserControllersTest() {
        Response response = new UserController().getUserByUsername(DEFAULT_USER);
        AddUserResponse getUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals(1, getUserResponse.getCode());
        Assertions.assertEquals("error", getUserResponse.getType());
        Assertions.assertEquals("User not found", getUserResponse.getMessage());
    }

    @Test
    void deleteUserByUserNameControllersTest() {
        Response response = new UserController().getUserByUsername(DEFAULT_USER);
        Assertions.assertEquals(404, response.statusCode());

        if (response.getContentType() != null && response.getContentType().contains("application/json")) {
            DeleteUserResponse deleteUserResponse = response.as(DeleteUserResponse.class);
            Assertions.assertEquals("User not found", deleteUserResponse.getMessage());
            Assertions.assertEquals(1, deleteUserResponse.getCode());
        } else {
            System.out.println("Response is not JSON. Raw response: " + response.getBody().asString());
        }
    }
}