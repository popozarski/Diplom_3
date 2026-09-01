package ru.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.RegistrationPage;
import ru.stellarburgers.utils.TestDataGenerator;
import ru.stellarburgers.utils.UserClient;
import io.restassured.response.Response;

public class RegistrationTest extends BaseTest {

    private String testEmail;
    private String testPassword;
    private String testName;
    private String accessToken;

    @After
    public void cleanUp() {
        if (testEmail != null && testPassword != null) {
            try {
                // Получение токена и удаление пользователя вынесено в cleanUp
                Response loginResponse = UserClient.loginUser(testEmail, testPassword);
                if (loginResponse.statusCode() == 200) {
                    String accessToken = loginResponse.path("accessToken");
                    Response deleteResponse = UserClient.deleteUser(accessToken);
                    deleteResponse.then().statusCode(202);
                }
            } catch (Exception e) {
                System.out.println("Failed to delete user in cleanUp: " + e.getMessage());
            }
        }
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверяем, что можно зарегистрировать пользователя с валидными данными")
    public void testSuccessfulRegistration() {
        testName = TestDataGenerator.generateName();
        testEmail = TestDataGenerator.generateEmail();
        testPassword = TestDataGenerator.generateValidPassword();

        driver.get(BASE_URL + "/register");
        RegistrationPage page = new RegistrationPage(driver);

        LoginPage loginPage = page.registerUserAndReturnToLoginPage(testName, testEmail, testPassword);

        Assert.assertEquals("После регистрации должен быть переход на /login",
                BASE_URL + "/login", driver.getCurrentUrl());

    }

    @Test
    @DisplayName("Регистрация с коротким паролем")
    @Description("Проверяем, что при пароле меньше 6 символов показывается ошибка")
    public void testRegistrationWithInvalidPassword() {
        String name = TestDataGenerator.generateName();
        String email = TestDataGenerator.generateEmail();
        String invalidPassword = TestDataGenerator.generateInvalidPassword();

        driver.get(BASE_URL + "/register");
        RegistrationPage page = new RegistrationPage(driver);

        page.registerUser(name, email, invalidPassword);

        Assert.assertTrue("Должна показаться ошибка 'Некорректный пароль'",
                page.isPasswordErrorVisible());
        Assert.assertEquals("Текст ошибки должен быть 'Некорректный пароль'",
                "Некорректный пароль", page.getErrorMessageText());
    }
}
