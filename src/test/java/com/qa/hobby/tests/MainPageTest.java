package com.qa.hobby.tests;

import com.google.common.io.Files;
import com.qa.hobby.config.ConfigProvider;
import com.qa.hobby.pages.*;
import com.qa.hobby.utils.AllureHelper;
import com.qa.hobby.utils.CommonHelper;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static com.qa.hobby.config.ConfigProvider.TICKET_ID_PATTERN;
import static com.qa.hobby.utils.FileUtils.deleteFile;
import static com.qa.hobby.utils.StringUtils.getSubstringByRegex;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Регистрация тикетов, поиск по различным критериям и проверка корректности сохраненных полей")
@Owner("Денис Максимович")
@Epic("Релиз проекта версии 1.0")
@Feature("Регистрация нового тикета")
class MainPageTest extends BaseTest {

    @Story("Задача на доработку ZOVO-001")
    @DisplayName("Создание тикета и проверка корректности зарегистрированных данных")
    @Description("Заполнение данных на формы обращения, регистрация и последующая корректности для каждого из отправленных полей")
    @ParameterizedTest(name = "[{index}] Регистрация с типом {0}")
    @MethodSource("com.qa.hobby.data.TestDataProvider#createTicketsData")
    public void createNewTicketAndVerifyIt(String queueSelectIn
            , String summaryFieldIn
            , String descriptionFieldIn
            , String prioritySelectIn
            , String dayOfMonthIn
            , File attachmentFileIn
            , String emailIn) {

        BasePage.goToPage(ConfigProvider.BASE_URL);

        // Register new ticket
        RegisteredTicketPage registeredTicketPage = new MainPage().fillTicketFormData(queueSelectIn
                        , summaryFieldIn
                        , descriptionFieldIn
                        , prioritySelectIn
                        , dayOfMonthIn
                        , attachmentFileIn.getAbsolutePath()
                        , emailIn)
                .submitTicket();


        // Waiting for saving attachment file to local download directory
        File registeredTicketAttachment = registeredTicketPage.getAttachment();
        CommonHelper.getWait(registeredTicketAttachment, Collections.emptyList()).until(File::exists);

        // Save registered ticket id in specific variable
        String registeredTicketId = getSubstringByRegex(registeredTicketPage.getCaption(), TICKET_ID_PATTERN, 1);
        AllureHelper.addReportDataToAllure("Идентификатор зарегистрированного тикета: {}", registeredTicketId);

        // Verification all field ticket data in after registered page
        Assertions.assertAll(
                () -> assertTrue(registeredTicketPage.getQueue().contains(queueSelectIn)),
                () -> assertTrue(registeredTicketPage.getCaption().contains(summaryFieldIn)),
                () -> assertEquals(descriptionFieldIn, registeredTicketPage.getDescription()),
                () -> assertEquals(prioritySelectIn, registeredTicketPage.getPriority()),
                () -> assertTrue(Files.equal(attachmentFileIn, registeredTicketAttachment)
                        , "Содержимое вложения, переданного при регистрации, не совпадает с данными вложения из сохраненного тикета!"),
                () -> assertTrue(StringUtils.isNotBlank(registeredTicketId)
                        , "В заголовке зарегистрированного тикета не найден присвоенный ему идентификатор: '" + registeredTicketId + "'"),
                () -> assertEquals(emailIn, registeredTicketPage.getEmail())
        );

        // Authorization on Login Page
        TicketsPage ticketsPage = registeredTicketPage.goToLoginPage().doLogin();

        // Search our ticket
        List<WebElement> foundedTickets = ticketsPage.searchTicket(summaryFieldIn, summaryFieldIn);

        // Check that one ticket has returned
        Assertions.assertEquals(1, foundedTickets.size()
                , "Результат поиска тикета по уникальным данным вернул '" + foundedTickets.size() + "' элементов, вместо одного ожидаемого!");

        AuthorizedTicketPage authorizedTicketPage = ticketsPage.openTicketPage(foundedTickets.get(0));

        // Delete attachment that was received at the ticket registration stage
        deleteFile(registeredTicketAttachment);

        // Wait for saving attachment file to local download directory
        File authorizedTicketAttachment = authorizedTicketPage.getAttachment();
        CommonHelper.getWait(authorizedTicketAttachment, Collections.emptyList()).until(File::exists);

        // Verification all field ticket data in authorized page
        Assertions.assertAll(
                () -> assertTrue(authorizedTicketPage.getTicketCaption().contains(summaryFieldIn)),
                () -> assertTrue(authorizedTicketPage.getQueue().contains(queueSelectIn)),
                () -> assertEquals(prioritySelectIn, authorizedTicketPage.getPriority()),
                () -> assertEquals(emailIn, authorizedTicketPage.getEmail()),
                () -> assertEquals(descriptionFieldIn, authorizedTicketPage.getDescription()),
                () -> assertTrue(Files.equal(attachmentFileIn, authorizedTicketAttachment)
                        , "Содержимое вложения, переданного при регистрации, не совпадает с данными вложения из сохраненного тикета под авторизованным пользователем!"),
                () -> assertTrue(authorizedTicketPage.getTicketCaption().contains(registeredTicketId)
                        , "В заголовке зарегистрированного тикета не найден присвоенный ему ранее идентификатор: '" + registeredTicketId + "'")
        );

    }

//    @Test
//    public void Test1() {
//        System.out.println("@@@ Test 1: " + (StringUtils.isBlank(this.str) ? "" : this.str));
//    }

}
