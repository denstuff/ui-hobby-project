package com.qa.hobby.data;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.provider.Arguments;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import static com.qa.hobby.config.ConfigProvider.*;
import static com.qa.hobby.utils.FileUtils.createTempFile;
import static com.qa.hobby.utils.StringUtils.stringDataTimeModifier;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
public class TestDataProvider {

    static Stream<Arguments> createTicketsData() {
        String strModifier = stringDataTimeModifier(EMPTY);
        // removing the leading zero
        String dayOfMonth = String.valueOf(
                Integer.parseInt(new SimpleDateFormat("dd").format(new Date())));
        return Stream.of(
                Arguments.of("Django Helpdesk"
                        , "Проблема-1" + strModifier
                        , "Описание 1 " + strModifier
                        , "1. Critical"
                        , dayOfMonth
                        , createTempFile(1 + strModifier, FILE_NAME_PRIFIX, FILE_EXTENSION)
                        , "email1@not.cy"),
                Arguments.of("Some Product"
                        , "Проблема-2" + strModifier
                        , "Описание 2 " + strModifier
                        , "2. High"
                        , dayOfMonth
                        , createTempFile(2 + strModifier, FILE_NAME_PRIFIX, FILE_EXTENSION)
                        , "email2@not.cy")
        );
    }
}
