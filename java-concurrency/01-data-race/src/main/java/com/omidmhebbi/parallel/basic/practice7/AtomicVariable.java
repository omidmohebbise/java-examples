package com.omidmhebbi.parallel.basic.practice7;

import java.util.concurrent.atomic.AtomicInteger;

class MyThread extends Thread{
    public MyThread(String name) {
        super(name);
    }
    //public static int counter = 0;

    //without atomic variable the result is not true and the same in different execution
    public static AtomicInteger counter = new AtomicInteger(0);
    @Override
    public void run() {
        int i = 0 ;
        while (i++ < 10000000 ){
            //counter++;
            counter.incrementAndGet();
        }
    }
}

public class AtomicVariable {

    public static void main(String[] args) throws InterruptedException {
        MyThread thread1 = new MyThread("First Thread");
        MyThread thread2 = new MyThread("Second Thread");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


        System.out.println("Counter = " + MyThread.counter);


    }
}
