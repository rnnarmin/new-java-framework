package Controllers;

import Models.User;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static Constants.CommonConstants.BASE_URI;
import static TestDate.TestDate.DEFAULT_USER;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

    public class UserController {
        RequestSpecification requestSpecification;
        private static final String USER_ENDPOINT= "user";

        public UserController() {
            this.requestSpecification = given()
                    .accept(JSON)
                    .contentType(JSON)
                    .baseUri(BASE_URI);
        }

        public Response createDefaultUser() {
            return given(this.requestSpecification)
                    .body(DEFAULT_USER)
                    .when()
                    .post(USER_ENDPOINT)
                    .andReturn();
        }

        public Response createUser(User user){
            return given(this.requestSpecification)
                    .body(user)
                    .when()
                    .post(USER_ENDPOINT)
                    .andReturn();

        }

        public Response UpdateUser(User user){
            return given(this.requestSpecification)
                    .body(user)
                    .when()
                    .put(USER_ENDPOINT + "/" + user.getUsername())
                    .andReturn();

        }

        public Response getUserByUsername(User user){
            return given(this.requestSpecification)
                    .when()
                    .get(USER_ENDPOINT + "/" + user)
                    .andReturn();
        }

        public Response deleteUserByUsername(String user){
            return given(this.requestSpecification)
                    .when()
                    .delete(USER_ENDPOINT + "/" + user)
                    .andReturn();
        }

    }
