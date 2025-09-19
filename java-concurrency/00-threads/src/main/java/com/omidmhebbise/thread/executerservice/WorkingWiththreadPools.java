package com.omidmhebbise.thread.executerservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WorkingWiththreadPools {
    public static void main(String[] args) {
        //executeFixedThreadPool();
        executeScheduledThreadPool();
    }

    private static void executeScheduledThreadPool() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        for (int i = 1; i <= 10; i++) {
            scheduledExecutorService.schedule(new Task(i),10, TimeUnit.SECONDS);
        }
        scheduledExecutorService.submit(new Task(100));
        scheduledExecutorService.shutdown();
    }

    private static void executeFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 10; i++) {
            executorService.execute(new Task(i));
        }
        executorService.submit(new Task(100));
        executorService.shutdown();
    }


    private static class Task implements Runnable {
        int index;
        public Task(int i) {
            this.index=i;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread " + index + "is executing");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

