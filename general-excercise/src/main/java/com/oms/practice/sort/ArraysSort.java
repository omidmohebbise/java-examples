package com.oms.practice.sort;

import java.util.Arrays;
import java.util.Comparator;

public class ArraysSort {
    public static void main(String[] args) {
        String[] strArr = {"a", "big", "setup", "box" };
        Arrays.sort(strArr, Comparator.comparingInt(String::length));
        Arrays.stream(strArr).forEach(System.out::println);
    }
}
