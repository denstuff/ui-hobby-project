package com.qa.hobby.utils;

import com.qa.hobby.driver.DriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogType;

import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.UUID;

import static io.qameta.allure.Allure.getLifecycle;

@Slf4j
public class AllureHelper {

    private static final ThreadLocal<Deque<String>> STEP_UUID_STACK =
            ThreadLocal.withInitial(ArrayDeque::new);

    @Attachment(value = "{message}", type = "image/png")
    public static byte[] addScreenshotToAllure(String message) {
        log.info("Add Allure attachment: '{}'", message);
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{message}", type = "text/plain", fileExtension = ".log")
    public static byte[] addBrowserLogsToAllure(String message) {
        log.info("Add Allure attachment: '{}'", message);
        return DriverManager.getDriver().manage()
                .logs()
                .get(LogType.BROWSER)
                .getAll()
                .toString()
                .getBytes(StandardCharsets.UTF_8);
    }

    @Step("{0}")
    public static void addReportDataToAllure(String message, Object value) {
    }

    public static void addSimpleMessageToAllure(String message) {
        Allure.step(message);
    }

    public static String stepStart(String stepName) {
        StepResult result = new StepResult().setName(stepName).setStatus(Status.PASSED);
        String uuid = UUID.randomUUID().toString();
        getLifecycle().startStep(uuid, result);
        STEP_UUID_STACK.get().addFirst(uuid);
        return STEP_UUID_STACK.get().getFirst();
    }

    public static void stepFinish() {
        getLifecycle().stopStep(STEP_UUID_STACK.get().removeFirst());
    }
}
