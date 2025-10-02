package com.omidmhebbi.datarace;

import java.util.concurrent.Semaphore;


public class DataRaceSolution3Semaphore {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            var thread = new ThreadWithSemaphore("%d Thread".formatted(i));
            thread.start();
            thread.join();
        }

        System.out.println("Counter = " + ThreadWithSemaphore.counter);


    }

    static class ThreadWithSemaphore extends Thread {
        public ThreadWithSemaphore(String name) {
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
}
