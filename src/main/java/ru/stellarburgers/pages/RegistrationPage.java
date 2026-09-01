package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    private final By nameInput      = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailInput     = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput  = By.xpath("//input[@type='password']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink      = By.xpath("//a[text()='Войти']");
    private final By errorMessage   = By.xpath("//p[text()='Некорректный пароль']");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести имя при регистрации")
    public void enterName(String name) {
        typeText(nameInput, name);
    }

    @Step("Ввести email при регистрации")
    public void enterEmail(String email) {
        typeText(emailInput, email);
    }

    @Step("Ввести пароль при регистрации")
    public void enterPassword(String password) {
        typeText(passwordInput, password);
    }

    @Step("Нажать кнопку «Зарегистрироваться»")
    public void clickRegisterButton() {
        click(registerButton);
    }

    @Step("Нажать ссылку «Войти» на странице регистрации")
    public void clickLoginLink() {
        click(loginLink);
    }

    @Step("Проверить отображение ошибки «Некорректный пароль»")
    public boolean isPasswordErrorVisible() {
        return isElementVisible(errorMessage);
    }

    @Step("Получить текст ошибки при регистрации")
    public String getErrorMessageText() {
        return getText(errorMessage);
    }

    @Step("Зарегистрировать пользователя {name}, {email}")
    public void registerUser(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    @Step("Зарегистрировать пользователя {name}, {email} и переход на страницу логина")
    public LoginPage registerUserAndReturnToLoginPage(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
        waitForUrl(BASE_URL + "/login");
        return new LoginPage(driver);
    }
}

