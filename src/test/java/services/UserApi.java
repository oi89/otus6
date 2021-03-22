package services;

import dto.User;
import config.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserApi {
    private RequestSpecification requestSpecification;

    public UserApi() {
        requestSpecification = given()
                .contentType(ContentType.JSON)
                .log().all();
    }

    public Response createUserRequest(User user) {
        return
                given(requestSpecification)
                        .basePath(Endpoints.CREATE_USER)
                        .body(user)
                        .when()
                        .post();
    }

    public Response getUserRequest(String userId) {
        return
                given(requestSpecification)
                        .pathParams("id", userId)
                        .basePath(Endpoints.GET_USER)
                        .when()
                        .get();
    }
}
