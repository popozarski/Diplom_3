package ru.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    // Локаторы
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");

    // Исправленный локатор для "Личный Кабинет"
    private final By personalAccountLink = By.xpath("//a[@href='/account']");

    // Вкладки конструктора
    private final By bunsTab = By.xpath("//div[contains(@class, 'tab')]//span[text()='Булки']");
    private final By saucesTab = By.xpath("//div[contains(@class, 'tab')]//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//div[contains(@class, 'tab')]//span[text()='Начинки']");

    // Заголовки секций
    private final By bunsSection = By.xpath("//h2[text()='Булки']");
    private final By saucesSection = By.xpath("//h2[text()='Соусы']");
    private final By fillingsSection = By.xpath("//h2[text()='Начинки']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    // Нажать "Войти в аккаунт"
    public void clickLoginButton() {
        click(loginButton);
    }

    // Нажать "Личный Кабинет"
    public void clickPersonalAccount() {
        click(personalAccountLink);
    }

    // Переходы по вкладкам конструктора
    public void clickBunsTab() {
        click(bunsTab);
    }

    public void clickSaucesTab() {
        click(saucesTab);
    }

    public void clickFillingsTab() {
        click(fillingsTab);
    }

    // Проверки видимости секций
    public boolean isBunsSectionVisible() {
        return isElementVisible(bunsSection);
    }

    public boolean isSaucesSectionVisible() {
        return isElementVisible(saucesSection);
    }

    public boolean isFillingsSectionVisible() {
        return isElementVisible(fillingsSection);
    }
}
