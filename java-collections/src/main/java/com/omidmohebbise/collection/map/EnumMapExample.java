package com.omidmohebbise.collection.map;

import java.time.DayOfWeek;

public class EnumMapExample {

    public static void main(String[] args) {
        //EnumMap is a specialized Map implementation for use with enum type keys.
        //It is a part of the Java Collections Framework and is found in the java.util package
        //EnumMap is more efficient than other Map implementations like HashMap or TreeMap when the keys are enum types.
        //EnumMap maintains the natural order of the enum keys (the order in which they are declared in the enum).
        //EnumMap does not allow null keys, but it allows null values.
        //EnumMap is not synchronized, so if multiple threads access an Enum
        //Map concurrently, and at least one of the threads modifies the map structurally,
        //it must be synchronized externally.
        //Here is an example of how to use EnumMap in Java:
        java.util.EnumMap<DayOfWeek, String> enumMap = new java.util.EnumMap<>(DayOfWeek.class);
        enumMap.put(DayOfWeek.MONDAY, "First day of the week");
        enumMap.put(DayOfWeek.TUESDAY, "First day of the week");
        enumMap.put(DayOfWeek.WEDNESDAY, "First day of the week");
        enumMap.put(DayOfWeek.THURSDAY, "First day of the week");
        enumMap.put(DayOfWeek.FRIDAY, null);
    }
}
