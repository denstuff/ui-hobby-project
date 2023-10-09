package com.qa.hobby.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static String stringDataTimeModifier(String source) {
        return source + "-" + new SimpleDateFormat("yyyyMMdd-HHmmss-SSS").format(new Date());
    }

    public static String getSubstringByRegex(String string, Pattern pattern, int group) {
        Matcher ticketIdMatcher = pattern.matcher(string);
        if (ticketIdMatcher.find())
            return ticketIdMatcher.group(group);
        else return null;
    }
}
