package com.omidmhebbise.future;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.*;

class DoATaskAndGiveMeTheAnswer implements Callable<String> {
    private final String name;

    public DoATaskAndGiveMeTheAnswer(String name) {
        this.name = " Thread " + name;
    }

    @Override
    public String call() throws Exception {
        System.out.print(name + "\t ");
        Thread.sleep(1);
        return "Task is down.";
    }
}

class ThreadTask implements Runnable {
    private final String name;

    public ThreadTask(String name) {
        this.name = " Thread " + name;
    }

    @Override
    public void run() {
        System.out.println(name + "\t " + " is done");
    }
}

public class FutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //usingNewSingleThread();
//        usingScheduledExecutorService();

        int[][] testCases = readInputs();

        System.out.println(Arrays.deepToString(testCases));

        //testCases[0] =
        //termination();
    }

    private static int[][] readInputs() {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.valueOf(scanner.nextLine());
        int testCases[][] = new int[count][2];
        for (int i = 0; i < count; i++) {
            String params[] = scanner.nextLine().split(" ");
            testCases[i] [0]= Integer.parseInt(params[0]);
            testCases[i] [1]= Integer.parseInt(params[1]);
        }
        return testCases;
    }


    private static void termination() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ThreadTask("T" + i));
        }
        executorService.shutdown();
        try {
            System.out.println("service termination status: " + executorService.awaitTermination(10, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            e.printStackTrace();
        }
        System.out.println("The end.");
    }

    private static void usingScheduledExecutorService() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        for (int i = 0; i < 10; i++) {
            scheduledExecutorService.scheduleAtFixedRate(new ThreadTask("T" + i), 0, 100, TimeUnit.MILLISECONDS);
        }
        scheduledExecutorService.shutdown();
        System.out.println("The end.");
    }

    private static void usingNewSingleThread() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            Future<String> result = service.submit(new DoATaskAndGiveMeTheAnswer("T" + i));
            System.out.println(result.get());
        }


        System.out.println("The end.");
        service.shutdown();
    }
}
