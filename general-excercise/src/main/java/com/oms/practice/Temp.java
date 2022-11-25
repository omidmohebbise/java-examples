package com.oms.practice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Temp {
    public static void main(String[] args) {
        int k = 3;
        int[] arr={4,3,1,1,3,3,2};
        Map<Integer , Integer> repetitions = new HashMap<>();
        for(int i = 0 ; i < arr.length; i++){
            Integer num = repetitions.get(arr[i]);
            if(num ==null)
                repetitions.put(arr[i], 1);
            else
                repetitions.put(arr[i], num.intValue() + 1);
        }
        List<Integer> repetitionsNumber = repetitions.values().stream().sorted().collect(Collectors.toList());
        System.out.println(repetitionsNumber);
        for (int i = 0; i < k; i++) {
            repetitionsNumber.remove(0);
        }
        System.out.println(repetitionsNumber);
    }
}
