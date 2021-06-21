package com.epam.izh.rd.online.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleDateService implements DateService {

    @Override
    public String parseDate(LocalDate localDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatDate = localDate.format(formatter);
        return formatDate;
    }

    @Override
    public LocalDateTime parseString(String string) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime resultDateTime = LocalDateTime.parse(string, dateTimeFormatter);
        return resultDateTime;
    }

    @Override
    public String convertToCustomFormat(LocalDate localDate, DateTimeFormatter formatter) {

        return localDate.format(formatter);
    }

    @Override
    public long getNextLeapYear() {

        LocalDate localDate = LocalDate.now();
        long year = localDate.getYear();
        while (year % 4 != 0) {
            year = year + 1;
        }
        return year;
    }

    @Override
    public long getSecondsInYear(int year) {

        return (LocalDate.of(year, 1, 1).lengthOfYear()) * 24 * 3600;
    }


}
