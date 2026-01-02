// File: java-concurrency/04-others/src/main/java/com/omidmhebbi/other/CountDownLatchClearExample.java
package com.omidmhebbi.other;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int numberOfTasks = 3;
        CountDownLatch latch = new CountDownLatch(numberOfTasks);

        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            new Thread(() -> {
                System.out.println("Task " + taskId + " started.");
                try {
                    Thread.sleep(500 + taskId * 300); // Simulate variable work time
                } catch (InterruptedException ignored) {}
                System.out.println("Task " + taskId + " completed.");
                latch.countDown();
            }).start();
        }

        System.out.println("Main thread waiting for tasks to complete...");
        latch.await();
        System.out.println("All tasks completed. Main thread resumes.");
    }
}
