package com.omidmohebbise.collection.map;

public class IdentityHashMapExample {
    public static void main(String[] args) {
        //Tell me about IdentityHashMap
        // An IdentityHashMap is a special type of Map in Java that uses reference equality (==) instead of object equality (equals()) for comparing keys and values. This means that two keys are considered equal only if they refer to the exact same object in memory, rather than having the same content.
        // IdentityHashMap is part of the java.util package and implements the Map interface.
        // It is not synchronized, so if multiple threads access an IdentityHashMap concurrently, and at least one of the threads modifies the map structurally, it must be synchronized externally.
        // IdentityHashMap allows null keys and values.
        // IdentityHashMap is not ordered, which means that the order of the elements in the
        // map is not guaranteed to be the same as the order in which they were added.
        // Here is an example of how to use IdentityHashMap in Java:
        java.util.IdentityHashMap<String, String> identityHashMap = new java.util.IdentityHashMap<>();
        String key1 = new String();
        String key2 = new String();
        identityHashMap.put(key1, "value1");
        identityHashMap.put(key2, "value2");
        identityHashMap.put(key1, "value3"); // This will replace the value for key1
        System.out.println("IdentityHashMap: " + identityHashMap);
    }
}
