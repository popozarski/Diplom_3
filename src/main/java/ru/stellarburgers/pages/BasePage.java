package ru.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // Ожидание видимости элемента
    protected WebElement waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Клик по элементу
    protected void click(By locator) {

        waitForElement(locator).click();
    }

    // Ввод текста
    protected void typeText(By locator, String text) {
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Получить текст элемента
    protected String getText(By locator) {
        return waitForElement(locator).getText();
    }

    // Проверить, виден ли элемент
    protected boolean isElementVisible(By locator) {
        try {
            waitForElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}