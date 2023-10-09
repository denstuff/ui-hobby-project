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
        Matcher matcher = pattern.matcher(string);
        if (matcher.find())
            return matcher.group(group);
        else return null;
    }
}
