package com.omidmhebbise.thread.executerservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyThread extends Thread{

    @Override
    public void run() {
       System.out.println(Thread.currentThread().getName() + "  => Executing");
    }
}
public class FixedThreadPoolExampleUseBlockingQueue {
    public static void main(String[] args) {
        int processNumber = Runtime.getRuntime().availableProcessors();
        System.out.println(processNumber);
        ExecutorService pool = Executors.newFixedThreadPool(processNumber);
        for (int i = 0; i < 100; i++) {
            pool.submit(new MyThread());
        }
        pool.shutdown();
    }
}
