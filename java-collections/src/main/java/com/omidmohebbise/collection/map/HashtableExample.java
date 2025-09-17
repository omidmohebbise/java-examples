package com.omidmohebbise.collection.map;

public class HashtableExample {
    public static void main(String[] args) {
        // Hashtable is synchronized and does not allow null keys or values
        java.util.Hashtable<String, String> hashtable = new java.util.Hashtable<>();
        hashtable.put("key1", "value1");
        hashtable.put("key2", "value2");
        // hashtable.put(null, "value3"); // This will throw NullPointerException
        // hashtable.put("key3", null); // This will throw NullPointerException
        System.out.println("Hashtable: " + hashtable);
    }
}
