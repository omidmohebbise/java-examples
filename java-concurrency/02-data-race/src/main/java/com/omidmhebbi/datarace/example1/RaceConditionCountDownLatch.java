package com.omidmhebbi.datarace.example1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyCountDownLatchThread extends Thread {
    public static int counter = 1;
    public static Lock threadLock = new ReentrantLock();
    private static final CountDownLatch cyclicBarrier = new CountDownLatch(5);

    public MyCountDownLatchThread(String name) {
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
            cyclicBarrier.countDown();

        } else {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
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

public class RaceConditionCountDownLatch {
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
