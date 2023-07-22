package com.omidmhebbi.parallel.basic.practice5;

class ModifiedThread extends Thread{
    public ModifiedThread(String name) {
        super(name);
    }
    public  static int counter = 0;

    private static synchronized void incrementCounter(){
        counter++;
    }
    @Override
    public void run() {
        int i = 0 ;
        //while (i++ < 10 ){    => in this case it is ok but in large number of accessing to the share resource the result is not the same per execution.
        while (i++ < 10000000 ){
            incrementCounter();
        }

        System.out.println(counter);
    }
}

public class DataRaceSolution1 {

    public static void main(String[] args) throws InterruptedException {
        ModifiedThread thread1 = new ModifiedThread("First Thread");
        ModifiedThread thread2 = new ModifiedThread("Second Thread");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


        System.out.println("Counter = " + ModifiedThread.counter);

    }
}
