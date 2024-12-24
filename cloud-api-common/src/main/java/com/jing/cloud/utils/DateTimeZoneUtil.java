package com.jing.cloud.utils;

import java.time.ZonedDateTime;

public class DateTimeZoneUtil {
    public static String getZonedDateTime() {
        ZonedDateTime now = ZonedDateTime.now();
        return now.toString();
    }

    public static void main(String[] args) {
        System.out.println(getZonedDateTime());
    }
}
