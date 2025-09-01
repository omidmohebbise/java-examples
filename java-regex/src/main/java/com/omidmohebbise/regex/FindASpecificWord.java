package com.omidmohebbise.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindASpecificWord {
    public static void main(String[] args) {

        String sentence = "The quick brown fox jumps over the lazy dog.";
        String searchWord = "the";

        Pattern pattern = Pattern.compile(searchWord, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sentence);

        if(matcher.find())
            System.out.printf("\u001B[33m" + "RED The word is found. It is repeated for %d time(s).\n" , matcher.groupCount());


    }
}
