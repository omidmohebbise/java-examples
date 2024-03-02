package com.oms.core.tricky;

import java.util.List;

public class StreamAPIs {
    public static void main(String[] args) {
        var fruits = List.of("banana", "apple", "cucumber", "orange");

        fruits.stream().map(f->{
            if (f.compareTo("apple")==0) {
                System.out.println("apple => appli");
                return "appli";
            }else return f;
        });

        fruits.stream().map(f->{
            if (f.compareTo("apple")==0) {
                System.out.println("apple => appli");
                return "appli";
            }else return f;
        });



    }
}
