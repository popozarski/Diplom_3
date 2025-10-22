package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class MainPage extends BasePage {

    private final By loginButton     = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccount = By.xpath("//a[@href='/account']");

    // Локаторы для кликов по вкладкам
    private final By bunsTab         = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesTab       = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingsTab     = By.xpath("//span[text()='Начинки']/parent::div");

    // Локаторы для секций
    private final By bunsSection     = By.xpath("//h2[text()='Булки']");
    private final By saucesSection   = By.xpath("//h2[text()='Соусы']");
    private final By fillingsSection = By.xpath("//h2[text()='Начинки']");

    private static final String ACTIVE_CLASS = "tab_tab_type_current__2BEPc";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать кнопку «Войти в аккаунт» на главной")
    public void clickLoginButton() {
        click(loginButton);
    }

    @Step("Нажать «Личный кабинет»")
    public void clickPersonalAccount() {
        click(personalAccount);
    }

    @Step("Перейти на вкладку «Булки»")
    public void clickBunsTab() {
        click(bunsTab);
        // Ждём, пока секция Булки станет видимой
        waitForSectionVisible(bunsSection);
    }

    @Step("Перейти на вкладку «Соусы»")
    public void clickSaucesTab() {
        click(saucesTab);
        waitForSectionVisible(saucesSection);
    }

    @Step("Перейти на вкладку «Начинки»")
    public void clickFillingsTab() {
        click(fillingsTab);
        waitForSectionVisible(fillingsSection);
    }

    @Step("Проверить, что вкладка «Булки» активна")
    public boolean isBunsTabActive() {
        String classValue = waitVisible(bunsTab).getAttribute("class");
        return classValue != null && classValue.contains(ACTIVE_CLASS);
    }

    @Step("Проверить, что вкладка «Соусы» активна")
    public boolean isSaucesTabActive() {
        String classValue = waitVisible(saucesTab).getAttribute("class");
        return classValue != null && classValue.contains(ACTIVE_CLASS);
    }

    @Step("Проверить, что вкладка «Начинки» активна")
    public boolean isFillingsTabActive() {
        String classValue = waitVisible(fillingsTab).getAttribute("class");
        return classValue != null && classValue.contains(ACTIVE_CLASS);
    }

    // Ждём, пока секция станет видимой и прокрутится наверх
    private void waitForSectionVisible(By sectionLocator) {
        WebElement section = waitVisible(sectionLocator);
        // Ждём, пока JavaScript закончит скролл (небольшая пауза)
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}




