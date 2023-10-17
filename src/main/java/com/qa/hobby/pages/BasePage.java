package com.qa.hobby.pages;

import com.qa.hobby.exeption.AutotestError;
import com.qa.hobby.utils.AllureHelper;
import com.qa.hobby.utils.CommonHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.qa.hobby.config.ConfigProvider.DOWNLOAD_DIRECTORY;

public abstract class BasePage {
    protected static WebDriver driver;
    protected static Collection<Class<? extends Exception>> baseWebDriverExceptions = List.of(NoSuchElementException.class, ElementNotInteractableException.class, StaleElementReferenceException.class);

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    public <T> WebElement findElementBy(FindType findType, T findValue) {
        ExpectedCondition<WebElement> expectedCondition;

        switch (findType) {
            case WEB_ELEMENT -> expectedCondition = ExpectedConditions.visibilityOf((WebElement) findValue);

            case XPATH ->
                    expectedCondition = ExpectedConditions.visibilityOfElementLocated(By.xpath(String.valueOf(findValue)));

            case CSS ->
                    expectedCondition = ExpectedConditions.visibilityOfElementLocated(By.cssSelector(String.valueOf(findValue)));

            default ->
                    throw new AutotestError(String.format("Переданный тип поиска вэб-элемента = '%s' не определен либо не поддерживается!", findType));
        }

        return CommonHelper.getWait(driver, baseWebDriverExceptions).until(expectedCondition);
    }

    public <T> List<WebElement> findElementsBy(FindType findType, T findValue) {
        ExpectedCondition<List<WebElement>> expectedCondition;

        switch (findType) {
            case WEB_ELEMENT -> expectedCondition = ExpectedConditions.visibilityOfAllElements((WebElement) findValue);

            case XPATH ->
                    expectedCondition = ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(String.valueOf(findValue)));

            case CSS ->
                    expectedCondition = ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(String.valueOf(findValue)));

            default ->
                    throw new AutotestError(String.format("Переданный тип поиска вэб-элементов = '%s' не определен либо не поддерживается!", findType));
        }

        return CommonHelper.getWait(driver, baseWebDriverExceptions).until(expectedCondition);
    }

    @Step("Переход в браузере на страницу: {0}")
    public static void goToPage(String url) {
        driver.get(url);
    }

    public File downloadAndGetAttachment(WebElement attachment) {
        AllureHelper.stepStart("На странице '" + this.getClass().getSimpleName() + "' сохраняем вложение с наименованием: '" + attachment.getText() + "'");
        attachment.click();
        File result = new File(DOWNLOAD_DIRECTORY + "\\" + attachment.getText());
        CommonHelper.getWait(result, Collections.emptyList()).until(File::exists);
        AllureHelper.stepFinish();
        return result;
    }

    public enum FindType {
        WEB_ELEMENT, XPATH, CSS
    }

}
