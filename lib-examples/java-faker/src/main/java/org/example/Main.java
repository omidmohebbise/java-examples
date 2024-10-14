package org.example;

import com.github.javafaker.Faker;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();

        String name = faker.name().fullName(); // Miss Samanta Schmidt
        String firstName = faker.name().firstName(); // Emory
        String lastName = faker.name().lastName(); // Barton

        String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449


        System.out.printf("name: %s%n", name);
        System.out.printf("first Name: %s%n", firstName);
        System.out.printf("last Name: %s%n", lastName);
        System.out.printf("street Address: %s%n", streetAddress);

    }
}