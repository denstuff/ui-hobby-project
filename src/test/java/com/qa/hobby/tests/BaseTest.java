package com.qa.hobby.tests;

import com.qa.hobby.config.ConfigProvider;
import com.qa.hobby.driver.DriverManager;
import com.qa.hobby.extensions.TestListener;
import com.qa.hobby.pages.BasePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.qa.hobby.config.ConfigProvider.DOWNLOAD_DIRECTORY;
import static com.qa.hobby.utils.FileUtils.deleteFiles;

@ExtendWith(TestListener.class)
public abstract class BaseTest {

    @BeforeAll
    public static void setUp() {
        DriverManager.initDriver();
        BasePage.setDriver(DriverManager.getDriver());
    }

    @BeforeEach
    public void cleanAndRestoreBrowserEnvironment() {
        if (ConfigProvider.CLEAN_COOKIES) DriverManager.getDriver().manage().deleteAllCookies();
    }

    @AfterAll
    public static void tearDown() {
        DriverManager.quitDriver();
        deleteFiles(DOWNLOAD_DIRECTORY);
    }

}
