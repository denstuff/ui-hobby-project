package com.qa.hobby.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.qa.hobby.config.ConfigProvider.USER_LOGIN;
import static com.qa.hobby.config.ConfigProvider.USER_PASSWORD;

public class LoginPage extends BasePage {

    public LoginPage() {
        findElementBy(FindType.WEB_ELEMENT, userName);
    }

    @FindBy(id = "username")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(xpath = "//input[@type='submit' and @value='Login']")
    private WebElement login;

    @FindBy(xpath = "//input[@type='checkbox' and @value='remember-me']")
    private WebElement checkBoxRememberPassword;

    @Step("Ввод учетных данных и авторизация пользователя")
    public TicketsPage doLogin() {
        userName.sendKeys(USER_LOGIN);
        password.sendKeys(USER_PASSWORD);
        // do not save password
        if (findElementBy(FindType.WEB_ELEMENT, checkBoxRememberPassword).isSelected())
            checkBoxRememberPassword.click();
        login.click();
        return new TicketsPage();
    }

}
