package com.javadeveloper.core.synchronization.basic_example;

public class Thread1 extends Thread {
    ResourceClass resourceClass;
    SynchronizeResourceClass synchronizeResourceClass;

    public Thread1(ResourceClass resourceClass) {
        this.resourceClass = resourceClass;
    }

    public Thread1(SynchronizeResourceClass synchronizeResourceClass) {
        this.synchronizeResourceClass = synchronizeResourceClass;
    }

    @Override
    public void run() {
        if (resourceClass != null)
            resourceClass.countInputNumberFiveTimes(5);
        else if (synchronizeResourceClass != null)
            synchronizeResourceClass.countInputNumberFiveTimes(5);
    }
}
