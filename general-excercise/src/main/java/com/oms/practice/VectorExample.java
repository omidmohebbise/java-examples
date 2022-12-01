package com.oms.practice;

import com.oms.practice.model.SimplePerson;

import java.util.Vector;


public class VectorExample {
    public static void main(String[] args) {
      simpleUsage();

    }
    public static void simpleUsage(){
        Vector<SimplePerson> persons = new Vector<>();
        var startTime = System.nanoTime();
        persons.add(new SimplePerson("Omid" , "Mohebbi" , 33));
        persons.add(new SimplePerson("Amir" , "Sadeghi" , 33));
        persons.add(new SimplePerson("ALi" , "Ahmadi" , 33));
        persons.add(new SimplePerson("Hasan" , "Ranjbar" , 33));
        var endTime = System.nanoTime();
        //persons.stream().forEach(System.out::println);
        System.out.println("Execution time in nano second = "+ (endTime-startTime));
    }
}
