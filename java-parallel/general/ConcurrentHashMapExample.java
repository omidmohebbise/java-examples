package com.omidmohebbise.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, Object> map = new ConcurrentHashMap<>(10,2);

        try (ExecutorService executorService = Executors.newFixedThreadPool(100)) {
            for (int i = 0; i < 1000; i++) {
                final int index = i;
                executorService.submit(() -> {
                    var key = index % 5;
                    map.put(key, index);
                    if(key == 0 ) {
                        System.out.println(index);
                    }
                });
            }
            System.out.println("Final Map Size: " + map.size());

            System.out.println("keys:   " + map.keySet());
            System.out.println("values: " + map.values());
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
