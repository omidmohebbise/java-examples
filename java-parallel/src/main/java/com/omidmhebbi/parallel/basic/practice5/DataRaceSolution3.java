package com.omidmhebbi.parallel.basic.practice5;


class MyThread3 extends Thread {
    public MyThread3(String name) {
        super(name);
    }

    public static int counter = 0;


    private void counterInc() {
        synchronized (this) {
            this.counter++;
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

public class DataRaceSolution3 {

    public static void main(String[] args) throws InterruptedException {
        MyThread3 thread1 = new MyThread3("First Thread");
        MyThread3 thread2 = new MyThread3("Second Thread");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


        System.out.println("Counter = " + MyThread3.counter);


    }
}
