package com.omidmohebbise.synchronization.example1;

public class SynchronizeResourceClass {
    public synchronized void countInputNumberFiveTimes(int number){
        for (int i = 1; i <= 5; i++) {
            System.out.println(number + i);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
