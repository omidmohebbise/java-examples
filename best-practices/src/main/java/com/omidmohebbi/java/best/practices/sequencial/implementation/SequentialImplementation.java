package com.omidmohebbi.java.best.practices.sequencial.implementation;

public class SequentialImplementation {
    static void main() {
        Step1 sequence = new MySequence();
        sequence
                .doFirst()
                .doSecond()
                .doThird();
    }
}

interface Step1 {
    Step2 doFirst();
}

interface Step2 {
    Step3 doSecond();
}

interface Step3 {
    void doThird();
}

// Implementation
class MySequence implements Step1, Step2, Step3 {
    public Step2 doFirst() {
        System.out.println("Doing first step");
        return this;
    }

    public Step3 doSecond() {
        System.out.println("Doing second step");
        return this;
    }

    public void doThird() {
        System.out.println("Doing third step");
    }
}
