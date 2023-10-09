package com.qa.hobby.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

public class RegisteredTicketPage extends BasePage {

    public RegisteredTicketPage() {
        findElementBy(FindType.WEB_ELEMENT, caption);
    }

    @FindBy(xpath = "//div[@id='content-wrapper']//th[starts-with(text(), 'Queue:')]")
    private WebElement queue;

    @FindBy(xpath = "//div[@id='content-wrapper']//th[text()='Submitter E-Mail']/following-sibling::td")
    private WebElement email;

    @FindBy(xpath = "//div[@id='content-wrapper']//th[text()='Priority']/following-sibling::td")
    private WebElement priority;

    @FindBy(xpath = "//div[@id='content-wrapper']//th[text()='Description']/following::td")
    private WebElement description;

    @FindBy(xpath = "//div[@class='attachments']//a[@href]")
    private WebElement attachment;

    @FindBy(xpath = "//div[@id='content-wrapper']//table//caption")
    private WebElement caption;

    @FindBy(id = "userDropdown")
    private WebElement login;

    public String getQueue() {
        return queue.getText();
    }

    public String getEmail() {
        return email.getText();
    }

    public String getPriority() {
        return priority.getText();
    }

    public String getDescription() {
        return description.getText();
    }

    public String getCaption() {
        return caption.getText();
    }

    public File getAttachment() {
        return downloadAndGetAttachment(attachment);
    }

    @Step("Переход на страницу авторизации пользователя")
    public LoginPage goToLoginPage() {
        login.click();
        return new LoginPage();
    }

}
