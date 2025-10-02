package com.omidmhebbi.datarace;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DataRaceSolution5MutualExclusionWithReentrantLock {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Program started");
        MyThread thread1 = new MyThread("First Thread");
        MyThread thread2 = new MyThread("Second Thread");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


        System.out.println("Counter = " + MyThread.counter);


    }

    static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }

        static int counter = 0;
        static Lock counterLock = new ReentrantLock();

        @Override
        public void run() {

            int i = 0;
            //counterLock.lock();
            while (i++ < 10_000_000) {
                counterLock.lock();
                counter++; // minimize contemplating during the threads
                counterLock.unlock();
            }
//        counterLock.unlock();
        }
    }
}
