package com.omidmohebbise.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindEmailAddresses {
    public static void main(String[] args) {
        String content = "Please contact me at john@example.com or send an email to info@example.com for more information.";
        Pattern pattern = Pattern.compile("\\w+@\\w+\\.\\w{3}");
        Matcher matcher = pattern.matcher(content);

        while(matcher.find())
            System.out.println(matcher.group());



    }
}
