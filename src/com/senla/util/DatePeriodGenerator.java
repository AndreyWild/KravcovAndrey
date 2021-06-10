package com.senla.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class DatePeriodGenerator {

    public static List<LocalDate> toDateList(String startDate, String endDate) {
        final LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        final LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        final long days = start.until(end, ChronoUnit.DAYS);

        return LongStream.rangeClosed(0, days)
                .mapToObj(start::plusDays)
                .collect(Collectors.toList());
    }

    public static List<LocalDate> toDateList(LocalDate startDate, LocalDate endDate) {
        long days = startDate.until(endDate, ChronoUnit.DAYS);

        return LongStream.rangeClosed(0, days)
                .mapToObj(startDate::plusDays)
                .collect(Collectors.toList());
    }
}
