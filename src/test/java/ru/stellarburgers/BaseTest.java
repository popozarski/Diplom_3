package ru.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {


    protected WebDriver driver;
    protected static final String BASE_URL = "https://stellarburgers.education-services.ru";

    @Before
    public void setUp() {

        /*// Для Яндекс браузера
   WebDriverManager.chromedriver().setup();

   ChromeOptions options = new ChromeOptions();
   options.setBinary("C:/Users/dimam/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
   driver = new ChromeDriver(options);*/

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(BASE_URL);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

