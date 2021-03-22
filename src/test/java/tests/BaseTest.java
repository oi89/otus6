package tests;

import config.Endpoints;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    public static ResponseSpecification responseSpecification;

    @BeforeAll
    public static void beforeAll() {
        RestAssured.baseURI = Endpoints.BASE_URI;
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}
