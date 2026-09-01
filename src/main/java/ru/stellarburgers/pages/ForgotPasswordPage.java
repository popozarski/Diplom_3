package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    private final By emailInput     = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By restoreButton  = By.xpath("//button[text()='Восстановить']");
    private final By loginLink      = By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести email для восстановления")
    public void enterEmail(String email) {
        typeText(emailInput, email);
    }

    @Step("Нажать кнопку «Восстановить»")
    public void clickRestoreButton() {
        click(restoreButton);
    }

    @Step("Нажать ссылку «Войти» на странице восстановления")
    public void clickLoginLink() {
        click(loginLink);
    }
}
