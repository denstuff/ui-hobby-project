package com.qa.hobby.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class MainPage extends BasePage {

    @FindBy(id = "id_queue")
    private WebElement queueSelect;

    @FindBy(id = "id_title")
    private WebElement summaryField;

    @FindBy(id = "id_body")
    private WebElement descriptionField;

    @FindBy(id = "id_priority")
    private WebElement prioritySelect;

    @FindBy(id = "id_due_date")
    private WebElement datePickerField;

    private String dynamicCalendarDate = "//table[@class='ui-datepicker-calendar']//td/a[text()='%s']";

    @FindBy(id = "id_attachment")
    private WebElement attachFileButton;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @Step("Заполнение данных для регистрации тикета на главной страницы")
    public MainPage fillTicketFormData(String queueSelectValue
            , String summaryFieldValue
            , String descriptionFieldValue
            , String prioritySelectValue
            , String dayOfMonthValue
            , String attachmentInputValue
            , String emailValue) {

        new Select(queueSelect).selectByVisibleText(queueSelectValue);
        summaryField.sendKeys(summaryFieldValue);
        descriptionField.sendKeys(descriptionFieldValue);
        new Select(prioritySelect).selectByVisibleText(prioritySelectValue);
        datePickerField.click();
        driver.findElement(By.xpath(String.format(dynamicCalendarDate, dayOfMonthValue))).click();
        attachFileButton.sendKeys(attachmentInputValue);
        emailField.sendKeys(emailValue);
        return this;
    }

    @Step("Регистрация нового тикета на основе введенных данных")
    public RegisteredTicketPage submitTicket() {
        submitButton.click();
        return new RegisteredTicketPage();
    }

}
