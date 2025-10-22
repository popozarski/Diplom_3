package ru.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stellarburgers.pages.RegistrationPage;
import ru.stellarburgers.utils.TestDataGenerator;

import java.time.Duration;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверяем, что можно зарегистрировать пользователя с валидными данными")
    public void testSuccessfulRegistration() {
        String name = TestDataGenerator.generateName();
        String email = TestDataGenerator.generateEmail();
        String password = TestDataGenerator.generateValidPassword();

        driver.get(BASE_URL + "/register");
        RegistrationPage page = new RegistrationPage(driver);

        page.registerUser(name, email, password);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(BASE_URL + "/login"));

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
