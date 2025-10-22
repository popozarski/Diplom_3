package ru.stellarburgers.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestDataGenerator {

    private static final Random random = new Random();
    private static final String API_BASE = "https://stellarburgers.education-services.ru/api";

    // Генерация случайного email
    public static String generateEmail() {
        long timestamp = System.currentTimeMillis();
        return "test_user_" + timestamp + "@yandex.ru";
    }

    // Генерация случайного имени
    public static String generateName() {
        String[] names = {"Иван", "Петр", "Сергей", "Алексей", "Дмитрий"};
        return names[random.nextInt(names.length)];
    }

    // Генерация валидного пароля (6+ символов)
    public static String generateValidPassword() {
        return "password" + random.nextInt(10000);
    }

    // Генерация невалидного пароля (меньше 6 символов)
    public static String generateInvalidPassword() {
        return "12345";
    }

    // Создание пользователя через API
    public static void createUser(String name, String email, String password) {
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);
        body.put("name", name);

        RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .when()
                .post(API_BASE + "/auth/register")
                .then()
                .statusCode(200);
    }

    // Получение токена для авторизации
    public static String getAccessToken(String email, String password) {
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .when()
                .post(API_BASE + "/auth/login")
                .then()
                .statusCode(200)
                .extract().response();

        return response.path("accessToken");
    }

    // Удаление пользователя через API
    public static void deleteUser(String email, String password) {
        String token = getAccessToken(email, password);

        RestAssured
                .given()
                .header("Authorization", token)
                .when()
                .delete(API_BASE + "/auth/user")
                .then()
                .statusCode(202);
    }
}
