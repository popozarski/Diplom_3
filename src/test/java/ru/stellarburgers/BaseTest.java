package ru.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;

    @Before
    public void setUp() {
        // Для Chrome
         driver = new ChromeDriver();

       /*// Для Яндекс браузера
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Users/dimam/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
        driver = new ChromeDriver(options);*/

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://stellarburgers.education-services.ru/");


    }

    @After
    public void tearDown() {
        // Закрываем браузер после теста
        if (driver != null) {
            driver.quit();
        }
    }
}