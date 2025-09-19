package com.omidmhebbi.datarace.example1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyThread extends Thread {
    public static int counter = 1;
    public static Lock writeLock = new ReentrantLock();

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        if (getName().contains("Doubler")) {
            try {
                writeLock.lock();
                counter *= 2;
                System.out.println(getName()+" => " + counter );
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        }else{
            try {
                writeLock.lock();
                counter += 2;
                System.out.println(getName()+" => " + counter );
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        }
    }
}

public class RaceCondition {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            MyCountDownLatchThread myThread1 = new MyCountDownLatchThread("Doubler" + i);
            MyCountDownLatchThread myThread2 = new MyCountDownLatchThread("Adder" + (i*2 + 1));
            myThread1.start();
            myThread2.start();
            try {
                myThread1.join();
                myThread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




        System.out.println(MyCountDownLatchThread.counter);
    }

}
