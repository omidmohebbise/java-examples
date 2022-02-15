package com.omidmhebbi.parallel.advanced.practice01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyThread extends Thread {
    public static int turnCounter = 10;
    public static int threadCount=0;
    private final int threadId;
    private final static Lock turnLock = new ReentrantLock();
    public static final String ANSI_RESET = "\u001B[0m";
    public MyThread(String name, int threadId) {
        super(name);
        threadCount++;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        while (turnCounter > 0){
            turnLock.lock();

            try {
                if (turnCounter % threadCount == threadId && turnCounter  > 0) {
                    System.out.println(ANSI_RESET + getName() + "\t" + "it is my turn");
                    turnCounter--;
                } else {
                    System.out.println(getName() + "\t" + "this is not my turn");
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                turnLock.unlock();
            }

        }
    }
}
public class ConditionVariableProblem {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new MyThread("Thread" + i , i).start();
        }
    }

}
