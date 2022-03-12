package com.oms.core.synchronization.basic_example;

public class SynchronizeResourceClass {
    public synchronized void countInputNumberFiveTimes(int number) {
        for (int i = 1; i <= 5; i++) {
            System.out.println(number + i);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
