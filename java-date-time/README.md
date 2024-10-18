# Class Hierarchy for Date-Time:

```text

java.lang.Object
└── java.time.temporal.Temporal
├── java.time.chrono.ChronoLocalDate
│    ├── java.time.LocalDate
│    └── java.time.chrono.HijrahDate
│         └── java.time.chrono.JapaneseDate
│              └── java.time.chrono.MinguoDate
│                   └── java.time.chrono.ThaiBuddhistDate
├── java.time.temporal.TemporalAccessor
│    └── java.time.ZoneId
│         ├── java.time.ZoneOffset
│         └── java.time.ZoneRegion
└── java.time.temporal.TemporalAdjuster
├── java.time.LocalTime
├── java.time.OffsetTime
├── java.time.Instant
├── java.time.LocalDateTime
├── java.time.ZonedDateTime
├── java.time.OffsetDateTime
└── java.time.Year
└── java.time.YearMonth
```

# Key Classes in java.time:


**Temporal**: Base interface for all date-time objects that can be manipulated using date-time fields.

**ChronoLocalDate**: Represents a date without time, intended for dates of a specific calendar system (like ISO, Hijrah, etc.).

**LocalDate**: Represents a date without a time-zone (e.g., 2024-10-18).
**ChronoLocalDate subtypes**: Include date types for different calendar systems (e.g., HijrahDate, JapaneseDate).
**LocalDateTime**: Represents both a date and a time without a time zone (e.g., 2024-10-18T14:30:00).

**ZonedDateTime**: Represents a date-time with a time-zone (e.g., 2024-10-18T14:30:00+02:00[Europe/Amsterdam]).

**OffsetDateTime**: Similar to ZonedDateTime, but without the full time-zone information, only the offset from UTC (e.g., 2024-10-18T14:30:00+02:00).

**LocalTime**: Represents a time without a date or a time-zone (e.g., 14:30:00).

**Instant**: Represents an instantaneous point on the time-line (e.g., 2024-10-18T12:30:00Z).

**ZoneId**: Represents a time-zone, and ZoneOffset is a subclass for representing offsets from UTC.

**Year**: Represents just a year (e.g., 2024).

**YearMonth**: Represents a combination of year and month (e.g., 2024-10).