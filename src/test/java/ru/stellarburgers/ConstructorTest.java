package ru.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.stellarburgers.pages.MainPage;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу «Булки»")
    @Description("Проверяем переход на вкладку «Булки»")
    public void testNavigateToBunsSection() {
        MainPage main = new MainPage(driver);

        Assert.assertTrue("Булки должны быть активны по умолчанию", main.isBunsTabActive());

        main.clickSaucesTab();
        Assert.assertTrue("После клика соусы должны быть активны", main.isSaucesTabActive());

        main.clickBunsTab();
        Assert.assertTrue("После клика булки должны быть активны", main.isBunsTabActive());
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    @Description("Проверяем переход на вкладку «Соусы»")
    public void testNavigateToSaucesSection() {
        MainPage main = new MainPage(driver);

        main.clickSaucesTab();
        Assert.assertTrue("После клика соусы должны быть активны", main.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    @Description("Проверяем переход на вкладку «Начинки»")
    public void testNavigateToFillingsSection() {
        MainPage main = new MainPage(driver);

        main.clickFillingsTab();
        Assert.assertTrue("После клика начинки должны быть активны", main.isFillingsTabActive());
    }
}

