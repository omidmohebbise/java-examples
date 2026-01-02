package com.omidmhebbi.other;


import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VirtualThreadExecutorExample {
    public static void main(String[] args) throws InterruptedException {
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executor.execute(() -> System.out.println("%d)Hello from virtual thread".formatted(finalI)));
        }

        executor.awaitTermination(2, TimeUnit.SECONDS);


    }
}
