package com.omidmohebbise.java21examples;

import java.util.*;

public class Jep431SequencedCollections {

    public static void main(String[] args) {
//        Example1SequenceSet();
        Example2SequneceMap();
    }

    private static void SequenceSetExample() {
        // Create a SequencedSet using LinkedHashSet (insertion-ordered)
        SequencedSet<String> sequencedSet = new LinkedHashSet<>();

        // Add elements to the set
        sequencedSet.add("Apple");
        sequencedSet.add("Banana");
        sequencedSet.add("Cherry");

        // Access the first and last elements
        System.out.println("First element: " + sequencedSet.getFirst()); // Apple
        System.out.println("Last element: " + sequencedSet.getLast());   // Cherry

        // Remove the first element
        sequencedSet.removeFirst();
        System.out.println("After removing first element: " + sequencedSet); // [Banana, Cherry]

        // Reverse view of the set
        SequencedSet<String> reversedSet = sequencedSet.reversed();
        System.out.println("Reversed set: " + reversedSet); // [Cherry, Banana]
    }

    public static void Example2SequneceMap() {
        // Create a SequencedMap using LinkedHashMap (insertion-ordered)
        SequencedMap<String, Integer> sequencedMap = new LinkedHashMap<>();

        // Add entries to the map
        sequencedMap.put("John", 30);
        sequencedMap.put("Alice", 25);
        sequencedMap.put("Bob", 35);

        // Access the first and last entries
        Map.Entry<String, Integer> firstEntry = sequencedMap.firstEntry();
        Map.Entry<String, Integer> lastEntry = sequencedMap.lastEntry();

        System.out.println("First entry: " + firstEntry);  // John=30
        System.out.println("Last entry: " + lastEntry);    // Bob=35

        // Remove the first entry
        sequencedMap.remove(sequencedMap.firstEntry().getKey());
        System.out.println("After removing first entry: " + sequencedMap); // {Alice=25, Bob=35}

        // Reverse view of the map
        SequencedMap<String, Integer> reversedMap = sequencedMap.reversed();
        System.out.println("Reversed map: " + reversedMap); // {Bob=35, Alice=25}
    }

}
