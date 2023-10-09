package com.qa.hobby.driver;

import com.qa.hobby.config.ConfigProvider;
import com.qa.hobby.exeption.AutotestError;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;

public abstract class DriverManager {
    private static WebDriver driver = null;

    public static void initDriver() {
        if (Objects.isNull(driver)) {
            switch (ConfigProvider.BROWSER) {
                case CHROME -> {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(DriverConfig.chromeOptions());
                }
                case FIREFOX -> {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(DriverConfig.fireFoxOptions());
                }
                case EDGE -> {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver(DriverConfig.edgeOptions());
                }
                default ->
                        throw new AutotestError(String.format("Выбранный тип браузера '%s' не определен либо не поддерживается!"
                                , ConfigProvider.configure.getString("browser")));
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(ConfigProvider.IMPICITY_WAIT);
            driver.manage().timeouts().pageLoadTimeout(ConfigProvider.PAGE_LOAD_TIMEOUT);
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (Objects.nonNull(driver)) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }
}
