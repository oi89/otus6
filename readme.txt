Проект содержит API тесты с использованием Rest Assured.

Тесты написаны для проверки API https://petstore.swagger.io/
- создание пользователя (POST /user)
-- создание пользователя со всеми заполненными полями
-- создание пользователя с частью полей
- получение пользователя по имени (GET /user/{username})
-- получение созданного пользователя со всеми заполненными полями
-- получение созданного пользователя с частью полей
-- получение несуществующего пользователя

Для проверки ответов используются стандартные Assertions с dto-объектами и Hamcrest-матчеры.
