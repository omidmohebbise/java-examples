package com.omidmhebbise.parallel.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyThread1 extends Thread{
    private final Lock firstLock, secondLock;
    public static int counter = 500_000;

    public MyThread1(String name, Lock firstLock, Lock secondLock) {
        super(name);
        this.firstLock = firstLock;
        this.secondLock = secondLock;
    }


    @Override
    public void run() {
        while (counter > 0 ){
            // lock
            firstLock.lock();
            secondLock.lock();

            if(counter>0){
                counter--;
                System.out.print("\r" + counter);
            }

            //unlock
            firstLock.unlock();
            secondLock.unlock();
        }


    }
}
public class ResolvedDeadlockExample {
    public static void main(String[] args) {
        Lock lockA = new ReentrantLock();
        Lock lockB = new ReentrantLock();
        Lock lockC = new ReentrantLock();

        new MyThread1("thread 1" , lockA,lockB).start();
        new MyThread1("thread 2" , lockB,lockC).start();
        new MyThread1("thread 3" , lockA,lockC).start();
    }
}
