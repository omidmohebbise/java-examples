package com.oms.practice;

import com.oms.practice.commpn.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsFunctionality {
    public static void main(String[] args) {
//        integerStream();
//        reduceFunctionality();
//        match();
//        takeWhile();
        var result = Person.getPersons().stream().anyMatch(person -> person.fullName.compareTo("Omid Mohebbi") > 0);
    }

    private static void match() {
        var result = Person.getPersons().stream().anyMatch(person -> person.fullName.compareTo("Omid Mohebbi") > 0);
        System.out.println("anyMatch()  for Omid Mohebbi => " + result);

        var result1 = Person.getPersons().stream().allMatch(person -> person.fullName.compareTo("Omid Mohebbi") > 0);
        System.out.println("allMatch()  for Omid Mohebbi => " + result1);
    }

    private static void takeWhile() {
        var result2 = Person.getPersons().stream().dropWhile(person -> person.fullName.compareTo("Omid Mohebbi") <= 0).collect(Collectors.toList());
        System.out.println("dropWhile()  for person -> person.fullName.compareTo(\"Omid Mohebbi\")  => " );
        result2.stream().map(p->p.fullName).forEach(System.out::println);

        Stream.of(1,2,3,4,5,6,7,8,9,10,9,8,7,6,5,4,3,2,1)
                .takeWhile(i -> i < 4 )
                .forEach(System.out::print);
    }

    private static void reduceFunctionality() {
        var sumWithInit = Stream.of(1, 2, 3).reduce(0, (total, a) -> total += a);
        var sumWithoutInit = Stream.of(1, 2,3).reduce((sum , a)-> sum += a);
        System.out.println("reduce sum width init example = " + sumWithInit);
        System.out.println("reduce sum widthout init example = " + sumWithoutInit.get());


        String [] strs = {"You ", "are ", "the ", "best." };
        var javaSentence = Arrays.stream(strs).reduce(String::concat).get();
        System.out.println("Java : \t" + javaSentence);
    }

    private static void integerStream() {
        var sum = Stream.of(1, 3, 4, 12, 5, 2, 5).mapToInt(i -> i).sum();
        var avg = Stream.of(1, 3, 4, 12, 5, 2, 5).mapToInt(i -> i).average().toString();
        var distinct = Stream.of(1, 3, 4, 12, 5, 2, 5).mapToInt(i -> i).distinct().count();
        var min = Stream.of(1, 3, 4, 12, 5, 2, 5).mapToInt(i -> i).min().getAsInt();
        var max = Stream.of(1, 3, 4, 12, 5, 2, 5).mapToInt(i -> i).max().getAsInt();
        var count = Stream.of(1, 3, 4, 12, 5, 2, 5).mapToInt(i -> i).count();


        System.out.println("sum = " + sum);
        System.out.println("avg = " + avg);
        System.out.println("distinct count= " + distinct);
        System.out.println("min = " + min);
        System.out.println("max = " + max);
        System.out.println("count = " + count);
    }
}
