package ru.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.stellarburgers.pages.RegistrationPage;
import ru.stellarburgers.utils.TestDataGenerator;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверяем, что можно зарегистрировать пользователя с валидными данными")
    public void testSuccessfulRegistration() {
        // Генерируем тестовые данные
        String name = TestDataGenerator.generateName();
        String email = TestDataGenerator.generateEmail();
        String password = TestDataGenerator.generateValidPassword();



        // Переходим на страницу регистрации
        driver.get("https://stellarburgers.education-services.ru/register");

        // Создаем объект страницы регистрации
        RegistrationPage registrationPage = new RegistrationPage(driver);

        // Заполняем форму и регистрируемся
        registrationPage.registerUser(name, email, password);

        // Ждем немного для перехода на страницу логина
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Проверяем, что перешли на страницу входа
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("После регистрации должен быть переход на /login", 
            currentUrl.contains("/login"));
    }

    @Test
    @DisplayName("Регистрация с коротким паролем")
    @Description("Проверяем, что при пароле меньше 6 символов показывается ошибка")
    public void testRegistrationWithInvalidPassword() {
        // Генерируем тестовые данные
        String name = TestDataGenerator.generateName();
        String email = TestDataGenerator.generateEmail();
        String invalidPassword = TestDataGenerator.generateInvalidPassword();

        // Переходим на страницу регистрации
        driver.get("https://stellarburgers.education-services.ru/register");

        // Создаем объект страницы
        RegistrationPage registrationPage = new RegistrationPage(driver);

        // Заполняем форму с невалидным паролем
        registrationPage.registerUser(name, email, invalidPassword);

        // Проверяем, что отображается ошибка
        Assert.assertTrue("Должна показаться ошибка 'Некорректный пароль'", 
            registrationPage.isPasswordErrorVisible());

        // Проверяем текст ошибки
        String errorText = registrationPage.getErrorMessageText();
        Assert.assertEquals("Текст ошибки должен быть 'Некорректный пароль'", 
            "Некорректный пароль", errorText);
    }
}