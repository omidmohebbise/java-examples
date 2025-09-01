package com.omidmhebbi.parallel.basic.practice2;

class MyThread extends Thread{
    public int counter;
    public static boolean active = true;

    public MyThread(String name) {
        super(name);
        this.counter = 0;
    }

    @Override
    public void run() {
        while (active){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            ++counter;
            // System.out.println(this.getName() + ": " + counter++);
        }
    }
}

public class Practice2 {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread1 = new MyThread("First Thread");
        MyThread myThread2 = new MyThread("Second Thread");
        myThread1.start();
        myThread2.start();
        Thread.sleep(100);
        MyThread.active = false;
        myThread1.join();
        myThread2.join();

        System.out.println(myThread1.getName() + "Counter = " + myThread1.counter);
        System.out.println(myThread2.getName() + "Counter = " + myThread2.counter);


    }
}
