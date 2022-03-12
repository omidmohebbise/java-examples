package com.oms.core.tricky;

public class FloatingPoint {
    public static void main(String[] arr){
        System.out.println(0.1*3 == 0.3);
        System.out.println(0.1*2 == 0.2);
    }
}
/*
The first print statement prints “false” and the second prints “true”.
 This happens simply because of the rounding error in floating-point numbers.
  Only numbers that are powers of 2 can be represented precisely by a simple binary representation.
   Numbers that do not correspond to a power of 2 must be rounded to fit into a limited number of bits.
    Here, because Java uses double to represent decimal values, only 64 bits are available to represent the number.
 Therefore, 0.1*3 would not be equal to 0.3.
 */
