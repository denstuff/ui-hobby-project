package com.qa.hobby.driver;

public enum BrowserType {
    CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("edge"),

    UNRECOGNIZED("");

    private final String name;

    BrowserType(String name) {
        this.name = name;
    }

    public String getBrowserName() {
        return this.name;
    }

    public static BrowserType getBrowserTypeByName(String name) {
        BrowserType result = UNRECOGNIZED;
        for (BrowserType type : values()) {
            if (type.getBrowserName().equalsIgnoreCase(name)) {
                result = type;
                break;
            }
        }
        return result;
    }
}
