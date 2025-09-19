package com.omidmhebbise.thread.executor;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class ExecutorServiceExamples {

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

    private static class SimpleCallable implements java.util.concurrent.Callable<Integer> {
        private final int id;

        public SimpleCallable(int id) {
            this.id = id;
        }

        @Override
        public Integer call() throws Exception {
            Thread.sleep(1000 / id);
            System.out.println("Task " + id + " completed");
            return 1000 / id;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //      executorService.execute(()->tasks(executorService, "newFixedThreadPool1"));
        //      submitExample1();
                invokeAllExample();
        //        invokeAnyExample();
    }

    private static void invokeAnyExample() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        var future = executorService.invokeAny(List.of(new SimpleCallable(1), new SimpleCallable(2), new SimpleCallable(3)));
        System.out.println(future.intValue());
        executorService.shutdown();
    }

    private static void invokeAllExample() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        var futures = executorService.invokeAll(List.of(new SimpleCallable(1), new SimpleCallable(2), new SimpleCallable(3)));

        for (var future : futures) {
            System.out.println("Result: " + future.get());
        }
        executorService.shutdown();
    }

    private static void submitExample1() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future future = executorService.submit(() -> tasks(executorService, "newFixedThreadPool2"));
        System.out.println("isDone: " + future.isDone());
        System.out.println("isCanceled: " + future.isCancelled());

        executorService.shutdown();
        System.out.println("isShutDown: " + executorService.isShutdown());
        System.out.println("Terminated: " + executorService.isTerminated());
        System.out.println(executorService.awaitTermination(1, TimeUnit.SECONDS));
        sleep(2000);
        System.out.println("Terminated: " + executorService.isTerminated());
    }

    private static void submitExample2() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future future = executorService.submit(() -> tasks(executorService, "newFixedThreadPool2"));

    }
}
