package com.omidmhebbi.datarace;




public class DataRaceSolution2SynchronizedBlock {

    public static void main(String[] args) throws InterruptedException {
        SynchronizedCounterBlock thread1 = new SynchronizedCounterBlock("First Thread");
        SynchronizedCounterBlock thread2 = new SynchronizedCounterBlock("Second Thread");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


        System.out.println("Counter = " + SynchronizedCounterBlock.counter);


    }
    static class SynchronizedCounterBlock extends Thread {
        public SynchronizedCounterBlock(String name) {
            super(name);
        }

        public static int counter = 0;

        private void counterInc() {
            synchronized (SynchronizedCounterBlock.class) {
                counter++;
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
