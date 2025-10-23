package ru.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final String BASE_URL = "https://stellarburgers.education-services.ru";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        waitClickable(locator).click();
    }

    protected void typeText(By locator, String text) {
        WebElement el = waitVisible(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected String getText(By locator) {
        return waitVisible(locator).getText();
    }

    protected boolean isElementVisible(By locator) {
        try {
            waitVisible(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected void waitForUrl(String expectedUrl) {
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    protected void waitForUrlContains(String expectedUrlPart) {
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
    }


    protected void waitForStableState(By locator, int pollIntervalMs) {
        wait.until(driver -> {
            try {
                String previousClass = driver.findElement(locator).getAttribute("class");
                Thread.sleep(pollIntervalMs);
                String currentClass = driver.findElement(locator).getAttribute("class");
                return previousClass.equals(currentClass);
            } catch (Exception e) {
                return false;
            }
        });
    }
}