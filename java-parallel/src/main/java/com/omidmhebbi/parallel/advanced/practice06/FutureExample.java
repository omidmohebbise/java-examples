package com.omidmhebbi.parallel.advanced.practice06;

import java.util.concurrent.*;

class DoATaskAndGiveMeTheAnswer implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("The task started...");
        Thread.sleep(3000);
        return "Task is down.";
    }
}
public class FutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> result = service.submit(new DoATaskAndGiveMeTheAnswer());
        System.out.println("do other thing");
        for (int i = 0; i < 100; i++) {
            System.out.print(".");
            Thread.sleep(100);
        }
        System.out.println();
        System.out.println(result.get());
        System.out.println("The end.");
        service.shutdown();
    }
}
