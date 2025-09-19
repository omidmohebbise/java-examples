package com.omidmhebbise.future;

import java.util.concurrent.*;

public class CombinedExample {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenApply(String::toUpperCase)
                .thenAccept(System.out::println);
    }
}
