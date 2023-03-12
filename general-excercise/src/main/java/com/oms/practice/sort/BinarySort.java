package com.oms.practice.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class BinarySort {
    public static void main(String[] args) {
        int[] ints = IntStream.generate(() -> new Random().nextInt(800)).limit(8000).toArray();
        System.out.println(Arrays.toString(ints));
        for (int j = 0; j < ints.length - 1; j++) {
            for (int i = j+1; i < ints.length; i++) {
                int temp = ints[j];
                if (temp > ints[i]) {
                    ints[j] = ints[i ];
                    ints[i] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(ints));

    }
}
