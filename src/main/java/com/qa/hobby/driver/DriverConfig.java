package com.qa.hobby.driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.HashMap;
import java.util.Map;

import static com.qa.hobby.config.ConfigProvider.*;

public abstract class DriverConfig {

    public static ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        // Set chrome options for simple download files
        options.setExperimentalOption("prefs", chromeDriverOptions());
        // Headless mode
        if (HEADLESS_MODE) options.addArguments("--headless=new");

        return options;

    }

    public static FirefoxOptions fireFoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        // Set firefox options for simple download files
        options.setProfile(firefoxDriverProfile());
        if (HEADLESS_MODE) options.addArguments("--headless");
        return options;
    }

    private static Map<String, Object> chromeDriverOptions() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", DOWNLOAD_DIRECTORY);
        prefs.put("download.prompt_for_download", false);
        prefs.put("disable-popup-blocking", true);
        prefs.put("profile.default_content_settings.popups", 0);
        return prefs;
    }

    private static FirefoxProfile firefoxDriverProfile() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.dir", DOWNLOAD_DIRECTORY);
        profile.setPreference("browser.helperApps.neverAsk.openFile",
                "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        profile.setPreference("browser.download.manager.focusWhenStarting", false);
        profile.setPreference("browser.download.manager.useWindow", false);
        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
        profile.setPreference("browser.download.manager.closeWhenDone", false);
        return profile;
    }

    public static EdgeOptions edgeOptions() {
        return new EdgeOptions();
    }

}
