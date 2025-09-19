package com.omidmhebbise.thread.basic;

public class RuntimeInfoExample {

    public static void main(String[] args) {
        long kilobyte = 1024;
        long megabyte = kilobyte*1024;
        long gigabyte = megabyte*1024;


        Runtime runtime = Runtime.getRuntime();

        //Total designated memory, this will equal the configured -Xmx value
        System.out.println("Total Memory: " + runtime.maxMemory()/ megabyte + " mb");

        //Total allocated memory, is the total allocated space reserved for the java process:
        System.out.println("Total Memory: " + runtime.totalMemory()/ megabyte + " mb");
        System.out.println("free memory: "  + runtime.freeMemory() / kilobyte + " kb   |   " +  + runtime.freeMemory() / megabyte + " Mb");


        System.out.println("Process Id : " + ProcessHandle.current().pid());

    }
}
