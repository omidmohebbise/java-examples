package com.oms.practice.commpn;

import java.util.ArrayList;
import java.util.List;

public class Person {
    public String fullName;

    Person(String fullName) {
        this.fullName = fullName;
    }

    public static List<Person> getPersons() {
        return new ArrayList<>(List.of(
                new Person("Ali sadeghi"), new Person("Omid Mohebbi"),
                new Person("Hassan Ranjbar"), new Person("Zahra sanaie")
        ));
    }
}
