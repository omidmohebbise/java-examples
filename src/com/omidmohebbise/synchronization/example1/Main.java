package com.omidmohebbise.synchronization.example1;

public class Main {
    public static void main(String[] args) {
        ResourceClass resource = new ResourceClass();//only one object
        Thread1 t1=new Thread1(resource);
        Thread2 t2=new Thread2(resource);


        SynchronizeResourceClass synchronizeResource = new SynchronizeResourceClass();//only one object
        Thread1 t3=new Thread1(synchronizeResource);
        Thread2 t4=new Thread2(synchronizeResource);

        //without synchronization
        //t1.start();
        //t2.start();
        //with synchronization
        t3.start();
        t4.start();



    }
}
