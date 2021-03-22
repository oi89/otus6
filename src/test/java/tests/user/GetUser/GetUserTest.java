package tests.user.GetUser;

import org.junit.jupiter.api.Test;
import tests.user.UserBaseTest;

import static org.hamcrest.Matchers.*;

public class GetUserTest extends UserBaseTest {

    // Тест запрашивает пользователя по имени.
    // Проверяет, что в ответе пришли все указанные при создании поля.
    // В тесте используюся hamcrest-матчеры для проверки ответа
    // Для успешной работы теста необходимо сначала создать этого пользователя в CreateUserTest.createUserWithAllDataTest()
    @Test
    public void getCreatedUserWithAllDataTest() {
        int id = 1;
        String username = "ivan123";
        String firstName = "Ivan";
        String lastName = "Ivanov";
        String email = "ivan123@gmail.com";
        String password = "ivanpass";
        String phone = "+791234567890";
        int userStatus = 0;

        userApi.getUserRequest(username)
                .then()
                .spec(responseSpecification)
                .log().all()
                .body("id", equalTo(id))
                .body("username", equalTo(username))
                .body("firstName", equalTo(firstName))
                .body("lastName", equalTo(lastName))
                .body("email", equalTo(email))
                .body("password", equalTo(password))
                .body("phone", equalTo(phone))
                .body("userStatus", equalTo(userStatus));
    }

    // Тест запрашивает пользователя по имени.
    // Проверяет, что в ответе пришли все указанные при создании поля.
    // Проверяет, что в ответе нет полей: lastName, email, phone.
    // Проверяет, что в ответе присутствует поле userStatus.
    // В тесте используюся hamcrest-матчеры для проверки ответа
    // Для успешной работы теста необходимо сначала создать этого пользователя в CreateUserTest.createUserWithSomeFieldsTest()
    @Test
    public void getCreatedUserWithSomeFieldsTest() {
        int id = 2;
        String username = "petr777";
        String firstName = "PETR";
        String password = "qwerty";

        userApi.getUserRequest(username)
                .then()
                .spec(responseSpecification)
                .log().all()
                .body("id", equalTo(id))
                .body("username", equalTo(username))
                .body("firstName", equalTo(firstName))
                .body("password", equalTo(password))
                .body("$", not(hasKey("lastName")))
                .body("$", not(hasKey("email")))
                .body("$", not(hasKey("phone")))
                .body("$", hasKey("userStatus"));
    }

    // Тест запрашивает несуществующего пользователя по имени.
    // Проверяет, что в ответе вернулся код 404.
    // Проверяет в теле ответа поля type, message.
    // В тесте используюся hamcrest-матчеры для проверки ответа
    @Test
    public void getNotCreatedUserTest() {
        String username = "vova000";

        userApi.getUserRequest(username)
                .then()
                .log().all()
                .statusCode(404)
                .body("type", is("error"))
                .body("message", containsString("User not found"));
    }
}
