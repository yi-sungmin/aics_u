package com.nobiz.aics_u.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeUtil {

    public static String getCurrentTimeByFormat(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    public static String getCurrentDateTime() {
        return getCurrentTimeByFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static String parseDate(String dateString, String inputFormat, String outputFormat) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputFormat);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat);

        LocalDateTime parsedDateTime = LocalDateTime.parse(dateString, inputFormatter);
        return parsedDateTime.format(outputFormatter);
    }

    public static long getCurrentTimeInMillis() {
        return System.currentTimeMillis();
    }
}
