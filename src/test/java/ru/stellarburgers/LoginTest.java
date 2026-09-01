package ru.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.stellarburgers.pages.ForgotPasswordPage;
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.pages.RegistrationPage;
import ru.stellarburgers.utils.TestDataGenerator;
import ru.stellarburgers.utils.UserClient;
import io.restassured.response.Response;

public class LoginTest extends BaseTest {

    private String testEmail;
    private String testPassword;
    private String testName;
    private String accessToken;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        testEmail = TestDataGenerator.generateEmail();
        testPassword = TestDataGenerator.generateValidPassword();
        testName = TestDataGenerator.generateName();

        Response createResponse = UserClient.createUser(testName, testEmail, testPassword);
        createResponse.then().statusCode(200);

        accessToken = UserClient.getAccessToken(testEmail, testPassword);
    }

    @Test
    @DisplayName("Вход через главную кнопку")
    @Description("Проверяем вход через кнопку «Войти в аккаунт»")
    public void testLoginViaMainPageButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPageAfterLogin = loginPage.loginAndReturnToMainPage(testEmail, testPassword);

        Assert.assertEquals("После логина должна быть главная страница",
                BASE_URL + "/", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через Личный кабинет")
    @Description("Проверяем вход через кнопку «Личный кабинет»")
    public void testLoginViaPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPageAfterLogin = loginPage.loginAndReturnToMainPage(testEmail, testPassword);

        Assert.assertEquals("После логина должна быть главная страница",
                BASE_URL + "/", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через форму регистрации")
    @Description("Проверяем переход из регистрации на логин")
    public void testLoginViaRegistrationForm() {
        driver.get(BASE_URL + "/register");

        RegistrationPage regPage = new RegistrationPage(driver);
        regPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPageAfterLogin = loginPage.loginAndReturnToMainPage(testEmail, testPassword);

        Assert.assertEquals("После логина должна быть главная страница",
                BASE_URL + "/", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через восстановление пароля")
    @Description("Проверяем переход из восстановления на логин")
    public void testLoginViaForgotPasswordForm() {
        driver.get(BASE_URL + "/forgot-password");

        ForgotPasswordPage forgotPage = new ForgotPasswordPage(driver);
        forgotPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPageAfterLogin = loginPage.loginAndReturnToMainPage(testEmail, testPassword);

        Assert.assertEquals("После логина должна быть главная страница",
                BASE_URL + "/", driver.getCurrentUrl());
    }

    @After
    public void cleanUp() {
        Response deleteResponse = UserClient.deleteUser(accessToken);
        deleteResponse.then().statusCode(202);
    }
}


