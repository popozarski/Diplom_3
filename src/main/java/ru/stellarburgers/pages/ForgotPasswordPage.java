package ru.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    // Локаторы
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By restoreButton = By.xpath("//button[text()='Восстановить']");
    private final By loginLink = By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }



    // Ввести email
    public void enterEmail(String email) {
        typeText(emailInput, email);
    }

    // Нажать "Восстановить"
    public void clickRestoreButton() {
        click(restoreButton);
    }

    // Нажать "Войти"
    public void clickLoginLink() {
        click(loginLink);
    }
}