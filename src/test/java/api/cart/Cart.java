package api.cart;

import api.BaseApiTest;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.pojo.AddNewProductCart;
import org.example.pojo.RequestCart;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Story("Запросы с карточкой пользователя")
public class Cart extends BaseApiTest {
   private int idProductNotFound = 6666;
    private int productId = 12;

    @Test
    @DisplayName("Проверка получения карты клиента")
    public void getUserCart() {

        authenticatedRequest()
                .get("/cart")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(RequestCart.class);

    }


    @Test
    @DisplayName("Проверка добавления существующего продукта в карточку клиента")
    public void postUserCart() {

        AddNewProductCart addNewProductCart = AddNewProductCart.builder()
                .productId(12)
                .quantity(11)
                .build();
        authenticatedRequest()
                .body(addNewProductCart)
                .post("/cart")
                .then()
                .log().all()
                .statusCode(201);

    }


    @Test
    @DisplayName("Проверка добавления в карточку клиента несуществующего продукта")
    public void posNotFoundUserCart() {

        AddNewProductCart addNewProductCart = AddNewProductCart.builder()
                .productId(idProductNotFound)
                .build();
        Response response = authenticatedRequest()
                .body(addNewProductCart)
                .post("/cart")
                .then()
                .log().all()
                .statusCode(404)
                .extract()
                .response();
        String responseBody = response.asString();
        System.out.println("Response Body: " + responseBody);

        String message = response.jsonPath().getString("message");
        assertThat(message, equalTo("Product not found"));
    }


    @Test
    @DisplayName("Проверка удаления продукта, который был добавлен ране,клиент не авторизован")
    public void postCartUserNotAuth() {

        //String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTcyNzE4NzQ1NSwianRpIjoiZDk1NjczZjQtODE5MS00NDg3LTk3YjItZTg2YzUzYWJmYzQ3IiwidHlwZSI6ImFjY2VzcyIsInN1YiI6Ik9sZWcxOTkzIiwibmJmIjoxNzI3MTg3NDU1LCJjc3JmIjoiNTY5ZWY0OGQtM2VlMi00OGJiLWE1YTktYzZlNjc2YTk2OTYwIiwiZXhwIjoxNzI3MTg4MzU1fQ.33T33Ncqk--531enLIj_xHS-TxLtbaA_hLWLIy2fowd";
        AddNewProductCart addNewProductCart = AddNewProductCart.builder()
                .productId(23)
                .quantity(33)
                .build();
        given().log().all()
                // .header("Authorization", "Bearer ")
                .contentType("application/json")
                .body(addNewProductCart)
                .post("/cart")
                .then()
                .log().all()
                .statusCode(401)
                .body("msg", Matchers.containsString("Missing Authorization Header"));

        //TODO я не знаю, что еще придумать, но 401 ловлю только тогда когда убираю хэдер и токен в остальных случаях 422, хотя токен не рабочий
    }


    @Test
    @DisplayName("Проверка удаления продукта, который был добавлен ране")
    public void deleteProduct() {

        authenticatedRequest()
                .pathParam("productId", productId)
                .delete("/cart/{productId}")
                .then()
                .log().all()
                .statusCode(200)
                .body("message", Matchers.containsString("Product removed from cart"));

    }


    @Test
    @DisplayName("Проверка удаления продукта, который не был добавлен в карточку клиента")
    public void deleteProductNotFound() {

        authenticatedRequest()
                .pathParam("productId", idProductNotFound)
                .delete("/cart/{productId}")
                .then()
                .log().all()
                .statusCode(404)
                .body("message", Matchers.containsString("Product not found in cart"));

    }


}
