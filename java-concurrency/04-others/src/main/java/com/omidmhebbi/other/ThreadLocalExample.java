package com.omidmhebbi.other;

/**
 * ThreadLocal provides thread-local variables.
 * Each thread accessing a ThreadLocal variable has its own, independently initialized copy.
 * This is useful for maintaining per-thread state, such as user sessions or database connections, without using synchronization.
 * In the example, each thread sets and gets its own value from the ThreadLocal variable,
 * so their values do not interfere with each other.
 */
public class ThreadLocalExample {

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> variable = new ThreadLocal<>();

        Thread thread1 = new Thread(()->{
           variable.set("Hello from thread1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(variable.get());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(variable.get());
        });
        Thread thread2 = new Thread(()->{
            variable.set("Hello from thread2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(variable.get());

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(variable.get());
        });

        thread1.start();
        thread2.start();


        thread1.join();
        thread2.join();


        System.out.println("result => "+ variable.get());


    }
}
