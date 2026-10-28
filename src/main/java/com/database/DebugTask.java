package com.database;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class DebugTask {

    static final ZoneId ZONE = ZoneId.of("Europe/Kyiv");

    // are 2 rents for same car overlapping: [aStart, aEnd) and [bStart, bEnd)
    static boolean overlaps(LocalDateTime aStart,
                            LocalDateTime aEnd,
                            LocalDateTime bStart,
                            LocalDateTime bEnd) {
        return !aStart.isAfter(bEnd)
                && !bStart.isAfter(aEnd);
    }

    // moment of booking creating
    static Instant createdAt(Clock clock) {
        return LocalDateTime.now(clock)
                .toInstant(ZoneOffset.UTC);
    }

    // how much hours does client pay
    // each started hour should be payed as entire
    static long billableHours(LocalDateTime start,
                              LocalDateTime end) {
        return Duration.between(start, end).toHours();
    }

    public static void main(String[] args) {
        // «now» is fixed: 01.11.2026, 10:00 (Kyiv time)
        Clock clock = Clock.fixed(
                Instant.parse("2026-11-01T08:00:00Z"), ZONE);

        LocalDate day = LocalDate.of(2026, 11, 1);
        LocalDateTime t1000 = day.atTime(10, 0);
        LocalDateTime t1200 = day.atTime(12, 0);
        LocalDateTime t1230 = day.atTime(12, 30);
        LocalDateTime t1400 = day.atTime(14, 0);

        check("Оренди 10:00-12:00 і 12:00-14:00 не перетинаються",
                false, overlaps(t1000, t1200, t1200, t1400));
        check("Час створення бронювання",
                Instant.parse("2026-11-01T08:00:00Z"),
                createdAt(clock));
        check("Оренда 10:00-12:30 - це 3 оплачувані години",
                3L, billableHours(t1000, t1230));
    }

    static void check(String name, Object expected,
                      Object actual) {
        String result = expected.equals(actual) ? "OK  " : "FAIL";
        System.out.println(result + " " + name);
        System.out.println("     очікувано: " + expected
                + ", фактично: " + actual);
    }
}
