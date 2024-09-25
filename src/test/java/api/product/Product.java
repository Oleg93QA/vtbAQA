package api.product;

import api.BaseApiTest;
import io.qameta.allure.Story;
import io.restassured.common.mapper.TypeRef;
import org.example.pojo.CartItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
@Story("Карточка клиента")
public class Product extends BaseApiTest {
   private int productIdNot = 444;
   private int productId = 4;
    @Test
    @DisplayName("Получить массив продуктов ")
    public void getListProduct() {

        given()
                .get("/products")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(new TypeRef<List<CartItem>>() {
                });

    }


    @Test
    @DisplayName("Получить продукт по айди - [{0}]")
    public void getLookingForProductId() {

        given().log().all().pathParam("productId", productId)
                .get("/products/" + "{productId}")
                .then()
                .log().all()
                .statusCode(200)
                .body("[0].id", equalTo(productId));
    }


    @Test
    @DisplayName("Получить не существующий продукт по айди - [{0}]")
    public void getNotFoundProductId() {

        String expectedMessage = "Product not found";
        given().log().all().pathParam("productId", productIdNot)
                .get("/products/" + "{productId}")
                .then()
                .log().all()
                .statusCode(404)
                .body("message", equalTo(expectedMessage));
    }


}
