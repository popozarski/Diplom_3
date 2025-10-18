package ru.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    // Локаторы
    private final By nameInput = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//input[@type='password']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath("//a[text()='Войти']");
    private final By errorMessage = By.xpath("//p[text()='Некорректный пароль']");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Ввести имя
    public void enterName(String name) {
        typeText(nameInput, name);
    }

    // Ввести email
    public void enterEmail(String email) {
        typeText(emailInput, email);
    }

    // Ввести пароль
    public void enterPassword(String password) {
        typeText(passwordInput, password);
    }

    // Нажать "Зарегистрироваться"
    public void clickRegisterButton() {
        click(registerButton);
    }

    // Нажать ссылку "Войти"
    public void clickLoginLink() {
        click(loginLink);
    }

    // Проверить видимость ошибки пароля
    public boolean isPasswordErrorVisible() {
        return isElementVisible(errorMessage);
    }

    // Получить текст ошибки
    public String getErrorMessageText() {
        return getText(errorMessage);
    }

    // Зарегистрировать пользователя (все шаги вместе)
    public void registerUser(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }
}