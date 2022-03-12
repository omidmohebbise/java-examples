package com.oms.core.tricky;

public class PassByReferenceConfusing {

    public static void main(String[] args) {
        Person person = new Person("omid", "mohebbi");
        System.out.println(person);
        changePersonName(person);
        System.out.println(person);
    }

    static void changePersonName(Person person) {
        person.name = "#" + person.name;
    }
}

class Person {
    String name;
    String family;

    public Person(String name, String family) {
        this.name = name;
        this.family = family;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                '}';
    }
}

/*
Java is always “pass-by-value”. However, when we pass the value of an object,
 we pass the reference to it because the variables store the object reference, not the object itself.
 But this isn’t “pass-by-reference.” This could be confusing for beginners.
 */
