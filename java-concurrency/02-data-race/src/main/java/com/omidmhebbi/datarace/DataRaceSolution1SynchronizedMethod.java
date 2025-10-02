package com.omidmhebbi.datarace;



public class DataRaceSolution1SynchronizedMethod {

    public static void main(String[] args) throws InterruptedException {
        SynchronizedThread thread1 = new SynchronizedThread("First Thread");
        SynchronizedThread thread2 = new SynchronizedThread("Second Thread");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


        System.out.println("Counter = " + SynchronizedThread.counter);

    }

    static class SynchronizedThread extends Thread{
        public SynchronizedThread(String name) {
            super(name);
        }
        public  static int counter = 0;

        private static synchronized void incrementCounter(){
            counter++;
        }

        @Override
        public void run() {
            int i = 0 ;
            //while (i++ < 10 ){    => in this case it is ok but in large number of accessing to the share resource the result is not the same per execution.
            while (i++ < 10000000 ){
                incrementCounter();
            }

            System.out.println(counter);
        }
    }
}
