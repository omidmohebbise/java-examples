package org.omidmohebbise.java.date.time;

import java.time.*;
import java.time.chrono.*;
import java.time.temporal.Temporal;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Temporal: Base interface for date-time objects that can be manipulated using date-time fields
        Temporal temporal = LocalDate.now(); // LocalDate is an implementation of Temporal
        System.out.println("Temporal (LocalDate): " + temporal);

        // ChronoLocalDate: Represents a date without time, intended for dates of specific calendar systems
        ChronoLocalDate chronoLocalDate = HijrahDate.now(); // Example of a Hijrah calendar date
        System.out.println("ChronoLocalDate (HijrahDate): " + chronoLocalDate);

        // LocalDate: A date without a time-zone (YYYY-MM-DD)
        LocalDate localDate = LocalDate.of(2024, 10, 18);
        System.out.println("LocalDate: " + localDate);

        // ChronoLocalDate subtypes: Date types for different calendar systems (HijrahDate, JapaneseDate, etc.)
        ChronoLocalDate hijrahDate = HijrahDate.now();
        System.out.println("ChronoLocalDate (hijrahDate): " + hijrahDate);
        // Example of Japanese calendar date
        ChronoLocalDate japaneseDate = JapaneseDate.now(); // Example of Japanese calendar date
        System.out.println("ChronoLocalDate (JapaneseDate): " + japaneseDate);

        // LocalDateTime: Represents both a date and a time without a time-zone
        LocalDateTime localDateTime = LocalDateTime.of(2024, 10, 18, 14, 30);
        System.out.println("LocalDateTime: " + localDateTime);

        // ZonedDateTime: Represents a date-time with a time-zone
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("Europe/Amsterdam"));
        System.out.println("ZonedDateTime: " + zonedDateTime);

        // OffsetDateTime: Represents a date-time with an offset from UTC
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, ZoneOffset.of("+02:00"));
        System.out.println("OffsetDateTime: " + offsetDateTime);

        // LocalTime: Represents a time without a date or time-zone
        LocalTime localTime = LocalTime.of(14, 30);
        System.out.println("LocalTime: " + localTime);

        // Instant: Represents an instantaneous point on the time-line (UTC)
        Instant instant = Instant.now();
        System.out.println("Instant: " + instant);

        // ZoneId: Represents a time-zone, and ZoneOffset represents UTC offsets
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println("ZoneId: " + zoneId);
        ZoneOffset zoneOffset = ZoneOffset.of("+02:00");
        System.out.println("ZoneOffset: " + zoneOffset);

        // Year: Represents a year
        Year year = Year.of(2024);
        System.out.println("Year: " + year);

        // YearMonth: Represents a combination of year and month
        YearMonth yearMonth = YearMonth.of(2024, Month.OCTOBER);
        System.out.println("YearMonth: " + yearMonth);
    }
}