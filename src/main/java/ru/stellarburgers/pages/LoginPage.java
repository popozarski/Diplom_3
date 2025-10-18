package ru.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Локаторы
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//button[text()='Войти']");



    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Ввести email
    public void enterEmail(String email) {
        typeText(emailInput, email);
    }

    // Ввести пароль
    public void enterPassword(String password) {
        typeText(passwordInput, password);
    }

    // Нажать "Войти"
    public void clickLoginButton() {



        click(loginButton);
    }

    // Выполнить вход (все шаги вместе)
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }
}