package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    private final By loginButton     = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccount = By.xpath("//a[@href='/account']");

    private final By bunsTab         = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesTab       = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingsTab     = By.xpath("//span[text()='Начинки']/parent::div");

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
        waitForTabToBecomeActive(bunsTab);
    }

    @Step("Перейти на вкладку «Соусы»")
    public void clickSaucesTab() {
        click(saucesTab);
        waitForTabToBecomeActive(saucesTab);
    }

    @Step("Перейти на вкладку «Начинки»")
    public void clickFillingsTab() {
        click(fillingsTab);
        waitForTabToBecomeActive(fillingsTab);
    }

    @Step("Проверить, что вкладка «Булки» активна")
    public boolean isBunsTabActive() {
        return isTabActive(bunsTab);
    }

    @Step("Проверить, что вкладка «Соусы» активна")
    public boolean isSaucesTabActive() {
        return isTabActive(saucesTab);
    }

    @Step("Проверить, что вкладка «Начинки» активна")
    public boolean isFillingsTabActive() {
        return isTabActive(fillingsTab);
    }

    // Надежное ожидание активации вкладки
    private void waitForTabToBecomeActive(By tabLocator) {
        // Ждем появления активного класса
        wait.until(ExpectedConditions.attributeContains(tabLocator, "class", ACTIVE_CLASS));

        // Дополнительная проверка: ждем стабильного состояния
        waitForStableState(tabLocator, 200);

        // Проверяем, что другие вкладки не активны
        wait.until(driver -> {
            if (tabLocator.equals(bunsTab)) {
                return !isTabActive(saucesTab) && !isTabActive(fillingsTab);
            } else if (tabLocator.equals(saucesTab)) {
                return !isTabActive(bunsTab) && !isTabActive(fillingsTab);
            } else if (tabLocator.equals(fillingsTab)) {
                return !isTabActive(bunsTab) && !isTabActive(saucesTab);
            }
            return true;
        });
    }

    private boolean isTabActive(By tabLocator) {
        try {
            WebElement tab = waitVisible(tabLocator);
            String classValue = tab.getAttribute("class");
            return classValue != null && classValue.contains(ACTIVE_CLASS);
        } catch (Exception e) {
            return false;
        }
    }
}