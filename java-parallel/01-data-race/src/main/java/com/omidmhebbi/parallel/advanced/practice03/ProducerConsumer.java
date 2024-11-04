package com.omidmhebbi.parallel.advanced.practice03;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

class Charger extends Thread {
    public static Semaphore ports = new Semaphore(4);

    public Charger(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            ports.acquire();
            System.out.println(getName() + " am using a port");
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(getName() + " am done.");
            ports.release();
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Charger("User " + i).start();
        }


        System.out.println("The end.");
    }

}
