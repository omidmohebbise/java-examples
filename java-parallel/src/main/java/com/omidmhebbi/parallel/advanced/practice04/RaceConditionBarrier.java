package com.omidmhebbi.parallel.advanced.practice04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyBarrierThread extends Thread {
    public static int counter = 1;
    public static Lock threadLock = new ReentrantLock();
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    public MyBarrierThread(String name) {
        super(name);
    }

    @Override
    public void run() {

        if (getName().contains("Adder")) {
            threadLock.lock();
            try {
                counter += 2;
                System.out.println(getName() + " => " + counter);
            } finally {
                threadLock.unlock();
            }
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        } else {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            threadLock.lock();
            try {
                counter *= 2;
                System.out.println(getName() + " => " + counter);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                threadLock.unlock();
            }

        }
    }
}

public class RaceConditionBarrier {
    public static void main(String[] args) {
        List<MyCountDownLatchThread> myBarrierThreads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            myBarrierThreads.add(new MyCountDownLatchThread("Doubler" + i));
            myBarrierThreads.add(new MyCountDownLatchThread("Adder" + (i * 2 + 1)));
        }
        myBarrierThreads.forEach(Thread::start);
        myBarrierThreads.forEach(myBarrierThread -> {
            try {
                myBarrierThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(MyCountDownLatchThread.counter);
    }

}
