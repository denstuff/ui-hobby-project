package com.qa.hobby.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

public class AuthorizedTicketPage extends BasePage {

    public AuthorizedTicketPage() {
        findElementBy(FindType.WEB_ELEMENT, caption);
    }

    @FindBy(xpath = "//table[@class='table table-sm table-border']//h3")
    private WebElement caption;

    @FindBy(xpath = "//table[@class='table table-sm table-border']//h3/parent::th")
    private WebElement queue;

    @FindBy(xpath = "//table[@class='table table-sm table-border']//th[text()='Submitter E-Mail']/following::td[1]")
    private WebElement email;

    @FindBy(xpath = "//table[@class='table table-sm table-border']//th[text()='Priority']/following::td[1]")
    private WebElement priority;

    @FindBy(xpath = "//table[@class='table table-sm table-border']//th[text()='Attachments']//following::a[@href][1]")
    private WebElement attachment;

    @FindBy(xpath = "//td[@id='ticket-description']/p")
    private WebElement description;


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

    public String getTicketCaption() {
        return caption.getText();
    }

    public File getAttachment() {
        return downloadAndGetAttachment(attachment);
    }

}
