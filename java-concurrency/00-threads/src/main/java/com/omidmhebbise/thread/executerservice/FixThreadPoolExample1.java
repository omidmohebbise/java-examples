package com.omidmhebbise.thread.executerservice;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixThreadPoolExample1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Thread " + Thread.currentThread().getName() + " is executing");
                Thread.sleep(1000);
                return (new Random().nextInt(100));
            }
        };

        for (int i = 0; i < 10; i++) {
            System.out.println( fixedThreadPool.submit(callable).get().intValue());
        }
    }
}
