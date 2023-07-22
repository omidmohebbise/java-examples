package com.omidmohebbise.regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TempClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final String regex = "\\b(\\w+)\\b(?=.*\\b\\1\\b)";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE );

        int lineNumber = scanner.nextInt();
        StringBuilder content = new StringBuilder();

        for(int i = 0 ; i < lineNumber ; i++){
            String stringLine = scanner.nextLine();

            Matcher matcher = pattern.matcher(stringLine);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(sb, matcher.group(1));
            }
            matcher.appendTail(sb);


            //append the lines together
            content.append(stringLine +"\n");
        }
        System.out.println(content.toString());
    }
}
