package com.qa.hobby.tests;

import com.qa.hobby.driver.DriverManager;
import com.qa.hobby.extensions.TestListener;
import com.qa.hobby.pages.BasePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.qa.hobby.config.ConfigProvider.*;
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
        if (CLEAN_COOKIES)
            DriverManager.getDriver().manage().deleteAllCookies();
    }

    @AfterAll
    public static void tearDown() {
        DriverManager.quitDriver();
        if (CLEAN_CONTEXT_FILES)
            deleteFiles(DOWNLOAD_DIRECTORY, FILE_NAME_PRIFIX, FILE_EXTENSION);
    }

}
