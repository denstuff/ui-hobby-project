package com.qa.hobby.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TicketsPage extends BasePage {

    @FindBy(id = "search_query")
    private WebElement searchQuery;

    private String ticketTitleLink = "//table[@id='ticketTable']//div[@class='tickettitle']/a[contains(text(),'%s')]";

    public TicketsPage() {
        findElementBy(FindType.WEB_ELEMENT, searchQuery);
    }

    @Step("Поиск тикетов по заданным критериям: {0}")
    public List<WebElement> searchTickets(String searchTicketData, String expectedTicketSummaryValue) {
        searchQuery.sendKeys(searchTicketData, Keys.ENTER);
        return findElementsBy(FindType.XPATH
                , String.format(ticketTitleLink, expectedTicketSummaryValue));
    }

    @Step("Переход в найденный тикет под авторизованным пользователем")
    public AuthorizedTicketPage openTicketPage(WebElement ticket) {
        ticket.click();
        return new AuthorizedTicketPage();
    }
}
