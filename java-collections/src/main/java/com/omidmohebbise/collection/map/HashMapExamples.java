package com.omidmohebbise.collection.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapExamples {
    public static void main(String[] args) {
       //add some comment about the HashMap
        // HashMap is a part of Java's collection framework and is used to store data in key-value pairs.
        // It is similar to Hashtable but is unsynchronized and permits null values and the null key
        // HashMap is implemented as a hash table, which means that it uses a hash function to compute the index of the array where the value will be stored.
        // HashMap is not ordered, which means that the order of the elements in the map
        // is not guaranteed to be the same as the order in which they were added.
        // HashMap allows one null key and multiple null values.
        // HashMap is not synchronized, which means that if multiple threads access a HashMap concurrently,
        // and at least one of the threads modifies the map structurally, it must be synchronized externally.
        // Here is an example of how to use HashMap in Java:
         Map<String, Integer> map = new HashMap<>();

        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.print(entry.getKey() + ": " + entry.getValue() + " ");
        }

    }
}