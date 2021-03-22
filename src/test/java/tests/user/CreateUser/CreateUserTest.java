package tests.user.CreateUser;

import dto.CreateUserResponse;
import dto.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.BaseTest;
import tests.user.UserBaseTest;

public class CreateUserTest extends UserBaseTest {
    private User user;

    // Тест создает пользователя со всеми заполненными полями.
    // Проверяет, что в ответе в поле 'message' возвращается указанный в запросе id
    // В тесте используюся стандратные Assertions для проверки ответа
    @Test
    public void createUserWithAllDataTest() {
        int id = 1;
        String username = "ivan123";
        String firstName = "Ivan";
        String lastName = "Ivanov";
        String email = "ivan123@gmail.com";
        String password = "ivanpass";
        String phone = "+791234567890";
        int userStatus = 0;

        user = User.builder()
                .id(id)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .phone(phone)
                .userStatus(userStatus)
                .build();

        CreateUserResponse createUserResponse = userApi.createUserRequest(user)
                .then()
                .spec(responseSpecification)
                .log().all()
                .extract()
                .body()
                .as(CreateUserResponse.class);

        Assertions.assertEquals(Integer.toString(id), createUserResponse.getMessage());
    }

    // Тест создает пользователя с полями: id, username, firstName, password.
    // Проверяет, что в ответе в поле 'message' возвращается указанный в запросе id
    // Проверяет, что в ответе в поле 'type' возвращается "unknown"
    // В тесте используюся стандратные Assertions для проверки ответа
    @Test
    public void createUserWithSomeFieldsTest() {
        int id = 2;
        String username = "petr777";
        String firstName = "PETR";
        String password = "qwerty";

        user = User.builder()
                .id(id)
                .username(username)
                .firstName(firstName)
                .password(password)
                .build();

        CreateUserResponse createUserResponse = userApi.createUserRequest(user)
                .then()
                .spec(BaseTest.responseSpecification)
                .log().all()
                .extract()
                .body()
                .as(CreateUserResponse.class);

        Assertions.assertEquals(Integer.toString(id), createUserResponse.getMessage());
        Assertions.assertEquals("unknown", createUserResponse.getType());
    }
}
