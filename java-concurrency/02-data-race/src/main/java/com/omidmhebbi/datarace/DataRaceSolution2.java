package com.omidmhebbi.datarace;

import java.util.concurrent.Semaphore;

class MyThread2 extends Thread {
    public MyThread2(String name) {
        super(name);
        semaphore = new Semaphore(1);
    }

    public static int counter = 0;
    private static Semaphore semaphore;

    private void counterInc() {
        try {
            semaphore.acquire();
            this.counter++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    @Override
    public void run() {
        int i = 0;
        //while (i++ < 10 ){    => in this case it is ok but in large number of accessing to the share resource the result is not the same per execution.
        while (i++ < 10000000) {
            counterInc();

        }
    }
}

public class DataRaceSolution2 {

    public static void main(String[] args) throws InterruptedException {
        MyThread2 thread1 = new MyThread2("First Thread");
        MyThread2 thread2 = new MyThread2("Second Thread");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


        System.out.println("Counter = " + MyThread2.counter);


    }
}
