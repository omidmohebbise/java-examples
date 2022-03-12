package com.oms.practice;

import java.util.Stack;

public class IntegerToBinary {
    public static void main(String[] args) {
        int num = 48;
        Stack<Short> binaries = new Stack<>();
       while( num/2 >= 1) {
            binaries.push((short) (num %2));
            num = num/2;
        }
       binaries.push((short) num);
       while (!binaries.isEmpty())
        System.out.print(binaries.pop());
    }
}
