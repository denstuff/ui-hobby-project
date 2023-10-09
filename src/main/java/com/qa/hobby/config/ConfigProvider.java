package com.qa.hobby.config;

import com.qa.hobby.driver.BrowserType;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.time.Duration;
import java.util.regex.Pattern;

public abstract class ConfigProvider {
    public static final Config configure = loadConfig();

    private static Config loadConfig() {
        return ConfigFactory.load("application.conf");
    }

    /**
     * --------------------------------  APPLICATION PROPERTY PARAMS ----------------------------------------------
     */

    // Browser options data
    public static final BrowserType BROWSER = BrowserType.getBrowserTypeByName(configure.getString("browser"));
    public static final Boolean HEADLESS_MODE = configure.getBoolean("headlessMode");
    public static final Duration IMPICITY_WAIT = Duration.ofSeconds(configure.getLong("implicitWait"));
    public static final Duration PAGE_LOAD_TIMEOUT = Duration.ofSeconds(configure.getLong("pageLoadTimeout"));
    public static final Duration WAIT_TIMEOUT = Duration.ofSeconds(configure.getLong("elementWaitTimeout"));
    public static final Duration POLLING_EVERY = Duration.ofMillis(configure.getLong("polling"));
    public static final Boolean CLEAN_COOKIES = configure.getBoolean("cleanCookies");

    // File settings data
    public static final String FILE_NAME_PRIFIX = configure.getString("fileNamePrefix");
    public static final String FILE_EXTENSION = configure.getString("fileExtension");
    public static final String DOWNLOAD_DIRECTORY = System.getProperty("user.home") + "\\Downloads";

    // Test data
    public static final String BASE_URL = configure.getString("baseUrl");
    public static final String USER_LOGIN = configure.getString("usersParams.demo.login");
    public static final String USER_PASSWORD = configure.getString("usersParams.demo.password");
    public static final Pattern TICKET_ID_PATTERN = Pattern.compile("\\[(\\w{2,}-\\d.+?)]");

}
