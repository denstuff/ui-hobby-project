package com.qa.hobby.utils;

import org.openqa.selenium.support.ui.FluentWait;

import java.util.Collection;

import static com.qa.hobby.config.ConfigProvider.WAIT_TIMEOUT;
import static com.qa.hobby.config.ConfigProvider.POLLING_EVERY;

public class CommonHelper {

    public static <T> FluentWait<T> getWait(T param, Collection<Class<? extends Exception>> classes) {
        return new FluentWait<>(param)
                .withTimeout(WAIT_TIMEOUT)
                .pollingEvery(POLLING_EVERY)
                .ignoreAll(classes);
    }

}
