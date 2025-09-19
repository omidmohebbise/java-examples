package com.omidmhebbise.thread.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class ExecutorExamples {
    private static void tasks(Executor executor, String newFixedThreadPool) {
        for (int i = 1; i <= 10; i++) {
            int index = i;
            executor.execute(() -> {
                try {
                    sleep(1000 / index);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(index + ": Hello from " + newFixedThreadPool);
            });
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        Executor singleThreadExecutor = executeWithSingleThreadExecutor();
//        Executor newFixedThreadPoolExecutor = Executors.newFixedThreadPool(4);
        Executor newVirtualThreadPerTaskExecutorExecutor = Executors.newVirtualThreadPerTaskExecutor();
        tasks(newVirtualThreadPerTaskExecutorExecutor, "newVirtualThreadPerTaskExecutorExecutor");
        Thread.sleep(1000);
    }

    private static void executeWithSingleThreadExecutor() {
        Executor executor = Executors.newSingleThreadExecutor();
        tasks(executor, "newFixedThreadPool");
    }
}
