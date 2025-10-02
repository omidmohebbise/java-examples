package com.omidmhebbi.datarace;


import java.util.concurrent.atomic.AtomicInteger;



public class DataRaceSolution4UsingAtomic {

    public static void main(String[] args) throws InterruptedException {
        AtomicCounterThread thread1 = new AtomicCounterThread("First Thread");
        AtomicCounterThread thread2 = new AtomicCounterThread("Second Thread");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


        System.out.println("Counter = " + AtomicCounterThread.counter);


    }

    static class AtomicCounterThread extends Thread {
        public static AtomicInteger counter = new AtomicInteger(0);

        public AtomicCounterThread(String name) {
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
}
