package com.omidmhebbi.datarace;

/**
 * The volatile keyword in Java ensures that changes to a variable are immediately visible to all threads.
 * It prevents threads from caching the variable locally, guaranteeing that every read reflects the most recent write.
 * However, it does not provide atomicity for compound operations like counter++.
 * Use volatile for simple flags or variables where visibility, not atomicity, is required.
 */
public class DataRaceSolutionXVolatileExample {
    private volatile int counter = 0;

    public void incrementCounter() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        DataRaceSolutionXVolatileExample counterExample = new DataRaceSolutionXVolatileExample();

        // Create multiple threads to increment the counter
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counterExample.incrementCounter();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counterExample.incrementCounter();
            }
        });

        // Start the threads
        thread1.start();
        thread2.start();

        // Wait for the threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final counter value
        System.out.println("Counter value: " + counterExample.getCounter());
    }
}
