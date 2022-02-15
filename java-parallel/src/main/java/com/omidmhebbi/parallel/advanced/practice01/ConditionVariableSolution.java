package com.omidmhebbi.parallel.advanced.practice01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyThread1 extends Thread {
    public static int turnCounter = 10;
    public static int threadCount=0;
    private final int threadId;
    public static Lock turnLock = new ReentrantLock();
    public static Condition condition = turnLock.newCondition();


    public MyThread1(String name, int threadId) {
        super(name);
        threadCount++;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        while (turnCounter > 0){
            turnLock.lock();

            try {
                while (turnCounter % threadCount != threadId && turnCounter  > 0) {
                    System.out.println(getName() + "\t" + "this is not my turn");
                    condition.await();
                }
                System.out.println(getName() + "\t" + "it is my turn => " + threadCount + " , " + threadId + " , " + turnCounter );
                turnCounter--;
                condition.signalAll();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                turnLock.unlock();
            }
        }
    }
}
public class ConditionVariableSolution {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new MyThread1("Thread" + i , i).start();
        }
    }

}
