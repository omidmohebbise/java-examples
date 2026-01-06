package com.omidmohebbise.java21examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Jep446ScopedValues_Preview {

    // Define a ScopedValue
    private static final ScopedValue<String> USER_CONTEXT = ScopedValue.newInstance();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Set a scoped value and execute a task
        ScopedValue.where(USER_CONTEXT, "Alice").run(() -> {
            System.out.println("Main task running with user: " + USER_CONTEXT.get());

            // Submit a task to the executor
            executorService.submit(() -> {
                // Nested context within the executor
                ScopedValue.where(USER_CONTEXT, "Bob").run(() -> {
                    System.out.println("Nested task running with user: " + USER_CONTEXT.get());
                });
            });

            // Another task in the same scope
            executorService.submit(() -> {
                System.out.println("Another task running with user: " + USER_CONTEXT.get());
            });
        });

        // Shutdown the executor
        executorService.shutdown();
    }
}
