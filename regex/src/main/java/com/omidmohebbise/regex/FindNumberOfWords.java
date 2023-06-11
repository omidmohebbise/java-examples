package com.omidmohebbise.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindNumberOfWords {
    public static void main(String[] args) {
        String text = "Hello, this is a sample sentence.";
        Pattern pattern = Pattern.compile("\\w{2,5}");
        Matcher matcher = pattern.matcher(text);
        int counter = 0;
        System.out.println("total group : " + matcher.groupCount());
        while (matcher.find()){
            System.out.print(counter+1 + ":"+ matcher.group() + "    ");
            counter++;
        }

        System.out.printf("\ntotal number of words = %d" ,counter );


    }
}
