package com.qa.hobby.extensions;

import com.qa.hobby.utils.AllureHelper;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class TestListener implements TestWatcher {

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        AllureHelper.addScreenshotToAllure("Screenshot on failure");
        AllureHelper.addBrowserLogsToAllure("Logs browser on failure");
    }

}
