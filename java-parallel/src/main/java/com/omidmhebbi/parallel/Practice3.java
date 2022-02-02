package com.omidmhebbi.parallel;

class MyThreadP3 extends Thread{
    public int counter;


    public MyThreadP3(String name) {
        super(name);
        this.counter = 0;
    }

    @Override
    public void run() {
        while (counter < 15){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            ++counter;
        }
    }
}

public class Practice3 {

    public static void main(String[] args) throws InterruptedException {
        MyThreadP3 myThread1 = new MyThreadP3("First Thread");
        MyThreadP3 myThread2 = new MyThreadP3("Second Thread");
        myThread1.start();
        myThread2.start();

        // Wait until the threads executed completely
        myThread1.join();
        myThread2.join();

        System.out.println(myThread1.getName() + "Counter = " + myThread1.counter);
        System.out.println(myThread2.getName() + "Counter = " + myThread2.counter);


    }
}
