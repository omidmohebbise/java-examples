package com.javadeveloper.examples.synchronization.example1;

public class Thread2 extends Thread{
    ResourceClass resourceClass;
    SynchronizeResourceClass synchronizeResourceClass;

    public Thread2(ResourceClass resourceClass) {
        this.resourceClass = resourceClass;
    }

    public Thread2(SynchronizeResourceClass synchronizeResourceClass) {
        this.synchronizeResourceClass = synchronizeResourceClass;
    }

    @Override
    public void run() {
        if (resourceClass != null)
            resourceClass.countInputNumberFiveTimes(100);
        else if (synchronizeResourceClass != null)
            synchronizeResourceClass.countInputNumberFiveTimes(100);
    }
}
