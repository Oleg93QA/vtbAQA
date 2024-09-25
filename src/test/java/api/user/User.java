package api.user;

import api.BaseApiTest;
import io.qameta.allure.Story;
import org.example.pojo.AddUser;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
@Story("Логин")
public class User extends BaseApiTest {

    @Test
    @DisplayName("Регистрация нового пользователя")
    public void postRegistrationNewUser() {

        AddUser addUser = AddUser.builder()
                .password("232323332")
                .username("Oleg1992373")
                .build();
        given().log().all()
                .contentType("application/json")
                .body(addUser)
                .post("/register")
                .then()
                .log().all()
                .statusCode(201)
                .body("message", Matchers.containsString("User registered successfully"));
    }


    @Test
    @DisplayName("Не зарегистрированный пользователь")
    public void postNotRegistrationUser() {

        AddUser addUser = AddUser.builder()
                .username("Oleg199993")
                .password("232323")
                .build();
        given().log().all()
                .contentType("application/json")
                .body(addUser)
                .post("/login")
                .then()
                .log().all()
                .statusCode(401)
                .body("message", Matchers.containsString("Invalid credentials"));
    }

    @Test
    @DisplayName("Не корректный пароль, пользователь зарегистрирован")
    public void postNotCorrectUserPassword() {

        AddUser addUser = AddUser.builder()
                .username("Oleg1993")
                .password("23232")
                .build();
        given().log().all()
                .contentType("application/json")
                .body(addUser)
                .post("/login")
                .then()
                .log().all()
                .statusCode(401)
                .body("message", Matchers.containsString("Invalid credentials"));

    }


}
