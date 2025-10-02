package com.omidmhebbi.other;

public class InheritableThreadLocalExample {
    private static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("Hello from the main thread");

        Thread childThread = new Thread(() -> {
            String value = threadLocal.get();
            System.out.println("Value in child thread: " + value);
        });
        childThread.start();
    }
}
