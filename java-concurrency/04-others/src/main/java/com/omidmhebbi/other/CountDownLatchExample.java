package com.omidmhebbi.other;

import java.util.concurrent.CountDownLatch;

/**
 * A CountDownLatch in Java is used to make one or more threads wait until a set of operations being performed in other
 * threads completes. A common use case is waiting for multiple tasks to finish before proceeding.
 */
public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int numberOfWorkers = 3;
        CountDownLatch latch = new CountDownLatch(numberOfWorkers);

        for (int i = 1; i <= numberOfWorkers; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " is working");
                // Simulate work
                try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
                System.out.println(Thread.currentThread().getName() + " finished");
                latch.countDown(); // Signal task completion
            }, "Worker-" + (i)).start();
        }

        // Main thread waits for all workers to finish
        latch.await();
        System.out.println("All workers finished. Main thread continues.");
    }
}
