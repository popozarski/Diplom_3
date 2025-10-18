package ru.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.stellarburgers.pages.ForgotPasswordPage;
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.pages.RegistrationPage;
import ru.stellarburgers.utils.TestDataGenerator;

public class LoginTest extends BaseTest {

    private String testEmail;
    private String testPassword;

    @Before
    @Override
    public void setUp() {
        super.setUp();

        // Создаем тестового пользователя для всех тестов входа
        testEmail = TestDataGenerator.generateEmail();
        testPassword = TestDataGenerator.generateValidPassword();
        String testName = TestDataGenerator.generateName();

        // Регистрируем пользователя
        driver.get("https://stellarburgers.education-services.ru/register");
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registerUser(testName, testEmail, testPassword);

        // Ждем перехода на страницу логина
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //После регистрации открываем главную страницу, чтобы начать тесты с чистого состояния
        driver.get("https://stellarburgers.education-services.ru/");
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной")
    @Description("Проверяем вход через главную кнопку на главной странице")
    public void testLoginViaMainPageButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        // Вводим данные и входим
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testEmail, testPassword);

        // Ждем загрузки
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Проверяем, что мы на главной странице (URL должен быть /)
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("После входа должна быть главная страница",
                currentUrl.equals("https://stellarburgers.education-services.ru/"));
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверяем вход через кнопку Личный кабинет в шапке")
    public void testLoginViaPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();

        // Вводим данные
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testEmail, testPassword);

        // Ждем загрузки
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Проверяем успешный вход
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("После входа должна быть главная страница",
                currentUrl.equals("https://stellarburgers.education-services.ru/"));
    }

    @Test
    @DisplayName("Вход через форму регистрации")
    @Description("Проверяем вход через ссылку 'Войти' на странице регистрации")
    public void testLoginViaRegistrationForm() {
        // Открываем страницу регистрации
        driver.get("https://stellarburgers.education-services.ru/register");

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLoginLink();

        // Вводим данные
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testEmail, testPassword);

        // Ждем загрузки
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Проверяем успешный вход
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("После входа должна быть главная страница",
                currentUrl.equals("https://stellarburgers.education-services.ru/"));
    }

    @Test
    @DisplayName("Вход через форму восстановления пароля")
    @Description("Проверяем вход через ссылку 'Войти' на странице восстановления пароля")
    public void testLoginViaForgotPasswordForm() {
        // Открываем страницу восстановления пароля
        driver.get("https://stellarburgers.education-services.ru/forgot-password");

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginLink();

        // Вводим данные
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testEmail, testPassword);

        // Ждем загрузки
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Проверяем успешный вход
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("После входа должна быть главная страница",
                currentUrl.equals("https://stellarburgers.education-services.ru/"));
    }
}
