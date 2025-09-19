package com.omidmhebbi.datarace;


import java.util.concurrent.atomic.AtomicInteger;

class MyThread4 extends Thread {
    public static AtomicInteger counter = new AtomicInteger(0);

    public MyThread4(String name) {
        super(name);

    }

    @Override
    public void run() {
        int i = 0;
        //while (i++ < 10 ){    => in this case it is ok but in large number of accessing to the share resource the result is not the same per execution.
        while (i++ < 10000000) {
            counter.incrementAndGet();

        }
    }
}

public class DataRaceSolution4 {

    public static void main(String[] args) throws InterruptedException {
        MyThread4 thread1 = new MyThread4("First Thread");
        MyThread4 thread2 = new MyThread4("Second Thread");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


        System.out.println("Counter = " + MyThread4.counter);


    }
}
