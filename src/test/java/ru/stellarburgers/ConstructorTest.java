package ru.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.stellarburgers.pages.MainPage;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверяем, что работает переход к разделу Булки в конструкторе")
    public void testNavigateToBunsSection() {
        // Открываем главную страницу
        driver.get("https://stellarburgers.education-services.ru/");

        MainPage mainPage = new MainPage(driver);

        // Сначала переходим на другую вкладку
        mainPage.clickSaucesTab();

        // Ждем прокрутки
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Теперь возвращаемся к Булкам
        mainPage.clickBunsTab();

        // Ждем прокрутки
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Проверяем, что секция Булки видна
        Assert.assertTrue("Секция 'Булки' должна быть видимой", 
            mainPage.isBunsSectionVisible());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверяем, что работает переход к разделу Соусы в конструкторе")
    public void testNavigateToSaucesSection() {
        // Открываем главную страницу
        driver.get("https://stellarburgers.education-services.ru/");

        MainPage mainPage = new MainPage(driver);

        // Переходим к Соусам
        mainPage.clickSaucesTab();

        // Ждем прокрутки
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Проверяем, что секция Соусы видна
        Assert.assertTrue("Секция 'Соусы' должна быть видимой", 
            mainPage.isSaucesSectionVisible());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверяем, что работает переход к разделу Начинки в конструкторе")
    public void testNavigateToFillingsSection() {
        // Открываем главную страницу
        driver.get("https://stellarburgers.education-services.ru/");

        MainPage mainPage = new MainPage(driver);

        // Переходим к Начинкам
        mainPage.clickFillingsTab();

        // Ждем прокрутки
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Проверяем, что секция Начинки видна
        Assert.assertTrue("Секция 'Начинки' должна быть видимой", 
            mainPage.isFillingsSectionVisible());
    }
}