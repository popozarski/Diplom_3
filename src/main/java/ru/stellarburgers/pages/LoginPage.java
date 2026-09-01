package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By emailInput    = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//input[@type='password']");
    private final By loginButton   = By.xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести email при логине")
    public void enterEmail(String email) {
        typeText(emailInput, email);
    }

    @Step("Ввести пароль при логине")
    public void enterPassword(String password) {
        typeText(passwordInput, password);
    }

    @Step("Нажать кнопку «Войти»")
    public void clickLoginButton() {
        click(loginButton);
    }

    @Step("Логин под пользователем {email}")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Логин под пользователем {email} и переход на главную страницу")
    public MainPage loginAndReturnToMainPage(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        waitForUrl(BASE_URL + "/");
        return new MainPage(driver);
    }
}
