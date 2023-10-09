package com.qa.hobby.exeption;

public class AutotestError extends AssertionError {

    public AutotestError(String message) {
        super(message);
    }
}
