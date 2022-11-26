package com.omidmohebbise.java11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class StringImprovement {
    public static void main(String[] args) {
        //blankMethod();
        //lines();
        //strips();
        //repeat();
        readWriteStringFromToFile();
    }

    public static void blankMethod() {
        System.out.println(" ".isBlank()); //true

        String s = "Anupam";
        System.out.println(s.isBlank()); //false
        String s1 = "";
        System.out.println(s1.isBlank()); //true
    }

    public static void lines() {
        String str = "JD\nJD\nJD";
        System.out.println(str);
        System.out.println(str.lines().collect(Collectors.toList()));
    }

    public static void strips() {
        String str = " JD ";
        System.out.print("Start");
        System.out.print(str.strip());
        System.out.println("End");


        System.out.print("Start");
        System.out.print(str.stripLeading());
        System.out.println("End");

        System.out.print("Start");
        System.out.print(str.stripTrailing());
        System.out.println("End");
    }

    public static void repeat(){
        String str = "=".repeat(3);
        System.out.println(str); //prints ===
    }

    public static void readWriteStringFromToFile()  {
        Path path = null;
        try {
            path = Files.writeString(Files.createTempFile("test", ".txt"), "This was posted on JD");
            System.out.println(path);
            String s = Files.readString(path);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
