package com.omidmhebbi.parallel.basic.practice5;

class MyThread extends Thread{
    public MyThread(String name) {
        super(name);
    }
    public static int counter = 0;
    @Override
    public void run() {
        int i = 0 ;
        //while (i++ < 10 ){    => in this case it is ok but in large number of accessing to the share resource the result is not the same per execution.
        while (i++ < 10000000 ){
            counter++;
        }
    }
}

public class DataRace {

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
