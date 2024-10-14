package com.omidmohebbise.java21examples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Jep444VirtualThreads {
    public static void main(String[] args) throws InterruptedException {
        //example1();
        int taskCount = 1_000_000; // Create 1 million tasks
        List<Thread> threads = new ArrayList<>();

        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < taskCount; i++) {
            Thread vThread = Thread.ofVirtual().start(() -> {
                // Simulate work by sleeping
                try {
                    System.out.print(atomicInteger.addAndGet(1)+", ");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads.add(vThread);
        }

        // Wait for all threads to complete
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("All tasks completed and atomicInteger = " + atomicInteger.get());
    }

    private static void example1() throws InterruptedException {
        var thread = Thread.ofVirtual().start(() -> System.out.println("Hello"));
        thread.join();
    }
}
