package com.omidmhebbi.datarace;



public class SynchronizedMethod {
    public static void main(String[] args) throws InterruptedException {
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

        public static int counter = 0;

        public static synchronized void incrementCounter() {
            counter++;
        }

        @Override
        public void run() {
            int i = 0;
            while (i++ < 10000000) {
                incrementCounter();
            }
        }
    }
}
