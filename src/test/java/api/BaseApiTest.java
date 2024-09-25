package api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.example.helpers.utils.configuration.ConfigApp;
import org.example.listeners.CustomTpl;
import org.example.pojo.AddUser;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseApiTest {
    private String cachedToken;

    @BeforeAll
    public static void setup() {
        ConfigApp configApp = ConfigFactory.create(ConfigApp.class);
        RestAssured.baseURI = configApp.baseApiUrl();
        RestAssured.basePath = configApp.baseApiPath();

        RestAssured.filters(CustomTpl.customLogFilter().withCustomTemplate());

    }


    protected String getToken() {
        if (cachedToken == null) {
            cachedToken = postReceiveToken();
        }
        return cachedToken;
    }

    private String postReceiveToken() {
        AddUser addUser = AddUser.builder()
                .username("Oleg1993")
                .password("232323")
                .build();

        return RestAssured.given()
                .contentType("application/json")
                .body(addUser)
                .post("/login")
                .then()
                .statusCode(200)
                .body("access_token", Matchers.notNullValue())
                .extract()
                .path("access_token");
    }


    protected RequestSpecification authenticatedRequest() {
        String token = getToken();
        return RestAssured.given().log().all()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json");
    }


}
