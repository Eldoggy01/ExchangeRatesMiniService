package com.example.exchangeratesminiservice.utils;


import java.text.DateFormat;
import java.util.Calendar;

public class DateTimeUtil {

    private DateTimeUtil() {

    }

    public static String getDateInSpecifiedFormat(int diffFromCurrent, DateFormat dateFormat) {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, diffFromCurrent);
        return dateFormat.format(calendar.getTime());
    }

    public static String getCurrentDateInSpecifiedFormat(DateFormat dateFormat) {
        return getDateInSpecifiedFormat(0, dateFormat);
    }
}
