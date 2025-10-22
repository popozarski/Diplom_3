package ru.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stellarburgers.pages.*;
import ru.stellarburgers.utils.TestDataGenerator;

import java.time.Duration;

public class LoginTest extends BaseTest {

    private String testEmail;
    private String testPassword;
    private String testName;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        testEmail    = TestDataGenerator.generateEmail();
        testPassword = TestDataGenerator.generateValidPassword();
        testName     = TestDataGenerator.generateName();

        // Создаём пользователя через API
        TestDataGenerator.createUser(testName, testEmail, testPassword);
    }

    @Test
    @DisplayName("Вход через главную кнопку")
    @Description("Проверяем вход через кнопку «Войти в аккаунт»")
    public void testLoginViaMainPageButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testEmail, testPassword);

        // Ждём перехода на главную
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(BASE_URL + "/"));

        Assert.assertEquals(BASE_URL + "/", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через Личный кабинет")
    @Description("Проверяем вход через кнопку «Личный кабинет»")
    public void testLoginViaPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testEmail, testPassword);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(BASE_URL + "/"));

        Assert.assertEquals(BASE_URL + "/", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через форму регистрации")
    @Description("Проверяем переход из регистрации на логин")
    public void testLoginViaRegistrationForm() {
        driver.get(BASE_URL + "/register");

        RegistrationPage regPage = new RegistrationPage(driver);
        regPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testEmail, testPassword);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(BASE_URL + "/"));

        Assert.assertEquals(BASE_URL + "/", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через восстановление пароля")
    @Description("Проверяем переход из восстановления на логин")
    public void testLoginViaForgotPasswordForm() {
        driver.get(BASE_URL + "/forgot-password");

        ForgotPasswordPage forgotPage = new ForgotPasswordPage(driver);
        forgotPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testEmail, testPassword);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(BASE_URL + "/"));

        Assert.assertEquals(BASE_URL + "/", driver.getCurrentUrl());
    }

    @After
    public void cleanUp() {
        TestDataGenerator.deleteUser(testEmail, testPassword);
    }
}


