package com.omidmohebbise.java21examples;

public class Jep440RecordPatterns {
    public record Person(String name, int age) {}

    public static void main(String[] args) {
        Object obj = new Person("Alice", 30);


        // Use pattern matching with record pattern in a switch statement
        String result = switch (obj) {
            case Person(String name, int age) ->
                    "Name: " + name + ", Age: " + age; // Deconstruct the record into name and age
            default -> "Unknown object";
        };

        System.out.println(result);


        Object obj1 = "";

        result = switch (obj1) {
            case Person(String name, int age) ->
                    "Name: " + name + ", Age: " + age; // Deconstruct the record into name and age
            default -> "Unknown object";
        };

        System.out.println(result);
    }
}
