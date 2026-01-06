package com.omidmohebbise.java21examples;

public class JEP441PatternMatchingForSwitch {
    public static void main(String[] args) {
        Object obj1 = "Hello, Pattern Matching!";
        Object obj2 = 123;
        Object obj3 = 12.34;

        System.out.println(process(obj1));  // Output: String with length: 23
        System.out.println(process(obj2));  // Output: Integer with value: 123
        System.out.println(process(obj3));  // Output: Unsupported type: class java.lang.Double
    }

    // Method to process different types of objects using switch with pattern matching
    static String process(Object obj) {
        return switch (obj) {
            case String s -> "String with length: " + s.length();
            case Integer i -> "Integer with value: " + i;
            case null -> "Null value";
            default -> "Unsupported type: " + obj.getClass();
        };
    }
}
