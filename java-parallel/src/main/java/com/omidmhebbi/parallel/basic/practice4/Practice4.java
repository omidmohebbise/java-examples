package com.omidmhebbi.parallel.basic.practice4;

class MyThreadP4 extends Thread{
    public MyThreadP4(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true){
            System.out.println("Thread is alive...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Practice4 {

    public static void main(String[] args) throws InterruptedException {
        MyThreadP4 myThread1 = new MyThreadP4("First Thread");
        myThread1.setDaemon(true);
        myThread1.start();

        Thread.sleep(500);
        System.out.println("Main Thread executing...");
        Thread.sleep(500);
        System.out.println("Main Thread executing...");
        Thread.sleep(500);
        System.out.println("Main Thread executing...");
        Thread.sleep(500);
        System.out.println("Main Thread executing...");
        Thread.sleep(500);
        System.out.println("Main Thread executing...");

        System.out.println("Main Thread finished");


    }
}
