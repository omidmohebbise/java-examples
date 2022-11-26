package com.omidmohebbise.java11;

import java.util.function.Function;

public class LocalVariable {
    public static void main(String[] args) {
        example1();
    }

    public static void example1() {
        Function<String, String> func1 = (var a) -> {
            System.out.println(a);
            return a + ".";
        };
        System.out.println(func1.apply("Hello from other side"));


    }
}
