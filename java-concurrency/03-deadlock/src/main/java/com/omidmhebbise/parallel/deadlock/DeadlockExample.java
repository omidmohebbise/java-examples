package com.omidmhebbise.parallel.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyThread extends Thread{
    private final Lock firstLock, secondLock;
    public static int counter = 500_000;

    public MyThread(String name, Lock firstLock, Lock secondLock) {
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

            //unlcok
            firstLock.unlock();
            secondLock.unlock();
        }


    }
}
public class DeadlockExample {
    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        ReentrantLock lock3 = new ReentrantLock();

        new MyThread("thread 1" , lock1,lock2).start();
        new MyThread("thread 2" , lock2,lock3).start();
        new MyThread("thread 3" , lock3,lock1).start();
    }
}
